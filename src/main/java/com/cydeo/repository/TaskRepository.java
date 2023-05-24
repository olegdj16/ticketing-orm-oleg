package com.cydeo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cydeo.entity.Task;


public interface TaskRepository extends JpaRepository <Task, Long>{

}
