package kotlin_in_action.section_4_classes.object_keyword;

public class ObjectTest {
    public static void main(String[] args) {
        AppEventHandler.INSTANCE.handle("Login");
        AppEventHandler.INSTANCE.handle("Logout");

        System.out.println(
                "Handled events: " + AppEventHandler.INSTANCE.getHandledCount()
        );
    }
}
