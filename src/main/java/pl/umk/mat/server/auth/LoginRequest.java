package pl.umk.mat.server.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel
public class LoginRequest {
    @NotNull
    @Email
    @ApiModelProperty(notes = "It's exactly what you expect.", required = true)
    private String email;

    @NotNull
    @Size(min = 8, max = 32)
    @ApiModelProperty(notes = "It's exactly what you expect.", required = true)
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
