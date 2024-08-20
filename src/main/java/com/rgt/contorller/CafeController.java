package com.rgt.contorller;

import com.rgt.repository.CafeRepository;
import com.rgt.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class CafeController {
    private final CafeService cafeService;

}
