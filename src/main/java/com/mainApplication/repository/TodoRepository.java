package com.mainApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mainApplication.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
