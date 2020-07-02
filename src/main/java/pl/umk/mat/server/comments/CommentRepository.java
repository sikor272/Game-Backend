package pl.umk.mat.server.comments;

import org.springframework.stereotype.Repository;
import pl.umk.mat.server.utils.BaseRepository;

@Repository
public interface CommentRepository extends BaseRepository<Comment, Long> {
}
