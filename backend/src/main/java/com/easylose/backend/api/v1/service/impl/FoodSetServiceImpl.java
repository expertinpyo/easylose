package com.easylose.backend.api.v1.service.impl;

import com.easylose.backend.api.v1.domain.FoodSet;
import com.easylose.backend.api.v1.domain.FoodSetDetail;
import com.easylose.backend.api.v1.domain.User;
import com.easylose.backend.api.v1.dto.FoodSetDto.FoodSetDetailRequestDto;
import com.easylose.backend.api.v1.dto.FoodSetDto.FoodSetDetailResponseDto;
import com.easylose.backend.api.v1.dto.FoodSetDto.FoodSetResponseDto;
import com.easylose.backend.api.v1.mapper.FoodSetMapper;
import com.easylose.backend.api.v1.repository.FoodSetDetailRepository;
import com.easylose.backend.api.v1.repository.FoodSetRepository;
import com.easylose.backend.api.v1.repository.UserRepository;
import com.easylose.backend.api.v1.service.FoodSetService;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class FoodSetServiceImpl implements FoodSetService {

  private final FoodSetRepository foodSetRepository;
  private final FoodSetDetailRepository foodSetDetailRepository;
  private final FoodSetMapper foodSetMapper;
  private final UserRepository userRepository;

  // 단순 이렇게 "식단"만 보내는 것은 의미 없음
  // 식단의 디테일까지 같이 보낼 수 있도록 설계해야 함

  public Collection<FoodSetResponseDto> getFoodSetAll(Long id) {
    List<FoodSet> foodSets = foodSetRepository.findByUserId(id);

    List<FoodSetResponseDto> foodSetDto = foodSetMapper.foodSetsToDto(foodSets);

    return foodSetDto;
  }

  public FoodSetResponseDto createFoodSet(Long id) {
    User user = userRepository.getReferenceById(id);

    FoodSet foodSet = FoodSet.builder().user(user).build();

    foodSetRepository.save(foodSet);

    return foodSetMapper.foodSetToDto(foodSet);
  }

  public boolean deleteFoodSet(Long id, Long foodSetId) {
    User user = userRepository.getReferenceById(id);

    FoodSet foodSet = foodSetRepository.getReferenceById(foodSetId);
    if (foodSet.getUser() != user) {
      return false;
    }

    foodSetRepository.delete(foodSet);

    return true;
  }

  public FoodSetDetailResponseDto createFoodSetDetail(
      Long id, Long foodSetId, FoodSetDetailRequestDto body) {

    User user = userRepository.getReferenceById(id);
    FoodSet foodSet = foodSetRepository.getReferenceById(foodSetId);

    if (foodSet.getUser() != user) {
      return null;
    }

    FoodSetDetail foodSetDetail = FoodSetDetail.builder().foodSet(foodSet).build();

    foodSetMapper.updateFoodSetDetail(body, foodSetDetail);

    foodSetDetailRepository.save(foodSetDetail);

    return foodSetMapper.foodSetDetailToDto(foodSetDetail);
  }

  public FoodSetDetailResponseDto updateFoodSetDetail(
      Long id, Long foodSetId, Long foodSetDetailId, FoodSetDetailRequestDto body) {

    User user = userRepository.getReferenceById(id);
    FoodSet foodSet = foodSetRepository.getReferenceById(foodSetId);
    FoodSetDetail foodSetDetail = foodSetDetailRepository.getReferenceById(foodSetDetailId);

    if (foodSet.getUser() != user) {
      return null;
    }

    if (foodSetDetail.getFoodSet() != foodSet) {
      return null;
    }

    foodSetMapper.updateFoodSetDetail(body, foodSetDetail);

    foodSetDetailRepository.save(foodSetDetail);

    return foodSetMapper.foodSetDetailToDto(foodSetDetail);
  }

  public boolean deleteFoodSetDetail(Long id, Long foodSetId, Long foodSetDetailId) {

    User user = userRepository.getReferenceById(id);
    FoodSet foodSet = foodSetRepository.getReferenceById(foodSetId);
    FoodSetDetail foodSetDetail = foodSetDetailRepository.getReferenceById(foodSetDetailId);

    if (foodSet.getUser() != user) {
      return false;
    }

    if (foodSetDetail.getFoodSet() != foodSet) {
      return false;
    }

    foodSetDetailRepository.delete(foodSetDetail);

    return true;
  }
}
