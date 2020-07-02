package pl.umk.mat.server.games;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.umk.mat.server.auth.utils.UserPrincipal;
import pl.umk.mat.server.users.UserDto;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/games")
@Api(description = "This controller provides logic for games.", tags = "Game Controller")
public class GameController {
    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get all games")
    public List<GameDto> getAllGames() {
        return gameService.findAllGames();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get Game by ID ")
    public GameDto getGameById(
            @PathVariable Long id
    ) {
        return gameService.findGameById(id);
    }

    @PatchMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Create new Game")
    @Authorization("Token")
    public GameDto addGame(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestParam MultipartFile file,
            @ModelAttribute @Valid NewGame newGame
    ) throws IOException {
        return gameService.addGame(userPrincipal.getUser(), file, newGame);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Update Game")
    @Authorization("Token")
    public GameDto addGame(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody @Valid NewGame newGame,
            @PathVariable Long id
    ) {
        return gameService.updateGame(userPrincipal.getUser(), id, newGame);
    }

    @PatchMapping("/{id}/image")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Update game picture.")
    @Authorization("Token")
    public GameDto setUserAvatar(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestParam MultipartFile file,
            @PathVariable Long id
    ) throws IOException {
        return gameService.updateImage(id, file);
    }
}
