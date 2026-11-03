package kotlin_in_action.section_7_nullable_values;

// Завдання 7.12.2 - клас без явних анотацій
public class LegacyUserService {

    private final String userName;

    public LegacyUserService(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}