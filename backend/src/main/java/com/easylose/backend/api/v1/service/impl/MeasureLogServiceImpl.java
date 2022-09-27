package com.easylose.backend.api.v1.service.impl;

import com.easylose.backend.api.v1.domain.MeasureLog;
import com.easylose.backend.api.v1.domain.User;
import com.easylose.backend.api.v1.dto.MeasureLogDto.MeasureLogFormDto;
import com.easylose.backend.api.v1.mapper.MeasureLogMapper;
import com.easylose.backend.api.v1.repository.MeasureLogRepository;
import com.easylose.backend.api.v1.repository.UserRepository;
import com.easylose.backend.api.v1.repository.specification.MeasureLogSpecification;
import com.easylose.backend.api.v1.service.MeasureLogService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MeasureLogServiceImpl implements MeasureLogService {

  private final MeasureLogRepository measureLogRepository;
  private final UserRepository userRepository;
  private final MeasureLogMapper measureLogMapper;

  @Override
  public List getMeasureLogAll(Long id, LocalDate start, LocalDate end) {
    User user = userRepository.getReferenceById(id);
    LocalDateTime startDate = null;
    LocalDateTime endDate = null;

    if (start != null) {
      startDate = start.atTime(0, 0, 0);
    } else {
      startDate = LocalDate.now().atTime(0, 0, 0);
    }

    if (end != null) {
      endDate = end.atTime(23, 59, 59);
    } else {
      endDate = LocalDate.now().atTime(23, 59, 59);
    }

    Specification<MeasureLog> spec = (root, query, builder) -> null;

    if (user != null) {
      spec = spec.and(MeasureLogSpecification.equalUser(user));
      log.info("first spec : {}", spec);
      spec = spec.and(MeasureLogSpecification.betweenDate(startDate, endDate));
      spec = spec.and(MeasureLogSpecification.equalNutrient(false));
    }
    List a = measureLogRepository.findAll(spec);
    log.info("List: {}", a);
    return a;
  }

  //  @Override
  //  public List getNutrientLogAll(Long id, LocalDate start, LocalDate end) {
  //    User user = userRepository.getReferenceById(id);
  //    LocalDateTime startDate = null;
  //    LocalDateTime endDate = null;
  //
  //    if (start != null) {
  //      startDate = start.atTime(0, 0, 0);
  //    } else {
  //      startDate = LocalDate.now().atTime(0, 0, 0);
  //    }
  //
  //    if (end != null) {
  //      endDate = end.atTime(23, 59, 59);
  //    } else {
  //      endDate = LocalDate.now().atTime(23, 59, 59);
  //    }
  //
  //    Specification<MeasureLog> spec = (root, query, builder) -> null;
  //
  //    if (user != null) {
  //      spec = spec.and(MeasureLogSpecification.equalUser(user));
  //      log.info("first spec : {}", spec);
  //      spec = spec.and(MeasureLogSpecification.betweenDate(startDate, endDate));
  //      spec = spec.and(MeasureLogSpecification.equalNutrient(true));
  //    }
  //    List a = measureLogRepository.findAll(spec);
  //    log.info("List: {}", a);
  //    return a;
  //  }

  @Override
  public void createMeasureLog(Long id) {
    User user = userRepository.getReferenceById(id);
    MeasureLogFormDto formDto = new MeasureLogFormDto();

    formDto.setWeight(user.getWeight());
    formDto.setHeight(user.getHeight());
    formDto.setUser(user);
    formDto.setIsNutrient(false);

    log.info("form dto : {}", formDto);
    measureLogRepository.save(measureLogMapper.toEntity(formDto));
  }

  //  @Override
  //  public void createNutrientLog(Long id) {
  //    User user = userRepository.getReferenceById(id);
  //    NutrientLogFormDto formDto = new NutrientLogFormDto();
  //
  //    formDto.setDailyCalorie(user.getDailyCalorie());
  //    formDto.setDailyCarb(user.getDailyCarb());
  //    formDto.setDailyProtein(user.getDailyProtein());
  //    formDto.setDailyFat(user.getDailyFat());
  //    formDto.setIsNutrient(true);
  //    formDto.setUser(user);
  //
  //    log.info("form dto : {}", formDto);
  //    measureLogRepository.save(measureLogMapper.fromNutrientToEntity(formDto));
  //  }
}