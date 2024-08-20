package com.rgt.service;

import com.rgt.repository.CafeRepository;
import com.rgt.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MenuService {
    private final MenuRepository menuRepository;


}
