package tech.suvraneel.projects;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
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
        ProjectBean projectBean = new ProjectBean("degen-splitwise", "Degen Splitwise", "Splitwise but for Web3.0", "https://github.com/expenze", "https://expenze.vercel.app", "http://youtube.com/ifhdih", "https://imgur.me/hgkuhg.png", new ArrayList<>(Arrays.asList("react native", "walletconnect", "ccip")), new Date());
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/projects", projectBean, Void.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        ResponseEntity<String> getResponse = restTemplate.getForEntity(responseEntity.getHeaders().getLocation(), String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
        assertThat(Optional.ofNullable(documentContext.read("$.projectName")).orElse(null)).isEqualTo("Degen Splitwise");
    }
}
