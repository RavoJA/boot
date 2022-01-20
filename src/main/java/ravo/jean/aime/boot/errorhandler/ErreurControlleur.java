package ravo.jean.aime.boot.errorhandler;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@ControllerAdvice
public class ErreurControlleur {
    private final List<String> errors = new ArrayList<>();
    private final AppHandlingError error = new AppHandlingError();

    @ExceptionHandler({AppResponseEntityException.class, Exception.class})
    public ResponseEntity<AppHandlingError> handleResponseError(HttpServletRequest req, Exception ex) {
        errors.add(ex.getLocalizedMessage());
        AppHandlingError error = new AppHandlingError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), errors);
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<AppHandlingError> violationInegrity(DataIntegrityViolationException ex) {
        error.setMessage("This record already exist");
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        error.setListErrors(Collections.singletonList(ex.getLocalizedMessage()));
        return new ResponseEntity<>(error, error.getStatus());
    }

}




