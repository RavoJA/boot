package ravo.jean.aime.boot.errorhandler;

import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class AppHandlingError {
    private HttpStatus status;
    private String message;
    private List<String> listErrors;

    public AppHandlingError(HttpStatus status, String message, List<String> listErrors) {
        this.status = status;
        this.message = message;
        this.listErrors = listErrors;
    }

    public AppHandlingError(HttpStatus status, String message, String error) {
        this.status = status;
        this.message = message;
        this.listErrors = Collections.singletonList(error);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getListErrors() {
        return listErrors;
    }

    public void setListErrors(List<String> listErrors) {
        this.listErrors = listErrors;
    }

    public AppHandlingError() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppHandlingError that = (AppHandlingError) o;
        return status == that.status && Objects.equals(message, that.message) && Objects.equals(listErrors, that.listErrors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, message, listErrors);
    }
}
