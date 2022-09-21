package com.example.todo;

import com.example.todo.domain.Todo;
import com.example.todo.repositories.TodoRepo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



import java.util.Date;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TodoApplicationTests {

    @Autowired
    TodoRepo todoRepo;

    @BeforeEach
    private void createTodo() {
        Todo todo = new Todo();
        todo.setUsername("test");
        todo.setId(1L);
        todo.setTargetDate(new Date());
        todo.setDone(false);
        todoRepo.save(todo);
    }

    @Test
    @Order(1)
    public void addTodo() {
       // createTodo();
        assertNotNull(todoRepo.findById(1L).get());
    }

    @Test
    @Order(2)
    public void getALlTodo() {
       // createTodo();
        List<Todo> list = todoRepo.findAll();
        assertThat(list).size().isGreaterThan(0);
    }


    @Test
    @Order(3)
    public void update() {
       // createTodo();
        Todo todo1 = todoRepo.findById(1L).get();
        todo1.setDone(true);
        todoRepo.save(todo1);
        assertNotEquals(false, todoRepo.findById(1L).get().isDone());
    }

    @Test
    @Order(4)
    public void delete() {
        //createTodo();
        todoRepo.deleteById(1L);
        assertThat(todoRepo.existsById(1L)).isFalse();
    }



}
