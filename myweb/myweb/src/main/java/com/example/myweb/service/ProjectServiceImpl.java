package com.example.myweb.service;

import com.example.myweb.dto.ProjectSummary;
import com.example.myweb.entity.BaseEntity;
import com.example.myweb.entity.Project;
import com.example.myweb.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectSummary> getSummaryList() {
        return projectRepository.findAll().stream().map(project -> {
            return new ProjectSummary(project.getId(), project.getTitle(), project.getImage());
        }).collect(Collectors.toList());
    }

    public Project getProject(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("id에 해당하는 프로젝트 없음");
        });
    }
}
