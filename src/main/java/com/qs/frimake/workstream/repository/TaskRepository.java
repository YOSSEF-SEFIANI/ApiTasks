package com.qs.frimake.workstream.repository;

import com.qs.frimake.workstream.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("select t from Task t where t.targetEntity = ?1 and t.targetEntityId = ?2")
    Page<Task> findBytargetEntityAndTargetEntityId(Pageable pageable,
                                                   String targetEntity,
                                                   String targetEntityId);
}
