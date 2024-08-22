package com.rgt.contorller;

import com.rgt.config.JwtConfig;
import com.rgt.constants.Authority;
import com.rgt.dto.request.UserReqDto;
import com.rgt.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "User Management", description = "사용자 관리 API")
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;

    @Operation(summary = "회원가입", description = "새로운 사용자를 등록합니다.")
    @ApiResponse(responseCode = "201", description = "회원가입 성공",
            content = @Content(schema = @Schema(implementation = UserDetails.class)))
    @PostMapping("/signup")
    public ResponseEntity<UserDetails> signup(
            @Parameter(description = "사용자 권한", required = true)
            @RequestParam(name = "authority") Authority authority,
            @Parameter(description = "사용자 정보", required = true)
            @RequestBody UserReqDto reqDto){
        UserDetails user = null;
        if(authority.equals(Authority.OWNER)){
            user = userService.saveUserForOwner(reqDto);
        }else if(authority.equals( Authority.USER)){
            user = userService.saveUserForUser(reqDto);
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(user);
    }

    @Operation(summary = "로그인", description = "사용자 인증 및 토큰 발급")
    @ApiResponse(responseCode = "200", description = "로그인 성공",
            content = @Content(schema = @Schema(implementation = Map.class)))
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login (
            @Parameter(description = "로그인 정보", required = true)
            @RequestBody UserReqDto reqDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        reqDto.getUsername(),
                        reqDto.getPassword()
                )
        );
        String accessToken = jwtConfig.generateToken((UserDetails) authentication.getPrincipal());
        String refreshToken = jwtConfig.generateRefreshToken((UserDetails) authentication.getPrincipal());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("refreshToken", refreshToken);

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("accessToken", accessToken);
        responseBody.put("refreshToken", refreshToken);
        responseBody.put("principal", authentication.getPrincipal());

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(responseBody);
    }
}