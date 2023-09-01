package com.example.userservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.userservice.exception.UsernameAlreadyExistsException;
import com.example.userservice.model.Project;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.serv.ProjectService;
import com.example.userservice.serv.UserServiceImpl;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUserLogin_Success() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password123");

        when(userRepository.findByUsername("testUser")).thenReturn(user);

        User result = userService.userLogin("testUser", "password123");

        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
    }


    @Test
    public void testSaveUser_Success() {
        User newUser = new User();
        newUser.setUsername("newUser");
        newUser.setPassword("password123");

        when(userRepository.save(any(User.class))).thenReturn(newUser);

        User result = userService.saveUser(newUser);

        assertNotNull(result);
        assertEquals("newUser", result.getUsername());
    }

    @Test
    public void testSaveUser_UsernameExists() {
        User newUser = new User();
        newUser.setUsername("existingUser");
        newUser.setPassword("password123");

        when(userRepository.save(any(User.class))).thenThrow(new UsernameAlreadyExistsException("Username 'existingUser' already exists!"));

        assertThrows(UsernameAlreadyExistsException.class, () -> userService.saveUser(newUser));
    }

    @Test
    public void testLoadUserByUsername() {
        User user = new User();
        user.setUsername("testUser");

        List<Project> projectList = new ArrayList<>();
        projectList.add(new Project());

        when(userRepository.findByUsername("testUser")).thenReturn(user);
        when(projectService.getAllProjects("testUser")).thenReturn(projectList);

        User result = userService.loadUserByUsername("testUser");

        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
        assertFalse(result.getProjects().isEmpty());
    }

    @Test
    public void testLoadUserById() {
        User user = new User();
        user.setUsername("testUser");

        List<Project> projectList = new ArrayList<>();
        projectList.add(new Project());

        when(userRepository.getById(anyLong())).thenReturn(user);
        when(projectService.getAllProjects("testUser")).thenReturn(projectList);

        User result = userService.loadUserById(1L);

        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
        assertFalse(result.getProjects().isEmpty());
    }

    @Test
    public void testGetAllUser() {
        List<User> userList = new ArrayList<>();
        userList.add(new User());

        when(userRepository.findAll()).thenReturn(userList);

        List<User> result = userService.getAllUser();

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testGetByPositon() {
        List<User> userList = new ArrayList<>();
        userList.add(new User());

        when(userRepository.getByPosition("2")).thenReturn(userList);

        List<User> result = userService.getByPositon();

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}
