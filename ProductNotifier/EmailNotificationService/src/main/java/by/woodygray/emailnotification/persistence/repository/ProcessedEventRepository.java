package by.woodygray.emailnotification.persistence.repository;

import by.woodygray.emailnotification.persistence.entity.ProcessedEventEntity;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

@Registered
public interface ProcessedEventRepository extends JpaRepository<ProcessedEventEntity, Long> {

    ProcessedEventEntity findByMessageId(String messageId);
}
