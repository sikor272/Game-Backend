package pl.umk.mat.server.auth;

import org.springframework.stereotype.Repository;
import pl.umk.mat.server.utils.BaseRepository;

@Repository
public interface ApiLoginEntriesRepository extends BaseRepository<ApiLoginEntries, Long> {
    ApiLoginEntries findByToken(String token);
}
