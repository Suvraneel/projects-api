package tech.suvraneel.projects;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import tech.suvraneel.projects.business.bean.ProjectBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProjectsApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void shouldReturnListOfAllExistingProjects(){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/projects", String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(responseEntity.getBody());
        System.out.println(responseEntity.getBody());
        assertThat(Optional.ofNullable(documentContext.read("$.length()")).orElse(0)).isEqualTo(3);
    }

    @Test
    void shouldReturnAProjectWhenProjectExists(){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/projects/supermeets", String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(responseEntity.getBody());
        assertThat(Optional.ofNullable(documentContext.read("$.projectName")).orElse(null)).isEqualTo("SuperMeets");
    }

    @Test
    void shouldNotReturnAProjectWhenItDoesNotExist(){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/projects/random-project", String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DirtiesContext
    void shouldCreateANewProject() {
        ProjectBean projectBean = new ProjectBean("degen-splitwise", "Degen Splitwise", "Splitwise but for Web3.0", "https://github.com/expenze", "https://expenze.vercel.app", "https://youtube.com/ifhdih", "https://imgur.me/hgkuhg.png", new ArrayList<>(Arrays.asList("react native", "walletconnect", "ccip")), new Date());
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/projects", projectBean, Void.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        ResponseEntity<String> getResponse = restTemplate.getForEntity(responseEntity.getHeaders().getLocation(), String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
        assertThat(Optional.ofNullable(documentContext.read("$.projectName")).orElse(null)).isEqualTo("Degen Splitwise");
    }

    @Test
    @DirtiesContext
    void shouldUpdateAnExistingProject() {
        ProjectBean projectBean = new ProjectBean("supermeets", "SuperMeets", "A meetup app for superheros", "https://github.com/supermeets", "https://supermeets.vercel.app", "https://youtube.com/supermeets", "https://imgur.me/supermeets.png", new ArrayList<>(Arrays.asList("react native", "walletconnect", "ccip")), new Date());
        HttpEntity<ProjectBean> httpEntity = new HttpEntity<>(projectBean);
        ResponseEntity<Void> responseEntity = restTemplate.exchange("/projects/supermeets", HttpMethod.PUT, httpEntity, Void.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        ResponseEntity<String> getResponse = restTemplate.getForEntity("/projects/supermeets", String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
        assertThat(Optional.ofNullable(documentContext.read("$.projectName")).orElse(null)).isEqualTo("SuperMeets");
    }

    @Test
    void shouldNotUpdateANonExistingProject() {
        ProjectBean projectBean = new ProjectBean("random-project", "Random Project", "A random project", "https://github.com/random-project", "https://random-project.vercel.app", "https://youtube.com/random-project", "https://imgur.me/random-project.png", new ArrayList<>(Arrays.asList("react native", "walletconnect", "ccip")), new Date());
        HttpEntity<ProjectBean> httpEntity = new HttpEntity<>(projectBean);
        ResponseEntity<Void> responseEntity = restTemplate.exchange("/projects/random-project", HttpMethod.PUT, httpEntity, Void.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DirtiesContext
    void shouldDeleteAnExistingProject() {
        ResponseEntity<Void> responseEntity = restTemplate.exchange("/projects/supermeets", HttpMethod.DELETE, null, Void.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        ResponseEntity<String> getResponse = restTemplate.getForEntity("/projects/supermeets", String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldNotDeleteANonExistingProject() {
        ResponseEntity<Void> responseEntity = restTemplate.exchange("/projects/random-project", HttpMethod.DELETE, null, Void.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
