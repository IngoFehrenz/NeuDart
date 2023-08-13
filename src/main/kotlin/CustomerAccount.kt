class CustomerAccount(username: String, password: String, shop: Shop) : Account(username, password, shop) {
    private val cart: MutableList<Product> = mutableListOf()
    private val orders: MutableList<Order> = mutableListOf()
    fun addProductToCart() {
        println("Artikel zum Warenkorb hinzufügen:")
        println("Bitte geben Sie den Namen des Produkts ein:")
        val productName = readln()
        val product = products.find { it.name == productName }
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


    fun placeOrder() {
        if (cart.isNotEmpty()) {
            val newOrder = Order(this, cart.toList())
            orders.add(newOrder)
            cart.clear()
            println("Bestellung erfolgreich aufgegeben. Bestellnummer: ${newOrder.orderNumber}")
        } else {
            println("Der Warenkorb ist leer. Es gibt nichts zu bestellen.")
        }
    }

    fun viewOrders() {
        if (orders.isNotEmpty()) {
            println("Ihre Bestellungen:")
            for (order in orders) {
                println("Bestellnummer: ${order.orderNumber}")
                println("Bestelldatum: ${order.orderDate}")
                println("Gesamtpreis: ${order.totalPrice}")
                println("---------------------------")
            }
        } else {
            println("Sie haben noch keine Bestellungen aufgegeben.")
        }
    }
}

