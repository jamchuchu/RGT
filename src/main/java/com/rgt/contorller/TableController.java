package com.rgt.contorller;

import com.rgt.service.CafeService;
import com.rgt.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class TableController {
    private final TableService tableService;

}
