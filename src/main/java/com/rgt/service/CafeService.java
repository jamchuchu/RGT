package com.rgt.service;

import com.rgt.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CafeService {
    private final CafeRepository cafeRepository;
}
