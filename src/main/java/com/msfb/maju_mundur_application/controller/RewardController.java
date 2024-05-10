package com.msfb.maju_mundur_application.controller;

import com.msfb.maju_mundur_application.constant.ApiRoute;
import com.msfb.maju_mundur_application.dto.request.RewardRequest;
import com.msfb.maju_mundur_application.dto.request.UpdateRewardRequest;
import com.msfb.maju_mundur_application.dto.response.CustomResponse;
import com.msfb.maju_mundur_application.dto.response.RewardResponse;
import com.msfb.maju_mundur_application.entity.Reward;
import com.msfb.maju_mundur_application.service.RewardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiRoute.API_REWARD)
@Tag(name = "Reward", description = "API for reward")
public class RewardController {

    private final RewardService rewardService;

    @Operation(
            summary = "Create",
            description = "Create new reward"
    )
    @SecurityRequirement(name = "Authorization")
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

    @Operation(
            summary = "Get By Id",
            description = "Get reward by id"
    )
    @SecurityRequirement(name = "Authorization")
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

    @Operation(
            summary = "Get All",
            description = "Get all reward"
    )
    @SecurityRequirement(name = "Authorization")
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

    @Operation(
            summary = "Update",
            description = "Update reward"
    )
    @SecurityRequirement(name = "Authorization")
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

    @Operation(
            summary = "Delete",
            description = "Delete reward by id"
    )
    @SecurityRequirement(name = "Authorization")
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
