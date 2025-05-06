package com.example.primo.progetto.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();
    private static int todosCount = 0;
    static {
        todos.add(new Todo(++todosCount, "umb", "Learn Java",
                LocalDate.now().plusYears(1), false));
        todos.add(new Todo(++todosCount, "umb", "Learn React",
                LocalDate.now().plusYears(1), false));
        todos.add(new Todo(++todosCount, "umb", "Learn Docker",
                LocalDate.now().plusYears(2), false));
    }
    public List<Todo> findByUsername(String username){
        Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
        return todos.stream().filter(predicate).toList();
    }
    public void addTodo(String username, String description, LocalDate targetDate, boolean done){
        Todo todo = new Todo(++todosCount, username, description, targetDate, done);
        todos.add(todo);
    }

    public void deleteById(long id){

        Predicate<? super Todo> predicate = todo -> todo.getId()== id;
        todos.removeIf(predicate);
    }

    public Todo findById(long id) {
        Predicate<? super Todo> predicate = todo -> todo.getId()== id;
        Todo todo = todos.stream().filter(predicate).findFirst().get();
        return todo;
    }

    public void updateTodo(@Valid Todo todo) {
        deleteById(todo.getId());
        todos.add(todo);
    }
}
