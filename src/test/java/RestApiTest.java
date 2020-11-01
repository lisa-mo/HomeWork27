import models.*;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class RestApiTest {
    String mainUrl = "http://dummy.restapiexample.com/api/v1/";

    @Test
    public void getAllEmployeesTestPositive() {
        String endpoint = "employees";
        String url = mainUrl + endpoint;
        given()
                .when()
                .get(url)
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body("status", equalTo("success"))
                .body("data.id", hasItems("1", "2", "3"));

    }

    @Test
    public void getAllEmployeesTestNegative() {
        //try to get all employees data from wrong endpoint
        String endpoint = "employee";// correct is "employees"
        String url = mainUrl + endpoint;
        given()
                .when()
                .get(url)
                .then()
                .log().all()
                .statusCode(404)
                .assertThat()
                .body("message", equalTo("Error Occured! Page Not found, contact rstapi2example@gmail.com"));

    }

    @Test
    public void getEmployeeIdTestPositive() {
        String endpoint = "employee/";
        String id = "1";
        String url = mainUrl + endpoint + id;
        Employee expectedEmployee = new Employee("Tiger Nixon", 320800, 61, "");
        EmployeeResponse expectedResponse = new EmployeeResponse("success", expectedEmployee, "Successfully! Record has been fetched.");

        EmployeeResponse response = given()
                .when()
                .log().all()
                .get(url)
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .extract()
                .as(EmployeeResponse.class);

        assertEquals(response, expectedResponse);
    }

    @Test
    public void getEmployeeIdTestNegative() {
        //try to get employee data by wrong endpoint
        String endpoint = "employeee/";
        String id = "id";
        String url = mainUrl + endpoint + id;
        given()
                .when()
                .get(url)
                .then()
                .log().all()
                .statusCode(404)
                .assertThat()
                .body("message", equalTo("Error Occured! Page Not found, contact rstapi2example@gmail.com"));
    }

    @Test
    public void postEmployeeTestPositive() {
        String endpoint = "create";
        String url = mainUrl + endpoint;
        PostEmployee employee = new PostEmployee("Thomas", "675432", "43");
        EmployeeResponse expectedResponse = new EmployeeResponse("success", new Employee(), "Successfully! Record has been added.");
        EmployeeResponse response = given()
                .with()
                .body(employee)
                .post(url)
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .extract()
                .as(EmployeeResponse.class);

        assertEquals(response, expectedResponse);

    }

    @Test
    public void postEmployeeTestNegative() {
        //try wrong method
        String endpoint = "create";
        String url = mainUrl + endpoint;
        PostEmployee employee = new PostEmployee("Thomas", "675432", "43");

        given()
                .with()
                .body(employee)
                .put(url)
                .then()
                .log().all()
                .statusCode(405);
    }

    @Test
    public void putEmployeeTestPositive() {
        String endpoint = "update/";
        String id = "40";
        String url = mainUrl + endpoint + id;
        PostEmployee employee = new PostEmployee("Thomas", "675432", "43");
        PutEmployeeResponse expectedResponse = new PutEmployeeResponse("success", new ArrayList<>(), "Successfully! Record has been updated.");
        PutEmployeeResponse response = given()
                .with()
                .body(employee)
                .put(url)
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .extract()
                .as(PutEmployeeResponse.class);
        assertEquals(response, expectedResponse);

    }

    @Test
    public void putEmployeeTestNegative() {
        //try wrong method
        String endpoint = "update/";
        String id = "id";
        String url = mainUrl + endpoint + id;
        PostEmployee employee = new PostEmployee("Thomas", "675432", "43");

        given()
                .with()
                .body(employee)
                .post(url)
                .then()
                .log().all()
                .statusCode(405);
    }


    @Test
    public void deleteEmployeeTestPositive() {
        String endpoint = "delete/";
        String id = "40";
        String url = mainUrl + endpoint + id;
        DeleteEmployee expectedResponse = new DeleteEmployee("success", id, "Successfully! Record has been deleted");
        DeleteEmployee response = given()
                .with()
                .delete(url)
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .extract()
                .as(DeleteEmployee.class);
        assertEquals(response, expectedResponse);
    }

    @Test
    public void deleteEmployeeTestNegative() {
        //try wrong method
        String endpoint = "delete/";
        String id = "id";
        String url = mainUrl + endpoint + id;

        given()
                .when()
                .post(url)
                .then()
                .statusCode(405);

    }
}
