package ute.group3.blogtravel.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ute.group3.blogtravel.dto.lstUserRespone;
import ute.group3.blogtravel.service.UserService;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class ManagerUserController {
    private final UserService userService;

    @RequestMapping(value = "/manageruser", method = RequestMethod.GET)
    public String GetAllUser(Model model){
        lstUserRespone lstUserRespone = userService.GetAllUser();
        model.addAttribute("lstUserRespone", lstUserRespone);
        return "pages/admin/ManagerUser";
    }
}
