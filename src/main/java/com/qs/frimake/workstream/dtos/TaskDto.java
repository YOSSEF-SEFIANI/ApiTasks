package com.qs.frimake.workstream.dtos;

import com.qs.frimake.workstream.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.util.Date;


@SuperBuilder
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class TaskDto{
    private String summary;
    private String description;
    private Task.TaskType type;
    private String responsible;
    private Date dueDate;
}
