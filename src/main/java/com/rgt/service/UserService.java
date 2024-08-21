package com.rgt.service;

import com.rgt.constants.Authority;
import com.rgt.entity.User;
import com.rgt.repository.CafeRepository;
import com.rgt.repository.UserRepository;
import com.rgt.request.UserReqDto;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username);
    }

    //일반 회원가입
    public UserDetails saveUserForUser(UserReqDto reqDto){
        User user = User.of(reqDto.getUserName(), passwordEncoder.encode(reqDto.getPassword()), Authority.USER);
        return userRepository.save(user);
    }

    //사장 회원가입
    public UserDetails saveUserForOwner(UserReqDto reqDto){
        User user = User.of(reqDto.getUserName(), passwordEncoder.encode(reqDto.getPassword()), Authority.CAFE_OWNER);
        return userRepository.save(user);
    }


}
