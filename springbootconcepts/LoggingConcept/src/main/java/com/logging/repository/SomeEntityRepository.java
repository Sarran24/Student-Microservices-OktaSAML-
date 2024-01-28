package com.logging.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logging.model.SomeEntity;

public interface SomeEntityRepository extends JpaRepository<SomeEntity, Long> {

}
