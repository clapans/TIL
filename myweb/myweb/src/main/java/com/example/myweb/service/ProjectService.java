package com.example.myweb.service;

import com.example.myweb.dto.ProjectSummary;
import com.example.myweb.entity.Project;

import java.util.List;

public interface ProjectService {
    List<ProjectSummary> getSummaryList();
    Project getProject(Long id);
}
