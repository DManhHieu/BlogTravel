package ute.group3.blogtravel.service;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import ute.group3.blogtravel.Repository.UserRepository;
import ute.group3.blogtravel.dto.UserDTO;
import ute.group3.blogtravel.model.User;

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
}
