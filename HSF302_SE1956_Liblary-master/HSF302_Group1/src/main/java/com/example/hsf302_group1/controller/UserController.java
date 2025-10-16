package com.example.hsf302_group1.controller;

import com.example.hsf302_group1.dto.UserDTO;
import com.example.hsf302_group1.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String rechargeMoney(HttpServletRequest request, Model model) {
        List<UserDTO> users = userService.getAllUsers();
        model.addAttribute("users", users);

        String uIdStr = request.getParameter("uId");
        String moneyStr = request.getParameter("money");
        if (uIdStr != null && !uIdStr.isEmpty()) {
            Integer uId = Integer.parseInt(uIdStr);
            Double money = Double.parseDouble(moneyStr);
            try {
                if(money>0){
                    List<UserDTO> updateList = userService.rechargeBalance(uId, money);
                    if(!updateList.isEmpty()){
                        UserDTO updateUser = updateList.get(0);
                        model.addAttribute("mess", "Đã thêm tiền vào tài khoản!");
                        model.addAttribute("updateUser", updateUser);
                    }
                    else model.addAttribute("mess","Không tìm thấy người dùng!");
                }
                else model.addAttribute("mess", "Vui lòng nhập số tiền tối thiểu là 1000đ!");
            } catch (NumberFormatException e) {
                model.addAttribute("mess", "Dữ liệu nhập vào không hợp lệ!");
            }
        }
        return "rechargeMoney";
    }
}
