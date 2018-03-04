package ru.aparc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.aparc.facadeDao.PostFacadeDao;

@Controller
public class PostController {

    private final PostFacadeDao dao;

    @Autowired
    public PostController(PostFacadeDao dao) {
        this.dao = dao;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/postLists")
    public String getPostList(@RequestParam(name = "type") String type, @RequestParam(name = "price") String price, Model model) {
        String query = "from Post";
        if (type.length() != 0 && price.length() != 0) {
            query = query.concat(" where type = \'" + type + "\' and price <= \'" + price + "\'");
            model.addAttribute("listPosts", dao.getPostsByQuery(query));
        } else if (type.length() != 0 && price.length() == 0) {
            query = query.concat(" where type = \'" + type + "\'");
            model.addAttribute("listPosts", dao.getPostsByQuery(query));
        } else if (type.length() == 0 && price.length() != 0) {
            query = query.concat(" where price <= \'" + price + "\'");
            model.addAttribute("listPosts", dao.getPostsByQuery(query));
        } else {
            model.addAttribute("listPosts", dao.getAllPosts());
        }
        return "posts";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String searchForm(){
        return "search";
    }
}
