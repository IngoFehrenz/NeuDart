class Product(val name: String, val price: Double, val review: String)
open class Account(val username: String, val password: String, protected val shop: Shop)
class AdminAccount(username: String, password: String, shop: Shop) : Account(username, password, shop) {
    fun addProduct() {
        println("Produkt hinzufügen:")
        println("Bitte geben Sie den Namen des Produkts ein:")
        val name = readln()
        println("Bitte geben Sie den Preis des Produkts ein:")
        val price = readln().toDoubleOrNull()
        println("Bitte geben Sie die Kundenrezension des Produkts ein:")
        val review = readln()
        if (name != null && price != null && review != null) {
            val product = Product(name, price, review)
            shop.addProduct(product)
            println("Produkt erfolgreich hinzugefügt.")
        } else {
            println("Ungültige Eingabe. Produkt konnte nicht hinzugefügt werden.")
        }
    }
    class Product(val id: Int, val name: String, val price: Double, val review: String) {
        override fun toString(): String {
            return "ID: $id\nName: $name\nPreis: $price\nKundenrezension: $review\n---------------------------"
        }
    }

}



