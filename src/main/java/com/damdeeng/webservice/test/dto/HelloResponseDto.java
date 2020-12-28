package com.damdeeng.webservice.test.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
// 'final' 선언된 모든 필드에 생성자 생성
@RequiredArgsConstructor
public class HelloResponseDto {

    private final String name;
    private final int amount;

    // 생성자
//    public HelloResponseDto(String name, int amount) {
//        this.name = name;
//        this.amount = amount;
//    }
}
