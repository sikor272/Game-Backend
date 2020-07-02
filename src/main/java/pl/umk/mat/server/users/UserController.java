package pl.umk.mat.server.users;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.umk.mat.server.auth.utils.UserPrincipal;

import java.io.IOException;

@RestController
@RequestMapping("/users")
@Api(description = "This controller provides logic for users.", tags = "User Controller")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get self info")
    @Authorization("Token")
    public UserDto getSelfInfo(
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return userService.getSelfInfo(userPrincipal);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get User by ID ")
    @Authorization("Token")
    public UserDto getUserById(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable Long id
    ) {
        return userService.findUserById(id);
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Update user")
    @Authorization("Token")
    public UserDto updateUser(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody UpdateUser updateUser
    ) {
        return userService.updateUser(userPrincipal.getUser(), updateUser);
    }

    @PatchMapping("/avatar")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Set user avatar.")
    @Authorization("Token")
    public UserDto setUserAvatar(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestParam MultipartFile file
    ) throws IOException {
        return userService.setUserAvatar(userPrincipal.getUser(), file);
    }

    @DeleteMapping("/avatar")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Delete user avatar.")
    @Authorization("Token")
    public UserDto deleteUserAvatar(
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return userService.deleteUserAvatar(userPrincipal.getUser());
    }
}
