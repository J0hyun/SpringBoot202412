package com.shop.entity;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(value = {AuditingEntityListener.class})  //Auditing을 적용하기 위해 추가
@MappedSuperclass   // 공통 매핑 정보가 필요할 때 사용하는 어노테이션
@Getter @Setter
public abstract class BaseTimeEntity {

    @CreatedDate      //생성시간
    @Column(updatable = false) //생성 시간 변경 불가
    private LocalDateTime regTime;


    @LastModifiedDate
    private LocalDateTime updateTime;

}
