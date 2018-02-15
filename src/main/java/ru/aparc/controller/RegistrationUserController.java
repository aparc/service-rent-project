package ru.aparc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.aparc.domain.User;
import ru.aparc.facadeDao.UserFacadeDao;

@Controller
public class RegistrationUserController {

    private final UserFacadeDao dao;

    @Autowired
    public RegistrationUserController(UserFacadeDao dao) {
        this.dao = dao;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addUser")
    public String addUser(@ModelAttribute("user") User user) {
        if(user.getUserId() == 0) {
            dao.createUser(user);
        } else {
            dao.updateUser(user);
        }
        return "new page";
    }
}
