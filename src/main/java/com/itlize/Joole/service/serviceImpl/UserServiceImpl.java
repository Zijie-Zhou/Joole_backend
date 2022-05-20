package com.itlize.Joole.service.serviceImpl;

import com.itlize.Joole.entity.User;
import com.itlize.Joole.repository.UserRepository;
import com.itlize.Joole.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByName(username).orElse(null);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean create(User user) {
        try{
            userRepository.save(user);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(User user) {
        if(user==null){
            return false;
        }
        userRepository.delete(user);
        return true;
    }

    @Override
    public boolean update(String userName, User user) {
        User toUpdate=userRepository.findByName(userName).orElse(null);
        if(toUpdate==null){
            return false;
        }
        toUpdate.setName(user.getName());
        try{
            userRepository.save(toUpdate);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public void clear() {
        userRepository.deleteAll();
    }
}
