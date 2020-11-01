package models;

import java.lang.reflect.Array;
import java.util.Objects;

public class EmployeeResponse {
    String status;
    Employee data;
    String message;

    public EmployeeResponse(String status, Employee data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public EmployeeResponse() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeResponse response = (EmployeeResponse) o;
        return Objects.equals(status, response.status) &&
                data.equals(response.data) &&
                Objects.equals(message, response.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, data, message);
    }
}
