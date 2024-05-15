package com.qs.frimake.workstream.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;


@SuperBuilder
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class DetailsTaskDto extends TaskDto{

    @CreatedDate
    private LocalDateTime createdDate;
}
