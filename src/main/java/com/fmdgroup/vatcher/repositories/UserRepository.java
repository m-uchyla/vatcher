package com.fmdgroup.vatcher.repositories;

import org.springframework.data.repository.CrudRepository;

import com.fmdgroup.vatcher.model.SingleUser;

public interface UserRepository extends CrudRepository<SingleUser, Long> {

}
