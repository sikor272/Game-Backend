package pl.umk.mat.server.users;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel
public class UpdateUser {
    @NotNull
    @Size(min = 1, max = 200)
    @ApiModelProperty(notes = "It's exactly what you expect.", required = true)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 200)
    @ApiModelProperty(notes = "It's exactly what you expect.", required = true)
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
