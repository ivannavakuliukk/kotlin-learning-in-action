package kotlin_in_action.section_8_basic_types_collections_and_arrays.part_8_2.Java_collections_in_Kotlin;

import kotlin_in_action.Product;

import java.util.List;
import java.util.Map;

public interface OrderProcessor {
    void processOrder(
            List<Product> products,
            Map<String, Integer> discounts,
            String customerType,
            Integer deliveryFee
    );
}