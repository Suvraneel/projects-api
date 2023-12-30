package tech.suvraneel.projects.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.suvraneel.projects.business.bean.ProjectBean;
import tech.suvraneel.projects.dao.ProjectDAO;
import tech.suvraneel.projects.entity.ProjectEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectDAO projectDAO;

    public ProjectBean createProject(ProjectBean projectBean) {
        ProjectEntity projectEntity = convertProjectBeanToEntity(projectBean);
        projectEntity = projectDAO.save(projectEntity);
        return convertProjectEntityToBean(projectEntity);
    }

    public List<ProjectBean> getAllProjects() {
        List<ProjectBean> projectBeans = new ArrayList<>();
        List<ProjectEntity> projectEntities = (List<ProjectEntity>) projectDAO.findAll();
        for (ProjectEntity projectEntity : projectEntities) {
            projectBeans.add(convertProjectEntityToBean(projectEntity));
        }
        return projectBeans;
    }

    public ProjectBean getProjectById(String projectId) {
        Optional<ProjectEntity> projectEntity = projectDAO.findById(projectId);
        return projectEntity.map(this::convertProjectEntityToBean).orElse(null);
    }

    public ProjectBean updateProjectById(String projectId, ProjectBean projectBean) {
        Optional<ProjectEntity> projectEntity = projectDAO.findById(projectId);
        if (projectEntity.isPresent()) {
            ProjectEntity projectEntityUpdated = projectEntity.get();
            BeanUtils.copyProperties(projectBean, projectEntityUpdated);
            projectEntityUpdated = projectDAO.save(projectEntityUpdated);
            return convertProjectEntityToBean(projectEntityUpdated);
        }
        return null;
    }

    public ProjectBean deleteProjectById(String projectId) {
        Optional<ProjectEntity> projectEntity = projectDAO.findById(projectId);
        projectEntity.ifPresent(projectDAO::delete);
        return projectEntity.map(this::convertProjectEntityToBean).orElse(null);
    }

    //     Utility Methods
    ProjectBean convertProjectEntityToBean(ProjectEntity projectEntity) {
        ProjectBean projectBean = new ProjectBean();
        BeanUtils.copyProperties(projectEntity, projectBean);
        return projectBean;
    }

    ProjectEntity convertProjectBeanToEntity(ProjectBean projectBean) {
        ProjectEntity projectEntity = new ProjectEntity();
        BeanUtils.copyProperties(projectBean, projectEntity);
        return projectEntity;
    }
}
