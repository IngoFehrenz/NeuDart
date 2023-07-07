class Product (val name: String, val price:Double, val review: String) open class Account(val username: String, val password: String,protected val shop: Shop)
class AdminAccount(username: String, password: String, shop: Shop) : Account(username, password, shop) {
    fun addProduct() {
        println("Product hinzufügen:")
        println("Bitte geben Sie den Namen des Produkts ein")
        val name = readln()
        println("Bitte geben Sie den Preis des Produkts ein:")
        val price = readln().toDoubleOrNull()
        println("Bitte geben Sie die Kundenrezension des Produkts ein:")
        val review = readln()
        if (name!= null && price != null && review != null){
            val product = Product(name, price, review)
            this.shop.addProduct(product)
            println("Produkt erfolgreich hinzugefügt.")
        } else {
            println("Ungueltige Eingabe.Produkt konnte nicht hinzugefuegt werden.")
        }
    }
}