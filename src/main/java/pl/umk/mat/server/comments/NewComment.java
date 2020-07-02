package pl.umk.mat.server.comments;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class NewComment {
    @ApiModelProperty(notes = "It's exactly what you expect.")
    private String description;

    @ApiModelProperty(notes = "It's exactly what you expect.")
    private Long gameId;

    public String getDescription() {
        return description;
    }

    public Long getGameId() {
        return gameId;
    }
}
