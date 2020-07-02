package pl.umk.mat.server.users;

import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.umk.mat.server.auth.ApiLoginEntriesRepository;
import pl.umk.mat.server.auth.utils.UserPrincipal;
import pl.umk.mat.server.config.Config;
import pl.umk.mat.server.utils.exceptions.BadRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.regex.Pattern;


@Service
public class UserService {
    private UserRepository userRepository;
    private ApiLoginEntriesRepository apiLoginEntriesRepository;
    private Config config;

    public UserService(UserRepository userRepository, ApiLoginEntriesRepository apiLoginEntriesRepository, Config config) {
        this.userRepository = userRepository;
        this.apiLoginEntriesRepository = apiLoginEntriesRepository;
        this.config = config;
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public UserDto findUserById(Long Id) {
        return new UserDto(userRepository.findByIdOrThrow(Id));
    }

    public UserDto getSelfInfo(UserPrincipal user) {
        return new UserDto(user.getUser());
    }

    public void logout(User user, String token) {
        apiLoginEntriesRepository.findByToken(token).setExpiredAt(Instant.now());
    }

    @Transactional
    public UserDto updateUser(User user, UpdateUser updateUser) {
        user.setFirstName(updateUser.getFirstName());
        user.setLastName(updateUser.getLastName());
        userRepository.save(user);
        return new UserDto(user);
    }

    @Transactional
    public UserDto setUserAvatar(User user, MultipartFile file) throws IOException {
        String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
        if (fileExtension == file.getOriginalFilename())
            throw new BadRequest("Incorrect file extension.");
        if (!Pattern.compile(config.getImageRegex()).matcher(fileExtension).find())
            throw new BadRequest("Incorrect file type (only jpg, jpeg supported).");
        String filename = "avatar_" + RandomString.make(20) + "." + fileExtension;
        String filePath = config.getImageDir() + filename;
        if (new File(filePath).exists())
            throw new RuntimeException("File exist try later.");
        if (!user.getAvatar().equals(config.getImageDir() + "avatar_default.jpg"))
            new File(user.getAvatar()).delete();
        user.setAvatar(filename);
        Files.copy(
                file.getInputStream(),
                Path.of(filePath),
                StandardCopyOption.REPLACE_EXISTING
        );
        userRepository.save(user);
        return new UserDto(user);
    }

    @Transactional
    public UserDto deleteUserAvatar(User user) {
        if (!user.getAvatar().equals(config.getImageDir() + "avatar_default.jpg"))
            new File(user.getAvatar()).delete();
        user.setAvatar("avatar_default.jpg");
        userRepository.save(user);
        return new UserDto(user);
    }
}
