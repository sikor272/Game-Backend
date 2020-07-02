package pl.umk.mat.server.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TokenExpired extends RuntimeException {
    public TokenExpired(String msg) {
        super(msg);
    }

    public TokenExpired() {
        super();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
