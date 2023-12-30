package tech.suvraneel.projects.business.bean;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

@Component
public class ProjectBean {
    String projectId;
    String projectName;
    String description;
    String githubURL;
    String deploymentURL;
    String demoURL;
    String thumbnailURL;
    ArrayList<String> techStack;
    @DateTimeFormat(pattern = "MM/YYYY")
    Date dateOfGenesis;

    public ProjectBean() {
    }

    public ProjectBean(String projectId, String projectName, String description, String githubURL, String deploymentURL, String demoURL, String thumbnailURL, ArrayList<String> techStack, Date dateOfGenesis) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.description = description;
        this.githubURL = githubURL;
        this.deploymentURL = deploymentURL;
        this.demoURL = demoURL;
        this.thumbnailURL = thumbnailURL;
        this.techStack = techStack;
        this.dateOfGenesis = dateOfGenesis;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGithubURL() {
        return githubURL;
    }

    public void setGithubURL(String githubURL) {
        this.githubURL = githubURL;
    }

    public String getDeploymentURL() {
        return deploymentURL;
    }

    public void setDeploymentURL(String deploymentURL) {
        this.deploymentURL = deploymentURL;
    }

    public String getDemoURL() {
        return demoURL;
    }

    public void setDemoURL(String demoURL) {
        this.demoURL = demoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public ArrayList<String> getTechStack() {
        return techStack;
    }

    public void setTechStack(ArrayList<String> techStack) {
        this.techStack = techStack;
    }

    public Date getDateOfGenesis() {
        return dateOfGenesis;
    }

    public void setDateOfGenesis(Date dateOfGenesis) {
        this.dateOfGenesis = dateOfGenesis;
    }

    @Override
    public String toString() {
        return "ProjectBean{" +
                "projectId='" + projectId + '\'' +
                ", projectName='" + projectName + '\'' +
                ", description='" + description + '\'' +
                ", githubURL='" + githubURL + '\'' +
                ", deploymentURL='" + deploymentURL + '\'' +
                ", demoURL='" + demoURL + '\'' +
                ", thumbnailURL='" + thumbnailURL + '\'' +
                ", techStack=" + techStack +
                ", dateOfGenesis=" + dateOfGenesis +
                '}';
    }
}
