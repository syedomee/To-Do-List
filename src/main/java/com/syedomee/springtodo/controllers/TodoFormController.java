package com.syedomee.springtodo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.syedomee.springtodo.models.TodoItem;
import com.syedomee.springtodo.repositories.TodoItemRepository;

@Controller
public class TodoFormController {
    private final Logger logger = LoggerFactory.getLogger(TodoFormController.class);

    @Autowired
    private TodoItemRepository todoItemRepository;

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        TodoItem todoItem = todoItemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("TodoItem: " + id + " not found"));
        
        model.addAttribute("todo", todoItem);
        return "update-todo-item";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") Long id, Model model) {
        TodoItem todoItem = todoItemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("TodoItem: " + id + " not found"));
        
        todoItemRepository.delete(todoItem);
        return "redirect:/";
    }
}
