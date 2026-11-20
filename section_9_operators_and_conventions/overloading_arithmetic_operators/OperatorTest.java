package kotlin_in_action.section_9_operators_and_conventions.overloading_arithmetic_operators;

import kotlin_in_action.section_9_operators_and_conventions.Money;

public class OperatorTest {
    public static void main(){
        Money first = new Money(1000);
        Money second = new Money(500);

        System.out.println(first.plus(second));
        System.out.println(first.minus(second));
    }
}
