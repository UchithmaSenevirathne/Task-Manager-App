package com.example.backend.services.impl;

import com.example.backend.dtos.UserDTO;
import com.example.backend.entities.User;
import com.example.backend.repositories.UserRepository;
import com.example.backend.services.UserService;
import com.example.backend.util.Mapping;
import com.example.backend.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Mapping mapping;

    @Override
    public int saveUser(UserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            return VarList.Not_Acceptable;
        }else{
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userRepository.save(mapping.convertToUserEntity(userDTO));
            return VarList.Created;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        User userEntity = userRepository.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(userEntity.getUsername(), userEntity.getPassword(), getAuthority(userEntity));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getUsername()));
        return authorities;
    }
}
