package com.rental.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //JPA Entity 클래스들이 BaseTimeEntity을 상속할 경우 CreatedDate, ModifiedDate등의 필드들도 칼럼으로 인식시키기 위한 어노테이션
@EntityListeners(AuditingEntityListener.class) //Auditing 기능을 포함시키는 어노테이션
public class BaseTimeEntity { //엔티티들의 등록/수정시간을 자동으로 관리하기 위한 엔티티

    @CreatedDate //Entity가 생성될 때 시간이 자동 저장됨
    private LocalDateTime createdDate;

    @LastModifiedDate //조회한 Entity의 값을 변경할 때 시간이 자동 저장됨
    private LocalDateTime modifiedDate;

}
