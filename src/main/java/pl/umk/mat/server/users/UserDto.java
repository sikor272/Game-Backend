package pl.umk.mat.server.users;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserDto {
    @ApiModelProperty(notes = "It's exactly what you expect.")
    private Long id;

    @ApiModelProperty(notes = "It's exactly what you expect.")
    private String firstName;

    @ApiModelProperty(notes = "It's exactly what you expect.")
    private String lastName;

    @ApiModelProperty(notes = "It's exactly what you expect.")
    private String avatar;

    @ApiModelProperty(notes = "It's exactly what you expect.")
    private String email;

    public UserDto(Long id, String firstName, String lastName, String avatar, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
        this.email = email;
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.avatar = user.getAvatar();
        this.email = user.getEmail();
    }

    public Long getId() {
        return id;
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

    public String getEmail() {
        return email;
    }
}
