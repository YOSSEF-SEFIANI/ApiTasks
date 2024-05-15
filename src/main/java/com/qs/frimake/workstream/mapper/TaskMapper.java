package com.qs.frimake.workstream.mapper;

import com.qs.frimake.workstream.dtos.DetailsTaskDto;
import com.qs.frimake.workstream.dtos.TaskDto;
import com.qs.frimake.workstream.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    DetailsTaskDto toDto(Task task);

    Task fromDto(TaskDto task);
}

