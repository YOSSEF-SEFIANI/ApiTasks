package com.qs.frimake.workstream.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import net.youss.fontendreactclient.config.jpa.entity.CustomAbstractAuditable;
import org.hibernate.envers.Audited;

import java.util.Date;


@Audited
@Entity
@RequiredArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class Task extends CustomAbstractAuditable<Long> {

    /**
     * Summary of the task, serving as a brief title or introduction.
     */

    @Column(nullable = false, length = 255)
    private String summary;

    /**
     * Detailed description of the task.
     */

    @Column(nullable = false, length = 1000)
    private String description;

    /**
     * Type of the task, categorizing its purpose (e.g., EMAIL, CALL).
     */

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskType type;

    /**
     * Specifies if the task is visible only to moderators. If true,
     *  only moderators can update this task.
     */

    private boolean moderationTask;

    /**
     * User responsible for completing the task.
     */

    private String responsible;

    /**
     * Due date for the task completion.
     */

    @Column(nullable = false)
    private Date dueDate;

    /**
     * Current status of the task (e.g., PENDING, DONE, CANCELLED).
     */

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    /**
     * Date when the task was processed or handled, particularly for status changes.
     */
    @Column
    private Date processDate;

    /**
     * Optional comment for when the task status changes to DONE or CANCELLED.
     */
    @Column(length = 1000)
    private String comment;

    /**
     * Generic foreign key to associate this task with any object like User, Project, Activity, etc.
     * Storing the class name of the target entity.
     */
//    @ManyToOne
    @Column(nullable = false)
    private String targetEntity;

    /**
     * The identifier of the target entity this task is associated with.
     */

    @Column(nullable = false)
    private String targetEntityId;




    public enum TaskStatus {
        PENDING, DONE, CANCELLED
    }

    public enum TaskType {
        TODO, EMAIL, CALL, MEETING, DOCUMENT, SIGNATURE
    }
}



