package kotlin_in_action.section_9_operators_and_conventions.delegated_properties

import kotlin_in_action.Employee
import kotlin_in_action.employees
import kotlin.collections.component1
import kotlin.collections.component2

// 9.5.2 - Using delegated properties: Lazy initialization and by lazy

/*
    Lazy initialization — відкладена ініціалізація значення до першого звернення з подальшим кешуванням результату.
    - lazy() — стандартна функція, яка приймає лямбду ініціалізації та повертає об'єкт Lazy<T>.
    - Lazy<T> містить властивість value, функцію isInitialized та операторну extension-функцію getValue,
     яка забезпечує можливість делегування властивості з використанням by.
    - by lazy використовується з val.
    - За замовчуванням lazy на JVM є потокобезпечним.
*/

// Завдання 1: Реалізувати ліниву ініціалізацію групування
// працівників вручну через допоміжну властивість(backing property) та власний гетер.
class DepartmentReportManually(
    val employees: List<Employee>,
){
    private var _employeesByDepartment: Map<String, List<Employee>>? = null

    val employeesByDepartment: Map<String, List<Employee>>
        get() {
            if (_employeesByDepartment == null) {
                println("Log: employeesByDepartment is initializing")
                _employeesByDepartment = employees.groupBy { it.department }
            }
            return _employeesByDepartment!!
        }

    fun printEmployeesByDepartment(){
        employeesByDepartment.forEach { (department, employees) ->
            println("$department: ${employees.joinToString { it.name }}")
        }
    }
}

// Завдання 2: Реалізувати аналогічну ліниву ініціалізацію
// через by lazy та порівняти обидва підходи.
class DepartmentReport(
    val employees: List<Employee>
){
    val employeesByDepartment by lazy {
        println("Log: employeesByDepartment is initializing")
        employees.groupBy { it.department }
    }
    fun printEmployeesByDepartment(){
        employeesByDepartment.forEach { (department, employees) ->
            println("$department: ${employees.joinToString { it.name }}")
        }
    }
}

fun main() {
    println("Task 1")
    val reportManually = DepartmentReportManually(employees = employees)
    println("First access")
    reportManually.printEmployeesByDepartment()
    println("\nSecond access")
    reportManually.printEmployeesByDepartment()

    println("\nTask 2")
    val report = DepartmentReport(employees = employees)
    println("First access")
    report.printEmployeesByDepartment()
    println("\nSecond access")
    report.printEmployeesByDepartment()
}