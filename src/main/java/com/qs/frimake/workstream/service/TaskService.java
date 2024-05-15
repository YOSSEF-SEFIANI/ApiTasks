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


    public DetailsTaskDto updateTask(Long id, TaskDto taskDto) {
        Task existingTask = taskRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Task not found"));

        // Check if responsible or moderation can update the task
        if (!existingTask.getResponsible().equals(taskDto.getResponsible())
                && !existingTask.isModerationTask()) {
            throw new RuntimeException("You are not authorized to update this task");
        }
        // update table Task
        Task updatedTask = existingTask.toBuilder()
                .summary(taskDto.getSummary())
                .description(taskDto.getDescription())
                .type(taskDto.getType())
                .responsible(taskDto.getResponsible())
                .dueDate(taskDto.getDueDate())
                .build();

        updatedTask = taskRepository.save(updatedTask);
        return taskMapper.toDto(updatedTask);
    }

    public Task cancelTask(Long id, String comment, String responsible) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found for this id :: " + id));

        // Check if responsible or moderation can update the task
        if (!existingTask.getResponsible().equals(responsible)
                && !existingTask.isModerationTask()) {
            throw new RuntimeException("You are not authorized to cancel this task");
        }

        existingTask.setStatus(Task.TaskStatus.CANCELLED);
        existingTask.setComment(comment);
        // Date to cancel
        existingTask.setProcessDate(new Date());
        return taskRepository.save(existingTask);
    }

    public Task completeTask(Long id, String comment, String responsible) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found for this id :: " + id));

        // Check if responsible or moderation can update the task
        if (!existingTask.getResponsible().equals(responsible)
                && !existingTask.isModerationTask()) {
            throw new RuntimeException("You are not authorized to cancel this task");
        }

        existingTask.setStatus(Task.TaskStatus.DONE);
        existingTask.setComment(comment);
        existingTask.setProcessDate(new Date());

        return taskRepository.save(existingTask);
    }

    public void deleteTask(Long id, String comment) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found for this id :: " + id));
        existingTask.setComment(comment);
        taskRepository.delete(existingTask);
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



