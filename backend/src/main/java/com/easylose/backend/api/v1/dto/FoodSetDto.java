package com.easylose.backend.api.v1.dto;

import com.easylose.backend.api.v1.domain.FoodSetDetail;
import com.easylose.backend.api.v1.dto.FoodDto.FoodResponseDto;
import com.easylose.backend.api.v1.enums.MealType;
import java.util.List;
import java.util.Map;
import lombok.*;
import lombok.Builder.Default;
import lombok.extern.jackson.Jacksonized;

public class FoodSetDto {
  @AllArgsConstructor
  @Builder
  @Getter
  @Setter
  @ToString
  public static class FoodSetResponseDto {
    private Long id;
    private String name;
    private Map<MealType, List<FoodSetDetailResponseDto>> details;
    private Map<MealType, FoodSetSumDto> sums;
    private FoodSetSumDto total;
  }

  @AllArgsConstructor
  @Builder
  @Getter
  @Setter
  @ToString
  @Jacksonized
  public static class FoodSetRequestDto {
    private String name;
  }

  @AllArgsConstructor
  @Builder
  @Getter
  @Setter
  @ToString
  public static class FoodSetDetailResponseDto {
    private Long id;
    private Float count;
    private FoodResponseDto food;
  }

  @AllArgsConstructor
  @Builder
  @Getter
  @Setter
  @ToString
  public static class FoodSetDetailRequestDto {
    private Long foodId;
    private Float count;
  }

  @AllArgsConstructor
  @Builder
  @Getter
  @Setter
  @ToString
  public static class FoodSetDetailCreateResponseDto {
    private Long foodSetId;
    private MealType mealType;
    private List<FoodSetDetailResponseDto> foods;
  }

  @AllArgsConstructor
  @Builder
  @Getter
  @Setter
  @ToString
  public static class FoodSetDetailCreateRequestDto {
    private MealType mealType;
    private List<FoodSetDetailRequestDto> foods;
  }

  @AllArgsConstructor
  @Builder
  @Getter
  @Setter
  @ToString
  public static class FoodSetSumDto {
    private @Default Float calorie = 0f;
    private @Default Float carb = 0f;
    private @Default Float protein = 0f;
    private @Default Float fat = 0f;
    private @Default Float sugar = 0f;
    private @Default Float salt = 0f;
    private @Default Float cholesterol = 0f;
    private @Default Float saturatedFat = 0f;
    private @Default Float transFat = 0f;

    public void addFromDetail(FoodSetDetail detail) {
      calorie += detail.getFood().getCalorie() + detail.getCount();
      carb += detail.getFood().getCarb() + detail.getCount();
      protein += detail.getFood().getProtein() + detail.getCount();
      fat += detail.getFood().getFat() + detail.getCount();
      sugar += detail.getFood().getSugar() + detail.getCount();
      salt += detail.getFood().getSalt() + detail.getCount();
      cholesterol += detail.getFood().getCholesterol() + detail.getCount();
      saturatedFat += detail.getFood().getSaturatedFat() + detail.getCount();
      transFat += detail.getFood().getTransFat() + detail.getCount();
    }

    public void addFromDto(FoodSetSumDto dto) {
      calorie += dto.getCalorie();
      carb += dto.getCarb();
      protein += dto.getProtein();
      fat += dto.getFat();
      sugar += dto.getSugar();
      salt += dto.getSalt();
      cholesterol += dto.getCholesterol();
      saturatedFat += dto.getSaturatedFat();
      transFat += dto.getTransFat();
    }
  }
}
