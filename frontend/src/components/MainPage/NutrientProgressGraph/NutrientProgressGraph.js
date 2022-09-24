import classes from "./NutrientProgressGraph.module.css"
import ReactApexChart from "react-apexcharts"

function NutrientProgressGraph() {
  const data = {
    series: [
      {
        name: "목표치",
        data: [100, 100, 100, 100],
      },
      {
        name: "현재",
        data: [76, 85, 101, 98],
      },
    ],
    responsive: [
      {
        breakpoint: 480,
        options: {
          legend: {
            position: "top",
          },
        },
      },
    ],
    options: {
      chart: {
        type: "bar",
        height: 350,
        toolbar: {
          show: false,
        },
      },
      colors: ["#7c83fd", "#afb4ff"],
      plotOptions: {
        bar: {
          horizontal: false,
          columnWidth: "55%",
          endingShape: "rounded",
        },
      },
      dataLabels: {
        enabled: false,
      },
      stroke: {
        show: true,
        width: 2,
        colors: ["transparent"],
      },
      legend: {
        show: false,
      },
      xaxis: {
        categories: ["열량", "탄수화물", "단백질", "지방"],
      },
      yaxis: {
        show: false,
      },
      fill: {
        opacity: 1,
      },
      tooltip: {
        y: {
          formatter: function (val) {
            return val + "%"
          },
        },
      },
    },
  }
  return (
    <div className={classes.chart_box}>
      <div className={classes.top_area}>
        <span>목표 달성률</span>
        <div className={classes.chart_info}>
          <div className={classes.info_item}>
            <div style={{ background: "var(--main-color)" }}></div>
            <p>목표치</p>
          </div>
          <div className={classes.info_item}>
            <div style={{ background: "var(--sub-color)" }}></div>
            <p>현재</p>
          </div>
        </div>
      </div>
      <div>
        <ReactApexChart
          options={data.options}
          series={data.series}
          type="bar"
        />
      </div>
    </div>
  )
}
export default NutrientProgressGraph
