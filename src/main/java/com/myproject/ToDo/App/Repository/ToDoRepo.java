package com.myproject.ToDo.App.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myproject.ToDo.App.modal.Todo;
@Repository
public interface ToDoRepo  extends JpaRepository<Todo, Long>{

}
