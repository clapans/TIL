package com.example.myweb.controller;

import com.example.myweb.dto.NoProject;
import com.example.myweb.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RouteController {
    private final ProjectService projectService;
    @Autowired
    public RouteController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("")
    public String getMain(Model model) {
        model.addAttribute("projectList",projectService.getSummaryList());
        return "index";
    }

    @GetMapping("/project/{id}")
    public String getProject(@PathVariable("id") Long id, Model model) {
        model.addAttribute("project",projectService.getProject(id));
        return "project";
    }

    @GetMapping("project/able/{id}")
    public String getProjectAble(@PathVariable("id") Long id, Model model) {
        if (id != 0L)
            model.addAttribute("project", projectService.getProject(id));
        else
            model.addAttribute("project",new NoProject());
        model.addAttribute("id",id);
        return "projectEdit";
    }
}
