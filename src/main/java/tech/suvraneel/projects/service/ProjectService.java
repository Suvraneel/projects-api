package tech.suvraneel.projects.service;

import tech.suvraneel.projects.business.bean.ProjectBean;

import java.util.List;

public interface ProjectService {
    ProjectBean createProject(ProjectBean projectBean);

    List<ProjectBean> getAllProjects();

    ProjectBean getProjectById(String projectId);
}
