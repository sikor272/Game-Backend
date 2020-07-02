package pl.umk.mat.server.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceAlreadyExist extends RuntimeException {
    public ResourceAlreadyExist(String message) {
        super(message);
    }

    public ResourceAlreadyExist() {
        super();
    }
}
