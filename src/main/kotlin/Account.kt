class CustomerAccount(username: String, password: String, shop: Shop) : Account(username, password, shop) {
    private val cart: MutableList<Product> = mutableListOf()
    fun addProductToCart() {
        println("Artikel zum Warenkorb hinzufügen:")
        println("Bitte geben Sie den Namen des Produkts ein:")
        val productName = readln()
        val product = shop.products.find { it.name == productName }
        if (product != null) {
            cart.add(product)
            println("Produkt erfolgreich zum Warenkorb hinzugefügt.")
        } else {
            println("Produkt nicht gefunden. Bitte geben Sie einen gültigen Namen ein.")
        }
    }

    fun printCart() {
        if (cart.isNotEmpty()) {
            println("Warenkorbinhalt:")
            for (product in cart) {
                println("Name: ${product.name}")
                println("Preis: ${product.price}")
                println("---------------------------")
            }
        } else {
            println("Der Warenkorb ist leer.")
        }
    }
}