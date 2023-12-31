package tech.suvraneel.projects.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tech.suvraneel.projects.business.bean.ProjectBean;
import tech.suvraneel.projects.service.ProjectService;

@Controller
@RequestMapping(path = "/cms/projects")
public class ProjectCMSController {
    @Autowired
    ProjectService projectService;

    @RequestMapping(path = "/greeting", method = RequestMethod.GET, produces = "text/html")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping(path = "/")
    public ModelAndView index() {
        return new ModelAndView("index", "projects", projectService.getAllProjects());
    }

    @GetMapping(path = "/edit/{requestedId}")
    public ModelAndView showProjectEditForm(@PathVariable("requestedId") String projectId){
        return new ModelAndView("editProject", "project", projectService.getProjectById(projectId));
    }

    @PostMapping(path = "/edit/{requestedId}")
    public String saveProjectEditForm(@PathVariable("requestedId") String projectId, @ModelAttribute("projectBean") ProjectBean projectBean) {
        projectService.createProject(projectBean);
        return "redirect:/cms/projects/";
    }
}


