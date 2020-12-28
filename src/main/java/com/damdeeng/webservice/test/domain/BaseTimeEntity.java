package com.damdeeng.webservice.test.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


@Getter
@MappedSuperclass // BaseTimeEntity를 상속할 경우, 다음과 같은 컬럼 익식
@EntityListeners(AuditingEntityListener.class) // BaseTimeEntity 클래스에 Auditing 상속
public abstract class BaseTimeEntity {

    // Entity가 생성되어 저장될 때 시간 자동 저장
    @CreatedDate
    private LocalDateTime createdDate;

    // 조회한 Entity의 값이 변경될 때 시간 자동 저장
    @LastModifiedDate
    private LocalDateTime modifiedDate;

}
