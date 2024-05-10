package com.msfb.maju_mundur_application.controller;

import com.msfb.maju_mundur_application.constant.ApiRoute;
import com.msfb.maju_mundur_application.dto.request.RewardRequest;
import com.msfb.maju_mundur_application.dto.request.UpdateRewardRequest;
import com.msfb.maju_mundur_application.dto.response.CustomResponse;
import com.msfb.maju_mundur_application.dto.response.RewardResponse;
import com.msfb.maju_mundur_application.entity.Reward;
import com.msfb.maju_mundur_application.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiRoute.API_REWARD)
public class RewardController {

    private final RewardService rewardService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CustomResponse<Reward>> create(@RequestBody RewardRequest request) {
        Reward reward = rewardService.createReward(request);
        CustomResponse<Reward> response = CustomResponse.<Reward>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Creates reward successfully")
                .data(reward)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CustomResponse<RewardResponse>> getById(@PathVariable String id) {
        RewardResponse reward = rewardService.getRewardById(id);
        CustomResponse<RewardResponse> response = CustomResponse.<RewardResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Get reward successfully")
                .data(reward)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CustomResponse<List<RewardResponse>>> getAllReward() {
        List<RewardResponse> rewards = rewardService.getAllReward();
        CustomResponse<List<RewardResponse>> responses = CustomResponse.<List<RewardResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Get all reward successfully")
                .data(rewards)
                .build();
        return ResponseEntity.ok(responses);
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CustomResponse<RewardResponse>> update(@RequestBody UpdateRewardRequest request) {
        RewardResponse reward = rewardService.updateReward(request);
        CustomResponse<RewardResponse> response = CustomResponse.<RewardResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Updated reward successfully")
                .data(reward)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<CustomResponse<Reward>> delete(@PathVariable String id) {
        rewardService.delete(id);
        CustomResponse<Reward> response = CustomResponse.<Reward>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Deleted reward successfully")
                .build();
        return ResponseEntity.ok(response);
    }
}
