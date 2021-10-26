package com.rental.domain.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum reserveStatus {

    GENERAL("GENERAL", "일반상태"),
    RENTAL("RENTAL", "대여중");

    private final String key;
    private final String value;
}
