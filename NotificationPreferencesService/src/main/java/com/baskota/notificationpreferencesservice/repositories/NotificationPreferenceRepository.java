package com.baskota.notificationpreferencesservice.repositories;

import com.baskota.notificationpreferencesservice.entities.NotificationPreferenceDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationPreferenceRepository extends CrudRepository<NotificationPreferenceDTO, Long> {

    @Override
    Optional<NotificationPreferenceDTO> findById(Long aLong);

    Optional<NotificationPreferenceDTO> findByCustomerId(String customerId);
}
