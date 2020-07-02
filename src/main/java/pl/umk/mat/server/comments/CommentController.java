package pl.umk.mat.server.comments;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.umk.mat.server.auth.utils.UserPrincipal;

import javax.validation.Valid;

@RestController
@RequestMapping("/comments")
@Api(tags = "Comment Controller", description = "This controller provides logic for comments.")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Create new Comment")
    @Authorization("Token")
    public void addComment(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody @Valid NewComment newComment
    ) {
        commentService.addComment(userPrincipal.getUser(), newComment);
    }
}
