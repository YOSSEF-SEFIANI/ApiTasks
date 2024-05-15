package com.qs.frimake.workstream.service;


import com.qs.frimake.workstream.dtos.DetailsTaskDto;
import com.qs.frimake.workstream.dtos.TaskDto;
import com.qs.frimake.workstream.entity.Task;
import com.qs.frimake.workstream.mapper.TaskMapper;
import com.qs.frimake.workstream.repository.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;


    // Method to create a task
    public DetailsTaskDto createTask(TaskDto taskDto) {
        Task task = taskMapper.fromDto(taskDto);
        task.setStatus(Task.TaskStatus.PENDING);
        Task saveTask = taskRepository.save(task);
        return taskMapper.toDto(saveTask);
    }
    

    public DetailsTaskDto updateTask(Long id, TaskDto task) {
        // Validation and business logic
        Task existingTask = taskRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Task not found"));

        // update table Task
        Task updatedTask = existingTask.toBuilder()
                .summary(task.getSummary())
                .description(task.getDescription())
                .type(task.getType())
                .responsible(task.getResponsible())
                .dueDate(task.getDueDate())
                .build();

        updatedTask = taskRepository.save(updatedTask);
        return taskMapper.toDto(updatedTask);
    }

    public Task cancelTask(Long id, String comment) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found for this id :: " + id));
        task.setStatus(Task.TaskStatus.CANCELLED);
        task.setComment(comment);
        task.setProcessDate(new Date());
        return taskRepository.save(task);
    }

    public Task completeTask(Long id, String comment) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found for this id :: " + id));
        task.setStatus(Task.TaskStatus.DONE);
        task.setComment(comment);
        task.setProcessDate(new Date());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found for this id :: " + id));
        taskRepository.delete(task);
    }

    public Page<Task> listTasks(Pageable pageable, String responsible, Task.TaskStatus status, Date dueDate) {
        // Filtering logic
        return taskRepository.findAll(pageable);  // Example, needs actual filtering implementation
    }

    public Page<Task> listTasksByTargetEntity(Pageable pageable, String targetEntity, String targetEntityId) {
        // Filtering logic
        return taskRepository.findBytargetEntityAndTargetEntityId(pageable, targetEntity, targetEntityId);
    }
}



