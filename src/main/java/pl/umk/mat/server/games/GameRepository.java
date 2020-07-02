package pl.umk.mat.server.games;

import org.springframework.stereotype.Repository;
import pl.umk.mat.server.utils.BaseRepository;

@Repository
public interface GameRepository extends BaseRepository<Game, Long> {

}
