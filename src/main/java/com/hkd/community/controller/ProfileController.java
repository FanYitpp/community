package com.hkd.community.controller;

import com.hkd.community.dto.PaginationDTO;
import com.hkd.community.model.User;
import com.hkd.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    QuestionService questionService;
    @GetMapping("/questions")
    public String profile(
            @RequestParam(name = "page", defaultValue = "1")Integer page,
            @RequestParam(name = "size", defaultValue = "5")Integer size,
            Model model, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if (user == null){
            return "redirect:/";
        }
        model.addAttribute("section","questions");
        model.addAttribute("sectionName","我的提问");
        PaginationDTO paginationDTO = questionService.getMyQuestion(user.getId(), page, size);
        model.addAttribute("paginationDTO",paginationDTO);
        return "profile";
    }
    @GetMapping("/replies")
    public String replies(Model model){
        model.addAttribute("section","replies");
        model.addAttribute("sectionName","我的回复");
        return "profile";
    }
}
