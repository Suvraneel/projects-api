package tech.suvraneel.projects.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "projects")
public class ProjectEntity {
    @Id
    @Column(name = "id")
    String projectId;
    @Column(name = "title", unique = true, nullable = false)
    String projectName;
    @Column(name = "description")
    String description;
    @Column(name = "github_url")
    String githubURL;
    @Column(name = "deployment_url")
    String deploymentURL;
    @Column(name = "demo_url")
    String demoURL;
    @Column(name = "thumbnail_url")
    String thumbnailURL;
    @Column(name = "tech_stack")
    ArrayList<String> techStack;
    @Column(name = "date_of_genesis")
    @Temporal(TemporalType.DATE)
    Date dateOfGenesis;

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
        return "ProjectEntity{" +
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
