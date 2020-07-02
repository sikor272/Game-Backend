package pl.umk.mat.server.games;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pl.umk.mat.server.utils.enums.Type;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@ApiModel
public class NewGame {
    @NotNull
    @Size(min = 1, max = 200)
    @ApiModelProperty(notes = "It's exactly what you expect.", required = true)
    private String title;

    @NotNull
    @Size(min = 10, max = 200)
    @ApiModelProperty(notes = "It's exactly what you expect.", required = true)
    private String info;

    @NotNull
    @Size(min = 10)
    @ApiModelProperty(notes = "It's exactly what you expect.", required = true)
    private String description;

    @NotNull
    @ApiModelProperty(notes = "It's exactly what you expect.", required = true)
    private Type type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
