package pl.umk.mat.server.users;

import org.springframework.stereotype.Repository;
import pl.umk.mat.server.utils.BaseRepository;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
    User findUserByEmail(String Email);

    Boolean existsUserByEmail(String Email);
}
