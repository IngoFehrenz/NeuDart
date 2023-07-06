class CustomerAccount (username: String, password: String, shop: Shop) : Account(username, password, shop) {
    private val cart: MutableList<Product> = mutableListOf()

    fun addProductToCart() {
        println("Artikel zum Warenkorb hinzufuegen:")
        println("Bitte geben Sie den Namen des Produkts ein:")
        val productName = readln()
        val product = shop.products.find { it.name == productName }
        if (product != null) {
            card.add(product)
            println("Produkt nicht gefunden.Bitte geben Sie einen gueltigen Namen ein.")
        }
    }
    fun printCart() {
        if (cart.isNotEmpty()) {
            println("Warenkorbinhalt:")
            for (product in cart) {
                println("Name: ${product.name}")
                println("Preis: ${product.price}")
                println("----------------------------")
            }
        } else {
            println("Der Warenkorb ist leer.")
        }
    }
}