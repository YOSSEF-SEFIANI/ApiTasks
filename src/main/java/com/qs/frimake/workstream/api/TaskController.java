package com.qs.frimake.workstream.api;

import com.qs.frimake.workstream.dtos.DetailsTaskDto;
import com.qs.frimake.workstream.dtos.TaskDto;
import jakarta.validation.Valid;
import com.qs.frimake.workstream.entity.Task;
import com.qs.frimake.workstream.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/tasks")
@AllArgsConstructor
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DetailsTaskDto createTask(@RequestBody TaskDto taskDto) {
        return taskService.createTask(taskDto);
    }


    @PutMapping("/{id}")
    public DetailsTaskDto update(@PathVariable Long id, @Valid @RequestBody TaskDto task) {
        return taskService.updateTask(id, task);
    }


    @PutMapping("/{id}/cancel")
    public Task cancel(@PathVariable Long id, @RequestBody String comment) {
        return taskService.cancelTask(id, comment);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {

    }
}
