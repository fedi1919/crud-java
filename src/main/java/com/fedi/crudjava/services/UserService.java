package com.fedi.crudjava.services;
import com.fedi.crudjava.entities.Role;
import com.fedi.crudjava.entities.User;
import com.fedi.crudjava.repositories.RoleRepository;
import com.fedi.crudjava.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import
        org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.HashSet;
@Service("userService")
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;  // classe de cryptage

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder
                               bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserService(){}

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveUser(User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()))
        ;
        user.setActive(0);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);

    }
}
