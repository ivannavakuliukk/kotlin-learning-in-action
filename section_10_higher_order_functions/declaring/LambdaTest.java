package kotlin_in_action.section_10_higher_order_functions.declaring;

import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin_in_action.Employee;

import static kotlin_in_action.section_10_higher_order_functions.declaring._10_1_2_calling_passed_functionsKt.processSalary;

// 10.1.3 - Java lambdas are automatically converted to Kotlin function types

/*
    Kotlin-функціональні типи на JVM представлені інтерфейсами Function0, Function1, Function2 тощо, залежно від кількості параметрів.
    - Кожен із цих інтерфейсів має абстрактний метод invoke().
    - Java автоматично перетворює лямбда-вираз на реалізацію відповідного функціонального інтерфейсу.
    - Наприклад, Kotlin-типу (Int) -> Int у Java відповідає Function1<Integer, Integer>.
    Завдяки цьому Java може передавати лямбда-вирази безпосередньо у Kotlin-функції без додаткових адаптерів.
 */

// Завдання: Викликати Kotlin-функцію processSalary() з Java,
// передаючи Java-лямбди для розрахунку зарплати.
public class LambdaTest {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee("Michael", 25, 3000, "IT"),
                new Employee("Anna", 30, 4000, "HR"),
                new Employee("John", 28, 5000, "Finance")
        );

        Function1<Integer, Integer> bonus =
                salary -> salary + salary * 15 / 100;

        Function1<Integer, Integer> deduction =
                salary -> salary - salary * 10 / 100;

        System.out.println("Salary with bonus");
        processSalary(employees, bonus);

        System.out.println("\nSalary with deduction");
        processSalary(employees, deduction);
    }
}