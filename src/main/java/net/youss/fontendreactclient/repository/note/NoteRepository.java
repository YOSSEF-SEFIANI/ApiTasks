package net.youss.fontendreactclient.repository.note;

import com.qs.frimake.workstream.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Task, Long> {

}
