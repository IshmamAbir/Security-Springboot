package com.example.security.init;

import com.example.security.Entity.Role;
import com.example.security.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitialiseData  implements CommandLineRunner {
    @Autowired
    RoleRepository repo;

    @Override
    public void run(String... args) throws Exception {

        /*Role role1=new Role();
        role1.setAuthority("Student");
        repo.save(role1);

        Role role2=new Role();
        role2.setAuthority("Teacher");
        repo.save(role2);

        Role role3=new Role();
        role3.setAuthority("Admin");
        repo.save(role3);*/

    }
}
