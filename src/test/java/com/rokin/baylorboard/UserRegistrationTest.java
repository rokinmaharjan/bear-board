package com.rokin.baylorboard;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.web.WebAppConfiguration;

import com.rokin.baylorboard.domain.Roles;
import com.rokin.baylorboard.domain.User;
import com.rokin.baylorboard.enums.Role;
import com.rokin.baylorboard.repository.UserRepository;
import com.rokin.baylorboard.service.UserService;

@SpringBootTest
@WebAppConfiguration
public class UserRegistrationTest {

    @Autowired
    private UserService userService;

//    @Autowired
//    private PasswordEncoder encoder;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void UserRegistrationTesting() {
        User user = new User();
        user.setEmailAddress("testUser7@gmail.com");
        user.setPassword("Pass11WW");
        user.setFirstName("Test");
        user.setLastName("User7");

        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user, userService.addUsers(user));
    }

//    @Test
//    public void LoginUserTesting() {
//        User user = new User();
//        user.setEmailAddress("testUser7@gmail.com");
//        user.setPassword("Pass11WW");
//
//        doReturn(user).when(userRepository).findByEmailAddress(user.getEmailAddress());
//
//        System.out.println(user);
//
//        User returnedUser = userService.loginUser(user);
//
//        Assertions.assertSame(user, returnedUser, "Same");
//    }
}
