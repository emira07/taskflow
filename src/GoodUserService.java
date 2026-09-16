import java.util.ArrayList;
import java.util.List;

public class GoodUserService {

    private final List<String> usernames = new ArrayList<>();

    public void register(String username) {
        validateUsername(username);
        usernames.add(username);
    }

    public boolean exists(String username) {
        return usernames.contains(username);
    }

    private void validateUsername(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
    }
}
