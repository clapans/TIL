package com.example.myweb.controller;

import com.example.myweb.dto.ProjectPostReq;
import com.example.myweb.entity.Environment;
import com.example.myweb.entity.Project;
import com.example.myweb.repository.EnvironmentRepository;
import com.example.myweb.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProjectController {
    private final ProjectRepository projectRepository;
    private final EnvironmentRepository environmentRepository;

    @Autowired
    public ProjectController(ProjectRepository projectRepository, EnvironmentRepository environmentRepository) {
        this.projectRepository = projectRepository;
        this.environmentRepository = environmentRepository;
    }


    @PostMapping("/project")
    public void regProject(@RequestBody ProjectPostReq projectPostReq) {
        Project project = new Project(projectPostReq.getTitle(),projectPostReq.getPeriod(),
                projectPostReq.getPersonnel(),projectPostReq.getIntroduce(),projectPostReq.getRole(),
                projectPostReq.getProcess(),projectPostReq.getMainFunction(),projectPostReq.getLearned()
        );
        projectRepository.save(project);
        projectPostReq.getEnvironment().stream().map(environment -> {
            return new Environment(environment, project);
        }).forEach(environmentRepository::save);
    }

    @GetMapping("/api/project/{id}")
    public Project getProject(@PathVariable("id") Long id) {
        return projectRepository.findById(id).orElseThrow(() -> {return new IllegalArgumentException("wow");});
    }

    /*
    @PutMapping("/project/{id}")
    public void putProject(@RequestBody ProjectPostReq projectPostReq, @PathVariable("id") Long id) {
        List<Environment> environments = projectPostReq.getEnvironment().stream().map(Environment::new).collect(Collectors.toList());
        Project project = projectRepository.findById(id).orElseThrow(() -> {return new IllegalArgumentException("wow");});
        project.setEnvironment(environments);
        project.setIntroduce(projectPostReq.getIntroduce());
        project.setTitle(projectPostReq.getTitle());
        project.setPeriod(projectPostReq.getPeriod());
        project.setPersonnel(projectPostReq.getPersonnel());
        project.setLearned(projectPostReq.getLearned());
        project.setRole(projectPostReq.getRole());
        project.setProcess(projectPostReq.getProcess());
        project.setMainFunction(projectPostReq.getMainFunction());
        projectRepository.save(project);
    }*/
}

