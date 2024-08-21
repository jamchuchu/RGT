package com.rgt.contorller;

import com.rgt.constants.Authority;
import com.rgt.entity.User;
import com.rgt.request.UserReqDto;
import com.rgt.service.CafeService;
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

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

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

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(authentication);
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Fail");
        }

    }



}
