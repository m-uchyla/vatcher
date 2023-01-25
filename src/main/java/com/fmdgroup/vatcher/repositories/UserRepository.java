package com.fmdgroup.vatcher.repositories;

import org.springframework.data.repository.CrudRepository;

import com.fmdgroup.vatcher.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
