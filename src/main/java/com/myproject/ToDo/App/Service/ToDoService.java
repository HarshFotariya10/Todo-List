package com.myproject.ToDo.App.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.ToDo.App.Repository.ToDoRepo;
import com.myproject.ToDo.App.modal.Todo;

@Service
public class ToDoService {
	
	@Autowired
	ToDoRepo todorepo;
	
	public List<Todo> getall(){
		ArrayList<Todo> todolist  =  new ArrayList<>();
		todorepo.findAll().forEach(todo -> todolist.add(todo));
		return todolist;
	}
	
	public Todo getToDoItemsById(Long id) {
		return todorepo.findById(id).get();
	}
	
	public Boolean updateStatus(Long id) {
		Todo todo =  getToDoItemsById(id);
		todo.setStatus("Completed");
		
		return SaveOrUpdateTodoItems(todo);
	}
	
	public boolean SaveOrUpdateTodoItems(Todo todo) {
		Todo update = todorepo.save(todo);
		if(getToDoItemsById(update.getId()) != null) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean deleteTodoItem(Long Id) {
		todorepo.deleteById(Id);
		if(todorepo.findById(Id).isEmpty()) {
			return true;
		}	
		else {
			return false;
		}
		
	}
	
}
