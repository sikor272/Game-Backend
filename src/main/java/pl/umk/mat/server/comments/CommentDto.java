package pl.umk.mat.server.comments;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pl.umk.mat.server.users.UserDto;

import java.time.Instant;

@ApiModel
public class CommentDto {
    @ApiModelProperty(notes = "It's exactly what you expect.")
    private Long id;

    @ApiModelProperty(notes = "It's exactly what you expect.")
    private String description;

    @ApiModelProperty(notes = "It's exactly what you expect.")
    private Instant createdAt;

    @ApiModelProperty(notes = "It's exactly what you expect.")
    private UserDto user;

    public CommentDto(Comment it) {
        this.id = it.getId();
        this.description = it.getDescription();
        this.createdAt = it.getCreatedAt();
        this.user = new UserDto(it.getUser());
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public UserDto getUser() {
        return user;
    }
}
