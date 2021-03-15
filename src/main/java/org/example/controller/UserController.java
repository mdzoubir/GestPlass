package org.example.controller;

import org.example.DAO.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @Autowired
    private UserDaoImpl userDao;

    @RequestMapping(value = "deleteUser",method = RequestMethod.POST)
    public String deleteUser(HttpServletRequest req,
                             HttpServletResponse resp){
        int id = Integer.parseInt(req.getParameter("id"));
        userDao.deleteUser(id);
        return "redirect:/Users";
    }
}
