package kotlin_in_action.section_4_classes.object_keyword;

public class CompanionObjectTest {
    public static void main(String[] args){
        System.out.println(AppSettings.ENVIRONMENT);
        AppSettings environment = AppSettings.createDefault();
        System.out.println(environment.getEnvironment());
        AppSettings environment2 = AppSettings.Companion.createDevelopment();
        System.out.println(environment2.getEnvironment());
    }
}
