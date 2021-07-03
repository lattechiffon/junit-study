package com.lattechiffon.junit.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    public void saveNewUser() {
        // given
        User user = User.builder().idx(1)
                .username("lattechiffon")
                .email("lattechiffon@gmail.com")
                .roles(Arrays.asList("USER", "ADMIN")).build();

        when(userRepository.save(any())).thenReturn(user);

        // when
        User result = userService.createUser(User.builder().username("lattechiffon").build());

        // then
        verify(userRepository, times(1)).save(any());
        assertThat(result, equalTo(user));
    }

    @Test
    public void findUser() {
        // given
        User user = User.builder().idx(1)
                .username("lattechiffon")
                .email("lattechiffon@gmail.com")
                .roles(Arrays.asList("USER", "ADMIN")).build();

        when(userRepository.findByUsername("lattechiffon")).thenReturn(user);

        // when
        User result = userService.findUser("lattechiffon");

        // then
        verify(userRepository, times(1)).findByUsername(any());
        assertThat(result, equalTo(user));
    }

    @Test
    public void findAllUsers() {
        // given
        List<User> users = Arrays.asList(User.builder().idx(1).username("lattechiffon").build(),
                User.builder().idx(2).username("yu-gotcha").build());

        when(userRepository.findAll()).thenReturn(users);

        // when
        List<User> result = userService.findAllUsers();

        // then
        verify(userRepository, times(1)).findAll();
        assertThat(result, equalTo(users));
    }
}