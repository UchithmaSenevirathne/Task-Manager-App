package com.example.backend.util;

import com.example.backend.dtos.UserDTO;
import com.example.backend.entities.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {

    @Autowired
    private ModelMapper modelMapper;

    //user matters mapping
    public UserDTO convertToUserDTO(User userEntity) {
        return modelMapper.map(userEntity, UserDTO.class);
    }

    public User convertToUserEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    public List<UserDTO> convertToUserDTOList(List<User> userEntityList) {
        return modelMapper.map(userEntityList, new TypeToken<List<UserDTO>>() {}.getType());
    }
}
