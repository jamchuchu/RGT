package com.rgt.entity;

import com.rgt.constants.Authority;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
@Getter
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Authority authority;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(authority.getAuthority()));
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public static User of(String username, String password, Authority authority) {
        return User.builder()
                .username(username)
                .password(password)
                .authority(authority)
                .build();
    }
}
