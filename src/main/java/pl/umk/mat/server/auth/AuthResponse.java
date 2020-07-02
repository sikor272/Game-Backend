package pl.umk.mat.server.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pl.umk.mat.server.users.User;
import pl.umk.mat.server.utils.enums.Role;

@ApiModel
public class AuthResponse {
    @ApiModelProperty(notes = "It's exactly what you expect.")
    private Long id;

    @ApiModelProperty(notes = "It's exactly what you expect.")
    private String token;

    @ApiModelProperty(notes = "It's exactly what you expect.")
    private String email;

    @ApiModelProperty(notes = "It's exactly what you expect.")
    private String firstName;

    @ApiModelProperty(notes = "It's exactly what you expect.")
    private String lastName;

    @ApiModelProperty(notes = "It's exactly what you expect.")
    private String avatar;

    @ApiModelProperty(notes = "It's exactly what you expect.")
    private Role role;

    public AuthResponse(User user, String token) {
        this.id = user.getId();
        this.token = token;
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.avatar = user.getAvatar();
        this.role = user.getRole();
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAvatar() {
        return avatar;
    }

    public Role getRole() {
        return role;
    }
}
