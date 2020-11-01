package models;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Objects;

public class PutEmployeeResponse {
    String status;
    List<String> data ;
    String message;

    public PutEmployeeResponse(String status,List<String> data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PutEmployeeResponse that = (PutEmployeeResponse) o;
        return Objects.equals(status, that.status) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, message);
    }
}
