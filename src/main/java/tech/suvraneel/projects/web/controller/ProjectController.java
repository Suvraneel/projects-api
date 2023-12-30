package tech.suvraneel.projects.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import tech.suvraneel.projects.business.bean.ProjectBean;
import tech.suvraneel.projects.service.ProjectService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectBean>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping(path = "/{requestedId}")
    public ResponseEntity<ProjectBean> getProjectById(@PathVariable("requestedId") String projectId) {
        ProjectBean projectBean = projectService.getProjectById(projectId);
        return projectBean!=null?ResponseEntity.ok(projectBean):ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> createProject(@RequestBody ProjectBean projectBean, UriComponentsBuilder uriComponentsBuilder) {
        projectBean = projectService.createProject(projectBean);
        System.out.println(projectBean);
        URI uri = uriComponentsBuilder.path("/projects/{requestedId}").buildAndExpand(projectBean.getProjectId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
