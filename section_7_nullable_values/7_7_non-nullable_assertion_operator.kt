package kotlin_in_action.section_7_nullable_values

// 7.7 - Making promises to compiler with the non-null assertion operator

/*
    !! — оператор, який дозволяє звернутися до nullable-значення як до non-null.
    Якщо значення виявиться null, виникне NullPointerException.

    У завданні показана ситуація у якій доцільно використати оператор
    - коли компілятор сам не може зрозуміти що це безпечно.
    Перевірка значення та його використання у різних функціях
 */

// Реалізувати текстовий редактор із можливістю копіювати вибраний рядок.
// Перевірити наявність вибраного рядка перед виконанням дії у одній функції
// та використати !! для отримання його індексу у іншій.

class SelectableTextList(
    val content: List<String>,
    var selectedIndex: Int?
)

class CopyRowAction(val list: SelectableTextList){
    fun isActionEnabled(): Boolean = list.selectedIndex != null

    fun executeCopyRow(){
        val index = list.selectedIndex!!
        val value = list.content[index]
        println("Row \"$value\" is copied to clipboard" )
    }
}

class TextEditor(
    val selectableTextList: SelectableTextList,
    val copyRowAction: CopyRowAction
){
    fun copySelectedRow(){
        if (copyRowAction.isActionEnabled()) copyRowAction.executeCopyRow()
        else println("Enable to copy, no row selected")
    }
}

fun main(){
    val textList1 = SelectableTextList(
        listOf("Hello", "World"),
        selectedIndex = null
    )
    val textList2 = SelectableTextList(listOf("Hello", "World"), 0)
    val textEditor1 = TextEditor(
        textList1,
        CopyRowAction(textList1)
    )
    val textEditor2 = TextEditor(
        textList2,
        CopyRowAction(textList2)
    )
    textEditor1.copySelectedRow()
    textEditor2.copySelectedRow()
}