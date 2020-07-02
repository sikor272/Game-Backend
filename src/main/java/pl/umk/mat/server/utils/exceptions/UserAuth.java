package pl.umk.mat.server.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UserAuth extends RuntimeException {
    public UserAuth(String message) {
        super(message);
    }

    public UserAuth() {
        super();
    }
}
