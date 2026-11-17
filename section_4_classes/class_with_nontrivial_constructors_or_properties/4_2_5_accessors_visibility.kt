package kotlin_in_action.section_4_classes.class_with_nontrivial_constructors_or_properties

// 4.2.5 - Changing accessors visibility

/*
    За замовчуванням getter та setter мають таку ж видимість як property.
    Однак її можна обмежувати для setter встановивши модифікатор видимості перед set: н-д private set
 */

// Завдання 1: Створити DownloadManager із лічильником завантажень та private set.
// Дозволити змінювати лічильник лише через методи completeDownload() і reset().
class DownloadManager{
    var completedDownloads: Int = 0
        private set

    fun completeDownload(){
        completedDownloads++
    }

    fun reset(){
        completedDownloads = 0
    }
}

// Завдання 2: Створити Shipment зі статусом доставки та protected set.
// Реалізувати зміну статусу в базовому класі та дочірньому ExpressShipment.
open class Shipment{
    var status: String = "Created"
        protected set

    fun ship(){
        status = "Shipped"
    }
}

class ExpressShipment : Shipment(){
    fun shipExpress(){
        status = "Express shipped"
    }
}

fun main(){
    println("Task 1")
    val downloadManager = DownloadManager()

    repeat(5) {
        downloadManager.completeDownload()
    }
    println("Files downloaded: ${downloadManager.completedDownloads}")

    downloadManager.reset()
    println("Files downloaded: ${downloadManager.completedDownloads}")
//    Неможливо - приватний сетер
//    downloadManager.completedDownloads = 5


    println("\nTask 2")
    val expressShipment = ExpressShipment()

    expressShipment.ship()
    println(expressShipment.status)

    expressShipment.shipExpress()
    println(expressShipment.status)

}