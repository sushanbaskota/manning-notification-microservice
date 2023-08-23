package com.baskota.applicationnotification.repositories;

import com.baskota.applicationnotification.entities.NotificationDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends CrudRepository<NotificationDTO, Long> {
}
