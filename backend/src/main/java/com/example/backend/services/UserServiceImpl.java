package com.example.backend.services;

import com.example.backend.dtos.UserDTO;
import com.example.backend.entities.User;
import com.example.backend.repositories.UserRepository;
import com.example.backend.util.Mapping;
import com.example.backend.util.VarList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

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
    public UserDTO loadUserDetailsByUsername(String username){
        User user = userRepository.findByUsername(username);
        return mapping.convertToUserDTO(user);
    }

    @Override
    public Long getUserIdByEmail(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return user.getId(); // Make sure return the correct field for the user ID
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(userEntity.getUsername(), userEntity.getPassword(), getAuthority(userEntity));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User userEntity) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(userEntity.getUsername()));
        return authorities;
    }
}
