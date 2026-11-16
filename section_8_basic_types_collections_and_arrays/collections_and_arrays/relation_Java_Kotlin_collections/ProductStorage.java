package kotlin_in_action.section_8_basic_types_collections_and_arrays.collections_and_arrays.relation_Java_Kotlin_collections;

import java.util.List;

public class ProductStorage {
    public static void addProduct(List<String> products, String product) {
        products.add(product);
    }
}