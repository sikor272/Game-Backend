package pl.umk.mat.server.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel
public class RegisterRequest {
    @NotNull
    @Email
    @ApiModelProperty(notes = "It's exactly what you expect.", required = true)
    private String email;

    @NotNull
    @Size(min = 8, max = 32)
    @ApiModelProperty(notes = "It's exactly what you expect.", required = true)
    private String password;

    @NotNull
    @Size(min = 1, max = 200)
    @ApiModelProperty(notes = "It's exactly what you expect.", required = true)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 200)
    @ApiModelProperty(notes = "It's exactly what you expect.", required = true)
    private String lastName;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


}
