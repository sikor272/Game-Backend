package pl.umk.mat.server.games;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pl.umk.mat.server.comments.CommentDto;
import pl.umk.mat.server.users.UserDto;
import pl.umk.mat.server.utils.enums.Type;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@ApiModel
public class GameDto {
    @ApiModelProperty(notes = "It's exactly what you expect.")
    private Long id;

    @ApiModelProperty(notes = "It's exactly what you expect.")
    private String title;

    @ApiModelProperty(notes = "It's exactly what you expect.")
    private String info;

    @ApiModelProperty(notes = "It's exactly what you expect.")
    private String description;

    @ApiModelProperty(notes = "It's exactly what you expect.")
    private Type type;

    @ApiModelProperty(notes = "It's exactly what you expect.")
    private Instant createdAt;

    @ApiModelProperty(notes = "It's exactly what you expect.")
    private String image;

    @ApiModelProperty(notes = "It's exactly what you expect.")
    private List<CommentDto> comments;

    @ApiModelProperty(notes = "It's exactly what you expect.")
    private UserDto author;

    public GameDto(Game game) {
        this.id = game.getId();
        this.title = game.getTitle();
        this.info = game.getInfo();
        this.description = game.getDescription();
        this.type = game.getType();
        this.createdAt = game.getCreatedAt();
        this.image = game.getImage();
        this.comments = game.getComments().stream().map(CommentDto::new).collect(Collectors.toList());
        this.author = new UserDto(game.getUser());
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }

    public String getDescription() {
        return description;
    }

    public Type getType() {
        return type;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getImage() {
        return image;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public UserDto getAuthor() {
        return author;
    }
}
