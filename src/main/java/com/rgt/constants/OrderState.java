package com.rgt.constants;

import lombok.Getter;

@Getter
public enum OrderState {
    CONFIRM("수락됨"),
    CANCEL("취소"),
    COMPLETE("완료");

    private final String description;

    OrderState(String description) {
        this.description = description;
    }
}