package kotlin_in_action.section_4_classes.compiled_generated_methods.data_class;
import java.lang.Record;

public class RecordTest {
    public static void main(String[] args) {
        Parcel parcel = new Parcel(1, "Kyiv", 12.5);

        System.out.println(parcel.trackingNumber());
        System.out.println(parcel.destination());
        System.out.println(parcel.weight());
        System.out.println(parcel.isHeavy());
        System.out.println(parcel instanceof Record);
    }
}