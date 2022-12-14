package com.easylose.backend.api.v1.service;

import com.easylose.backend.api.v1.dto.DailyMealLogDto.DailyMealCreateRequestDto;
import com.easylose.backend.api.v1.dto.DailyMealLogDto.DailyMealCreateResponseDto;
import com.easylose.backend.api.v1.dto.DailyMealLogDto.DailyMealRequestDto;
import com.easylose.backend.api.v1.dto.DailyMealLogDto.DailyMealResponseDto;
import com.easylose.backend.api.v1.dto.DailyMealLogDto.DailyMealResultDto;
import java.time.LocalDate;
import java.util.List;

public interface DailyMealLogService {
  List<DailyMealResultDto> getDailyMealAll(Long id, LocalDate date);

  //  List<DailyMealResponseDto> getDailyMealCalender(Long id, LocalDate date);

  DailyMealCreateResponseDto createDailyMeal(Long id, DailyMealCreateRequestDto requestDto);

  DailyMealResponseDto updateDailyMeal(Long id, Long dailyMeal_id, DailyMealRequestDto requestDto);

  boolean deleteDailyMeal(Long id, Long dailyMeal_id);
}
