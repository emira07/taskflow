public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(String username, String email, String password) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Invalid username");
        }

        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }

        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("Password is too short");
        }

        if (userRepository.findByEmail(email) != null) {
            throw new IllegalStateException("Email already exists");
        }

        User user = new User(null, username, email, password, true);
        userRepository.save(user);

        System.out.println("Welcome email sent to " + email);
        return user;
    }

    public User getUser(Long id) {
        User user = userRepository.findById(id);

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        return user;
    }
}
