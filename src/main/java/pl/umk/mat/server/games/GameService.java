package pl.umk.mat.server.games;

import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.umk.mat.server.config.Config;
import pl.umk.mat.server.users.User;
import pl.umk.mat.server.utils.exceptions.BadRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class GameService {
    private Config config;
    private GameRepository gameRepository;

    public GameService(Config config, GameRepository gameRepository) {
        this.gameRepository = gameRepository;
        this.config = config;
    }

    public List<GameDto> findAllGames() {
        return gameRepository.findAllList().stream().map(GameDto::new).collect(Collectors.toList());
    }

    public GameDto findGameById(Long id) {
        return new GameDto(gameRepository.findByIdOrThrow(id));
    }

    @Transactional
    public GameDto addGame(User user, MultipartFile file, NewGame newGame) throws IOException {
        String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
        if (fileExtension.equals(file.getOriginalFilename()))
            throw new BadRequest("Incorrect file extension.");
        if (!Pattern.compile(config.getImageRegex()).matcher(fileExtension).find())
            throw new BadRequest("Incorrect file type (only jpg, jpeg supported).");
        String filename = "game_" + RandomString.make(20) + "." + fileExtension;
        String filePath = config.getImageDir() + filename;
        if (new File(filePath).exists())
            throw new RuntimeException("File exist try later.");
        Files.copy(
                file.getInputStream(),
                Path.of(filePath),
                StandardCopyOption.REPLACE_EXISTING
        );
        Game game = new Game(newGame, filename, user);
        gameRepository.save(game);
        return new GameDto(game);
    }

    @Transactional
    public GameDto updateGame(User user, Long id, NewGame newGame) {
        Game game = gameRepository.findByIdOrThrow(id);
        game.update(newGame, user);
        gameRepository.save(game);
        return new GameDto(game);
    }

    public GameDto updateImage(Long id, MultipartFile file) throws IOException {
        String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
        if (fileExtension.equals(file.getOriginalFilename()))
            throw new BadRequest("Incorrect file extension.");
        if (!Pattern.compile(config.getImageRegex()).matcher(fileExtension).find())
            throw new BadRequest("Incorrect file type (only jpg, jpeg supported).");
        String filename = "game_" + RandomString.make(20) + "." + fileExtension;
        String filePath = config.getImageDir() + filename;
        if (new File(filePath).exists())
            throw new RuntimeException("File exist try later.");
        Files.copy(
                file.getInputStream(),
                Path.of(filePath),
                StandardCopyOption.REPLACE_EXISTING
        );
        Game game = gameRepository.findByIdOrThrow(id);
        game.setImage(filename);
        gameRepository.save(game);
        return new GameDto(game);
    }
}
