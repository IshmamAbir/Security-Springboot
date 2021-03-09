package com.example.security.service;

import com.example.security.Entity.Role;
import com.example.security.Entity.User;
import com.example.security.repository.RoleRepository;
import com.example.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    public void saveUser(User user){
        /*Optional<Role> optionalRole=roleRepo.findById(1L);
        Role role=optionalRole.get();
        List<Role> roles =new ArrayList<>();
        roles.add(role);*/

        List<Role> roles=roleRepo.findAll();
        user.setRoleList(roles);
        user.setPassword(customPasswordEncoder().encode(user.getPassword()));
        userRepo.save(user);

    }

    public PasswordEncoder customPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         User user= userRepo.findByUsername(username);
        return user;
    }
}
