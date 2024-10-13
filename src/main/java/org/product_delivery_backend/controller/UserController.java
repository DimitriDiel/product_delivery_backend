package org.product_delivery_backend.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.product_delivery_backend.dto.userDto.UserRequestDto;
import org.product_delivery_backend.dto.userDto.UserResponseDto;
import org.product_delivery_backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Tag(name = "User controller")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Register a new user",
            description = "Create a new user account with the provided registration details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserResponseDto.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Invalid user request",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PostMapping("/register")
    ResponseEntity<UserResponseDto> registerUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.registerUser(userRequestDto));
    }

    @Operation(summary = "Retrieve all users",
            description = "Get a list of all registered users in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users retrieved successfully",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserResponseDto.class))
                    }),
            @ApiResponse(responseCode = "404", description = "No users found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping
    ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
