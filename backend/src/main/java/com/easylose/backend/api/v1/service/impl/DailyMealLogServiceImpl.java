package com.easylose.backend.api.v1.service.impl;

import com.easylose.backend.api.v1.domain.DailyMealLog;
import com.easylose.backend.api.v1.domain.Food;
import com.easylose.backend.api.v1.domain.User;
import com.easylose.backend.api.v1.dto.DailyMealLogDto.DailyMealCreateRequestDto;
import com.easylose.backend.api.v1.dto.DailyMealLogDto.DailyMealCreateResponseDto;
import com.easylose.backend.api.v1.dto.DailyMealLogDto.DailyMealFoodDto;
import com.easylose.backend.api.v1.dto.DailyMealLogDto.DailyMealRequestDto;
import com.easylose.backend.api.v1.dto.DailyMealLogDto.DailyMealResponseDto;
import com.easylose.backend.api.v1.dto.DailyMealLogDto.DailyMealResultDto;
import com.easylose.backend.api.v1.mapper.DailyMealLogMapper;
import com.easylose.backend.api.v1.repository.DailyMealLogRepository;
import com.easylose.backend.api.v1.repository.FoodRepository;
import com.easylose.backend.api.v1.repository.UserRepository;
import com.easylose.backend.api.v1.repository.specification.DailyMealLogSpecification;
import com.easylose.backend.api.v1.service.DailyMealLogService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DailyMealLogServiceImpl implements DailyMealLogService {

  public final DailyMealLogRepository dailyMealLogRepository;
  public final UserRepository userRepository;
  public final FoodRepository foodRepository;

  public final DailyMealLogMapper dailyMealLogMapper;

  public List<DailyMealResultDto> getDailyMealAll(Long id, LocalDate date) {
    Specification<DailyMealLog> spec = (root, query, criteriaBuilder) -> null;
    User user = userRepository.getReferenceById(id);
    if (date == null) {
      return null;
    }

    if (user != null && date != null) {
      spec = spec.and(DailyMealLogSpecification.equalUserAndDate(user, date));
    }
    List<DailyMealLog> dailyMealLogs = dailyMealLogRepository.findAll(spec);
    DailyMealResultDto response = dailyMealLogMapper.dailyLogResultDto(dailyMealLogs, date);

    return Collections.singletonList(response);
  }

  public DailyMealCreateResponseDto createDailyMeal(Long id, DailyMealCreateRequestDto requestDto) {
    User user = userRepository.getReferenceById(id);
    DailyMealCreateResponseDto.DailyMealCreateResponseDtoBuilder responseDto =
        DailyMealCreateResponseDto.builder();
    if (requestDto.getMealType() == null || requestDto.getDate() == null) {
      return null;
    }

    responseDto.date(requestDto.getDate()).mealType(requestDto.getMealType());

    List<DailyMealFoodDto> foods = new ArrayList<DailyMealFoodDto>();

    for (DailyMealRequestDto requestFoodDto : requestDto.getFoods()) {
      Food food = foodRepository.getReferenceById(requestFoodDto.getFoodId());

      if (food.getUser() != null && food.getUser() != user) {
        continue;
      }

      DailyMealLog dailyMealLog =
          DailyMealLog.builder().user(user).food(food).count(requestFoodDto.getCount()).build();

      dailyMealLogMapper.updateDailyMealLogFromRequestDto(requestDto, dailyMealLog);
      dailyMealLogRepository.save(dailyMealLog);

      foods.add(dailyMealLogMapper.toDailyMealFoodDto(dailyMealLog));
    }

    responseDto.foods(foods);

    return responseDto.build();
  }

  public DailyMealResponseDto updateDailyMeal(
      Long id, Long dailyMeal_id, DailyMealRequestDto requestDto) {
    User user = userRepository.getReferenceById(id);
    DailyMealLog dailyMealLog = dailyMealLogRepository.getReferenceById(dailyMeal_id);

    if (user != dailyMealLog.getUser()) {
      return null;
    }

    if (requestDto.getFoodId() != null) {
      Food food = foodRepository.getReferenceById(requestDto.getFoodId());
      if (food.getUser() != null && food.getUser() != user) {
        return null;
      }
      dailyMealLog.setFood(food);
    }

    dailyMealLogMapper.updateDailyMealLogFromRequestDto(requestDto, dailyMealLog);
    dailyMealLogRepository.save(dailyMealLog);

    return dailyMealLogMapper.dailyMealLogToDto(dailyMealLog);
  }

  public boolean deleteDailyMeal(Long id, Long dailyMeal_id) {
    User user = userRepository.getReferenceById(id);
    DailyMealLog dailyMealLog = dailyMealLogRepository.getReferenceById(dailyMeal_id);

    log.info("daily : {}", dailyMealLog);
    if (user != dailyMealLog.getUser()) {
      return false;
    }

    dailyMealLogRepository.delete(dailyMealLog);

    return true;
  }
}
