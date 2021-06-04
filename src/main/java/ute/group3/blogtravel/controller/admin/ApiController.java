package ute.group3.blogtravel.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ute.group3.blogtravel.dto.UserRequest;
import ute.group3.blogtravel.dto.UserRespone;
import ute.group3.blogtravel.dto.lstUserRespone;
import ute.group3.blogtravel.service.UserService;




@RestController
@RequestMapping(value = "/api-admin")
@AllArgsConstructor
public class ApiController {
    private final UserService userService;


    @RequestMapping(value = "/enabledaccount", method = RequestMethod.POST )
    public @ResponseBody
    UserRespone Post(@RequestBody UserRequest userRequest){
        lstUserRespone lstUser= new lstUserRespone();
        UserRespone userRespone=userService.EnableAccount(userRequest.getUserName(), userRequest.getEnable());
        return userRespone;
    }
}
