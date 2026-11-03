package kotlin_in_action.section_7_nullable_values;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// Завдання 7.12.1 - клас з явними анотаціями
public class UserService {

    private final String username;
    private final String city;

    public UserService(String username, String city) {
        this.username = username;
        this.city = city;
    }

    @NotNull
    public String getUsername() {
        return username;
    }

    @Nullable
    public String getCity() {
        return city;
    }
}