package ute.group3.blogtravel.service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ute.group3.blogtravel.Repository.UserRepository;
import ute.group3.blogtravel.dto.UserDTO;
import ute.group3.blogtravel.dto.UserRespone;
import ute.group3.blogtravel.dto.lstUserRespone;
import ute.group3.blogtravel.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public UserDTO getUser(String user){
        User user1 = userRepository.findAllByUsername(user);
        if(!user1.getUsername().equals(user)) {
            return null;
        }
        return  MapUser(user1);
    }
    private UserDTO MapUser(User user){
        UserDTO userDTO=new UserDTO();
        userDTO.setAddress(user.getAddress());
        userDTO.setFullName(user.getFullName());
        userDTO.setDatetime(user.getDatetime());
        userDTO.setGender(user.getGender());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }

    public UserDTO UpdateUser(UserDTO userDTO, String username){
        User user = userRepository.findByUsername(username);
        user.setAddress(userDTO.getAddress());
        user.setFullName(userDTO.getFullName());
        user.setDatetime(userDTO.getDatetime());
        user.setGender(userDTO.getGender());
        user.setEmail(userDTO.getEmail());
        userRepository.save(user);
        return userDTO;
    }
    public UserRespone MapUserToUserRespone(User user){
        UserRespone userRespone= new UserRespone();
        userRespone.setUserName(user.getUsername());
        userRespone.setCreated(user.getCreated());
        userRespone.setEmail(user.getEmail());
        userRespone.setEnabled(user.getEnabled());
        return userRespone;
    }

    public UserRespone EnableAccount(String username, String enable){
        User user= userRepository.findByUsername(username);
        user.setEnabled(Boolean.parseBoolean(enable));
        userRepository.save(user);
        User userreturn= userRepository.findByUsername(username);
        UserRespone userRespone= MapUserToUserRespone(userreturn);
        return userRespone;
    }

    public lstUserRespone GetAllUser(){
        List<UserRespone> lstUserRespone= new ArrayList<>();
        List<User> lstUser= userRepository.findAll();
        for (User user: lstUser) {
            lstUserRespone.add(MapUserToUserRespone(user));
        }
        return new lstUserRespone(lstUserRespone);
    }
}
