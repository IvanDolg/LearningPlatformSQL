package controller;

import com.example.learningplatformsql.controller.TaskController;
import com.example.learningplatformsql.entity.Task;
import com.example.learningplatformsql.service.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskControllerTest {
    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskService taskService;

    @Test
    public void testGetAllTasks() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "task1", "disc1", "query1"));
        tasks.add(new Task(2L, "task2", "disc2", "query2"));

        when(taskService.getAllTasks()).thenReturn(tasks);

        ResponseEntity<List<Task>> responseEntity = taskController.getAllTasks();
        assertNotNull(responseEntity.getBody());
        assertEquals(2, responseEntity.getBody().size());
    }

    @Test
    public void testGetTaskById() {
        Long id = 1L;
        Task task = new Task(id, "task1", "disc1", "query1");

        when(taskService.getTaskById(id)).thenReturn(Optional.of(task));

        ResponseEntity<Task> responseEntity = taskController.getTaskById(id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(task, responseEntity.getBody());
    }

    @Test
    public void testCreateTask() {
        Task task = new Task(1L, "task1", "disc1", "query1");

        when(taskService.createTask(task)).thenReturn(task);

        ResponseEntity<Task> responseEntity = taskController.createTask(task);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(task, responseEntity.getBody());
    }

    @Test
    public void testUpdateTask() {
        Long id = 1L;
        Task task = new Task(id, "task1", "disc1", "query1");

        when(taskService.getTaskById(id)).thenReturn(Optional.of(new Task(id, "task1", "disc1", "query1")));
        when(taskService.updateTask(task)).thenReturn(new Task(id, "updatedTask1", "updatedDisc1", "updatedQuery1"));

        ResponseEntity<Task> responseEntity = taskController.updateTask(id, task);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).usingRecursiveComparison()
                .isEqualTo(new Task(id, "updatedTask1", "updatedDisc1", "updatedQuery1"));
    }

    @Test
    public void testDeleteTask() {
        Long id = 1L;

        when(taskService.getTaskById(id)).thenReturn(Optional.of(new Task(id, "task1", "disc1", "query1")));

        ResponseEntity<Void> responseEntity = taskController.deleteTask(id);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}
