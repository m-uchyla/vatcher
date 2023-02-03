package com.fmdgroup.vatcher.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fmdgroup.vatcher.model.Notifications;

public interface NotificationsRepository extends JpaRepository<Notifications, Long> {

//	List<Notifications> findByActiveTrue();

}
