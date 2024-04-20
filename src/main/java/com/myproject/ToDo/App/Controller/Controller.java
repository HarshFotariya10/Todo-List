package com.myproject.ToDo.App.Controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.myproject.ToDo.App.Service.ToDoService;
import com.myproject.ToDo.App.modal.Todo;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;






@org.springframework.stereotype.Controller
public class Controller {
	@Autowired
	private ToDoService todoService;
	
	@GetMapping({"/", "viewToDoList"})
	public String viewAllTodoItem(Model model, @ModelAttribute("Message") String message) {
	    model.addAttribute("list", todoService.getall()); // Corrected attribute name to "list"
	    model.addAttribute("message", message); // Corrected attribute name to "message"
	    return "viewToDoList";
	}
	
	@GetMapping("/updateToDoStatus/{id}")
	public String updatetoDoList(@PathVariable Long id ,RedirectAttributes redirectAttributes) {
		if(todoService.updateStatus(id)) {
			redirectAttributes.addFlashAttribute("Message"," Update  Successs");
			return "redirect:/viewToDoList";
		}
		redirectAttributes.addFlashAttribute("Message"," Update  Failure");
		return "redirect:/viewToDoList";
	}
	
	@GetMapping("/AddToDoItem")
	public String addTodoItem(Model model) {
		model.addAttribute("todo",new Todo());
		return "AddToDoItem";
	}
	
	@PostMapping("/saveToDoItem")
	public String saveTodoItem(Todo todo,RedirectAttributes redirectAttributes ) {
		if(todoService.SaveOrUpdateTodoItems(todo)) {
			redirectAttributes.addFlashAttribute("Message","Save Success");
			return "redirect:/viewToDoList";
			
		}
		redirectAttributes.addFlashAttribute("Message","Save Faliure");
		return "redirect:/AddToDoItem";
	}
	 
	@GetMapping("/editToDoItem/{id}")
	public String editTodoItems(@PathVariable Long id,Model model) {
		model.addAttribute("todo",todoService.getToDoItemsById(id));
		return "editSaveTodoItem";
	}
	
	@PostMapping("/editSaveToDoItem")
	public String editSaveTodoItem(Todo todo,RedirectAttributes redirectAttributes) {
		if(todoService.SaveOrUpdateTodoItems(todo)) {
			redirectAttributes.addFlashAttribute("Message","Edit Success");
			return "redirect:/viewToDoList";
			
		}
		redirectAttributes.addFlashAttribute("Message","Edit Faliure");
		return "redirect:/editToDoItem/" + todo.getId();
	}	
	
	@GetMapping("/deleteToDoItem/{id}")
	public String deleteTodoItem(@PathVariable Long id,RedirectAttributes redirectAttributes) {
		
		if(todoService.deleteTodoItem(id)){
			redirectAttributes.addFlashAttribute("Message","Delete Success");
			return "redirect:/viewToDoList";
		}
		redirectAttributes.addFlashAttribute("Message","Delete Failure");
		return "redirect:/viewToDoList";
		}

	
}
