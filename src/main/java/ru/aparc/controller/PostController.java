package ru.aparc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.aparc.facadeDao.PostFacadeDao;

@Controller
public class PostController {

    private final PostFacadeDao dao;

    @Autowired
    public PostController(PostFacadeDao dao) {
        this.dao = dao;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/postLists")
    public String getPostList(Model model) {
        model.addAttribute("listPosts", dao.getAllPosts());
        return "posts";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String searchForm(){
        return "search";
    }
}
