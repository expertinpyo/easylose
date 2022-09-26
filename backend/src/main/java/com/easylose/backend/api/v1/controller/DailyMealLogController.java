package com.easylose.backend.api.v1.controller;

import com.easylose.backend.api.v1.dto.DailyMealLogDto;
import com.easylose.backend.api.v1.service.DailyMealLogService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/dailymeal")
public class DailyMealLogController {
  DailyMealLogService dailyMealLogService;

  @Autowired
  public DailyMealLogController(DailyMealLogService dailyMealLogService) {
    this.dailyMealLogService = dailyMealLogService;
  }

  @GetMapping("")
  @Operation(summary = "유저가 먹은 음식 조회", description = "유저가 특정 날짜에 먹은 음식 조회")
  public ResponseEntity<Collection> getDailyMealAll(
      @AuthenticationPrincipal Long id,
      @RequestBody DailyMealLogDto.DailyMealGetRequestDto getRequestDto) {
    Collection response = dailyMealLogService.getDailyMealAll(id, getRequestDto);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PostMapping("")
  @Operation(summary = "유저가 먹은 음식 생성", description = "먹은 음식 생성")
  public ResponseEntity<DailyMealLogDto.DailyMealResponseDto> createDailyMeal(
      @AuthenticationPrincipal Long id,
      @RequestBody DailyMealLogDto.DailyMealRequestDto requestDto) {
    DailyMealLogDto.DailyMealResponseDto response =
        dailyMealLogService.createDailyMeal(id, requestDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PutMapping("/{dailyMeal_id}")
  @Operation(summary = "유저가 먹은 음식 수정", description = "먹은 음식 수정")
  public ResponseEntity<DailyMealLogDto.DailyMealResponseDto> updateDailyMeal(
      @AuthenticationPrincipal Long id,
      @PathVariable Long dailyMeal_id,
      @RequestBody DailyMealLogDto.DailyMealRequestDto requestDto) {
    DailyMealLogDto.DailyMealResponseDto response =
        dailyMealLogService.updateDailyMeal(id, dailyMeal_id, requestDto);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @DeleteMapping("/{dailymeal_id}")
  @Operation(summary = "로그인 한 유저 정보 삭제", description = "로그인 한 유저 정보를 삭제한다.")
  public ResponseEntity deleteDailyMeal(
      @AuthenticationPrincipal Long id, @PathVariable Long dailyMeal_id) {
    dailyMealLogService.deleteDailyMeal(id, dailyMeal_id);
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
}