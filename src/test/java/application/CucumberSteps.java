package application;

import application.DemoApplication;
import application.logic.EndPoints;
import application.model.entity.User;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;
import static org.springframework.util.Assert.noNullElements;

@ContextConfiguration
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberSteps {

    @Autowired
    private TestRestTemplate testRestTemplate;
    private ResponseEntity<List<User>> responseEntity;

    @When("^the client calls /users$")
    public void the_client_issues_GET_users() throws Throwable {
        responseEntity = testRestTemplate
                .exchange(
                        EndPoints.USERS,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<User>>() {}
                );

    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        assertThat(
                responseEntity.getStatusCodeValue()
        ).isEqualTo(statusCode);
    }

    @And("^the client receives users list$")
    public void the_client_receives_users_list() throws Throwable {
        List<User> userList = responseEntity.getBody();
        assertNotNull(userList);
        noNullElements(userList.toArray());
    }

}
