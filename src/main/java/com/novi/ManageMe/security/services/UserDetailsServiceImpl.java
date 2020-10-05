package com.novi.ManageMe.security.services;

import com.novi.ManageMe.models.user.User;
import com.novi.ManageMe.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service // declares class that performs service tasks
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional // defines the scope of a single database transaction
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // search user by username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // build UserDetails object
        return UserDetailsImpl.build(user);
    }

    // find user by email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // update user's password
    public void updatePassword(String password, Long userId) {
        userRepository.updatePassword(password, userId);
    }
}
