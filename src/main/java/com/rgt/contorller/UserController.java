package com.rgt.contorller;

import com.rgt.service.JwtService;
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

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<UserDetails> signup(@RequestParam(name = "authority") Authority authority, @RequestBody UserReqDto reqDto){
        UserDetails user = null;
        if(authority.equals(Authority.CAFE_OWNER)){
            user = userService.saveUserForOwner(reqDto);
        }else if(authority.equals( Authority.USER)){
            user = userService.saveUserForUser(reqDto);
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody UserReqDto reqDto){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            reqDto.getUsername(),
                            reqDto.getPassword()
                )
            );
            //토큰 리턴
            String token = jwtService.generateToken((UserDetails) authentication.getPrincipal());
            // JWT 토큰을 헤더에 포함
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + token);

            // 토큰과 함께 사용자 정보를 반환
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("token", token);
            responseBody.put("principal", authentication.getPrincipal());

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .headers(headers)
                    .body(responseBody);

        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(e);
        }

    }



}
