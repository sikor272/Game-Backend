package pl.umk.mat.server.comments;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.umk.mat.server.games.Game;
import pl.umk.mat.server.games.GameRepository;
import pl.umk.mat.server.users.User;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private GameRepository gameRepository;

    public CommentService(CommentRepository commentRepository, GameRepository gameRepository) {
        this.commentRepository = commentRepository;
        this.gameRepository = gameRepository;
    }

    @Transactional
    public void addComment(User user, NewComment newComment) {
        Game game = gameRepository.findByIdOrThrow(newComment.getGameId());
        Comment comment = new Comment(newComment, user, game);
        commentRepository.save(comment);
    }
}
