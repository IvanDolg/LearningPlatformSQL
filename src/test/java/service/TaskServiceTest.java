package service;

import com.example.learningplatformsql.entity.Task;
import com.example.learningplatformsql.repository.TaskRepository;
import com.example.learningplatformsql.service.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    public void testGetAllTasks() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "task1", "disc1", "query1"));
        tasks.add(new Task(2L, "task2", "disc2", "query2"));

        when(taskRepository.findAll()).thenReturn(tasks);
        List<Task> result = taskService.getAllTasks();

        assertEquals(2, result.size());
    }

    @Test
    public void testGetTaskById() {
        Task task = new Task(1L, "task1", "disc1", "query1");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Optional<Task> result = taskService.getTaskById(1L);

        assertEquals(task.getId(), result.get().getId());
        assertEquals(task.getTitle(), result.get().getTitle());
    }

    @Test
    public void testCreateTask() {
        Task task = new Task(1L, "task1", "disc1", "query1");

        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.createTask(task);

        assertEquals(task.getTitle(), result.getTitle());
    }

    @Test
    public void testUpdateTask() {
        Task task = new Task(1L, "task1", "disc1", "query1");
        task.setId(1L);

        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.updateTask(task);

        assertEquals(task.getId(), result.getId());
        assertEquals(task.getTitle(), result.getTitle());
    }

    @Test
    public void testDeleteTask() {
        Long id = 1L;

        taskService.deleteTask(id);

        taskRepository.deleteById(id);
    }
}
