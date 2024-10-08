package com.flow.assignment.rule.entity;

import com.flow.assignment.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.*;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "rule")
@Entity
@SQLDelete(sql = "UPDATE rule SET is_deleted = TRUE WHERE id = ?")
@Where(clause = "is_deleted = false")
public class Rule extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "id")
    private UUID id;

    @NotNull
    @Column(name = "address")
    private String address; // IP 주소

    @Column(name = "description")
    private String description; // 설명(최대 20자 입력 가능)

    @NotNull
    @Column(name = "start_time")
    private LocalDateTime startTime; // 사용 허용 시작 시간

    @NotNull
    @Column(name = "end_time")
    private LocalDateTime endTime; // 사용 허용 종료 시간

    @Builder
    public Rule(
            String address,
            String description,
            LocalDateTime startTime,
            LocalDateTime endTime
    ) {
        this.address = address;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static Rule of(
            String address,
            String description,
            LocalDateTime startTime,
            LocalDateTime endTime
    ) {
        return Rule.builder()
                .address(address)
                .description(description)
                .startTime(startTime)
                .endTime(endTime)
                .build();
    }

}
