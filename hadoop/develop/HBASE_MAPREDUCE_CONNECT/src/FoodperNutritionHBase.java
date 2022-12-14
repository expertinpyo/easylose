/*
 * Copyright 2022. Dongwon Kim All rights reserved.
 *
 * File name : FoodperNutritionHBase.java
 * 
 * MapReduce for food recommendation
 *   map: 
 *     - key: carbon,protein,fat
 *     - value: food index
 *     - get data from 'nutrition_DB'
 * 
 *   reduce:
 *     - key: carbon,protein,fat
 *     - value: food index1,food index2, ....
 *     - store data to 'food_recommend'
 *     
 * Modificatoin history
 *     written by Dongwon Kim on Sep 23, 2022
 */
package ease;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.FirstKeyOnlyFilter;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.conf.Configuration;

public class FoodperNutritionHBase {
  public static class TokenizerMapper extends TableMapper<Text, Text> {
    // variable declarations
    private Text keypair = new Text();
    private Text numpair = new Text();
    private int carbon_max, carbon_unit, protein_max, protein_unit, fat_max, fat_unit;
    public static final byte[] CF = "nutrition".getBytes();
    public static final byte[] ATTR1 = "carbon".getBytes();
    public static final byte[] ATTR2 = "protein".getBytes();
    public static final byte[] ATTR3 = "fat".getBytes();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
      Configuration config = context.getConfiguration();
      carbon_max = config.getInt("carbon_max", 1000);
      protein_max = config.getInt("protein_max", 200);
      fat_max = config.getInt("fat_max", 200);
      carbon_unit = config.getInt("carbon_unit", 50);
      protein_unit = config.getInt("protein_unit", 5);
      fat_unit = config.getInt("fat_unit", 5);
    }
    
    /*
     * Map
     * 
     * input: 
     *  row: food index
     *  columns: nutrition:carbon, nutrition:protein, nutrition:fat
     * 
     * output:
     *  key: Text, carbon,protein,fat
     *  value: Text, food index 
     */
    @Override
    public void map(ImmutableBytesWritable row, Result columns, Context context)
        throws IOException, InterruptedException {
      String carbon_s = new String(columns.getValue(CF, ATTR1));
      String protein_s = new String(columns.getValue(CF, ATTR2));
      String fat_s = new String(columns.getValue(CF, ATTR3));
      
      double carbon = Double.parseDouble(carbon_s);
      double protein = Double.parseDouble(protein_s);
      double fat = Double.parseDouble(fat_s);

      // range nutrition values
      for (int i = 0; i < carbon_max; i += carbon_unit) {
        if (carbon >= i && carbon < i + carbon_unit) {
          carbon = (double) i;
          break;
        }
      }
      for (int i = 0; i < protein_max; i += protein_unit) {
        if (protein >= i && protein < i + protein_unit) {
          protein = (double) i;
          break;
        }
      }
      for (int i = 0; i < fat_max; i += fat_unit) {
        if (fat >= i && fat < i + fat_unit) {
          fat = (double) i;
          break;
        }
      }

      keypair.set(Double.toString(carbon) + "," + Double.toString(protein) + "," + Double.toString(fat));
      numpair.set(row.get());
      context.write(keypair,numpair);
    }
  }
  
  public static class ConcatenatorReducer extends TableReducer<Text, Text, ImmutableBytesWritable> {
    /*
     * Reduce
     * 
     * input
     *  key: Text, carbon,protein,fat
     *  value: Text, food index 
     * 
     * output
     *  key: ImmutableBytesWritable carbon,protein,fat
     *  value: ImmutableBytesWritable, food indexs
     */    
    @Override
    public void reduce(Text key, Iterable<Text> values, Context context)
        throws IOException, InterruptedException {
      String s = new String();
      int comma = 0;
      for (Text val : values) {
        if (comma == 0) {
          comma = 1;
          s = val.toString();
        } else {
          s += ("," + val.toString());
        }
      }
      ImmutableBytesWritable key_b = new ImmutableBytesWritable(Bytes.toBytes(key.toString()));
      Put put = new Put(Bytes.toBytes(key.toString()));
      put.addColumn(Bytes.toBytes("foods_num"), Bytes.toBytes("food_index"), Bytes.toBytes(s));
      context.write(key_b, put);
    }
  }
  
  /* Main function */
  public static void main(String[] args) throws Exception {
    HBaseConfiguration conf = new HBaseConfiguration();
    Job job = new Job(conf, "food per nutrition");
    job.setJarByClass(FoodperNutritionHBase.class);
    Scan scan = new Scan();
    String families = "nutrition";
    String qualifiers = "carbon,protein,fat";
    scan.addColumn(Bytes.toBytes(families), Bytes.toBytes("carbon"));
    scan.addColumn(Bytes.toBytes(families), Bytes.toBytes("protein"));
    scan.addColumn(Bytes.toBytes(families), Bytes.toBytes("fat"));
    TableMapReduceUtil.initTableMapperJob("nutrition_DB", scan, TokenizerMapper.class, Text.class, Text.class, job);
    TableMapReduceUtil.initTableReducerJob("food_recommend", ConcatenatorReducer.class, job);
    job.setNumReduceTasks(1);

    
    Configuration config = job.getConfiguration();
    config.setInt("carbon_max", 1000);
    config.setInt("protein_max", 200);
    config.setInt("fat_max", 200);
    config.setInt("carbon_unit", 50);
    config.setInt("protein_unit", 5);
    config.setInt("fat_unit", 5);

    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }  
}
