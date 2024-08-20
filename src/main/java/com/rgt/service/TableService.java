package com.rgt.service;

import com.rgt.repository.CafeRepository;
import com.rgt.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TableService {
    private final TableRepository tableRepository;


}
