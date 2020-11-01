package models;

import java.util.Objects;

public class DeleteEmployee {
    String status;
    String data;
    String message;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteEmployee that = (DeleteEmployee) o;
        return Objects.equals(status, that.status) &&
                Objects.equals(data, that.data) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, data, message);
    }

    public DeleteEmployee(String status, String data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }
}
