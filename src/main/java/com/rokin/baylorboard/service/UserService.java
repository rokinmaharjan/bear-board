package com.rokin.baylorboard.service;

import com.rokin.baylorboard.domain.Event;
import com.rokin.baylorboard.domain.Roles;
import com.rokin.baylorboard.enums.Role;
import com.rokin.baylorboard.exceptions.BadRequestException;
import com.rokin.baylorboard.repository.EventRepository;
import com.rokin.baylorboard.repository.RoleRepository;
import com.rokin.baylorboard.repository.UserRepository;
import com.rokin.baylorboard.utils.CustomBeanUtils;
import com.rokin.baylorboard.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rokin.baylorboard.domain.User;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
//
//    @Autowired
//    private PasswordEncoder encoder;

    public User addUsers(User user) {
        String password = user.getPassword();
        if (password.isEmpty()) {
            throw new BadRequestException("Invalid password.");
        }
        String encodedPassword = password;
        String salt = PasswordUtils.getSalt(30);

        // Protect user's password. The generated value can be stored in DB.
        String mySecurePassword = PasswordUtils.generateSecurePassword(password, salt);

        user.setPassword(mySecurePassword);
        user.setSalt(salt);

        User userExists = userRepository.findByEmailAddress(user.getEmailAddress());



        if (userExists != null) {
            throw new BadRequestException(user.getEmailAddress() + " already registered.");
        }
        user.setRole(Role.PROFESSOR);
        return userRepository.save(user);
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public User updateUser(String id, User updatedUser) {
        User user = userRepository.findById(id).get();
        CustomBeanUtils.copyNonNullProperties(updatedUser, user);
        return userRepository.save(user);
    }

    public User findById(String id) {
        return userRepository.findById(id).get();
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public User loginUser(User user) {

        User userExists = userRepository.findByEmailAddress(user.getEmailAddress());

        if (userExists == null) {
            throw new BadRequestException("Invalid email");
        }

        String password = user.getPassword();
        String salt = userExists.getSalt();
        String encryptedPassword = userExists.getPassword();

        boolean passwordMatch = PasswordUtils.
                verifyUserPassword(password, encryptedPassword, salt);

        if (!passwordMatch) {
            throw new BadRequestException("Invalid password.");
        }
        return userExists;
    }

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepository.findByEmailAddress(email);
//
//        if(user != null) {
//            List<GrantedAuthority> authorities = getUserAuthority(user.getRole());
//            return buildUserForAuthentication(user, authorities);
//        } else {
//            throw new UsernameNotFoundException("username not found");
//        }
//    }
//
//    private List<GrantedAuthority> getUserAuthority(Set<Roles> userRoles) {
//        Set<GrantedAuthority> roles = new HashSet<>();
//        userRoles.forEach((role) -> {
//            roles.add(new SimpleGrantedAuthority(role.getName().toString()));
//        });
//
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
//        return grantedAuthorities;
//    }
//
//    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
//        return new org.springframework.security.core.userdetails.User(user.getEmailAddress(), user.getPassword(), authorities);
//    }
}
