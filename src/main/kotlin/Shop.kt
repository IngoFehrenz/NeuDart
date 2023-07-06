class Shop {
    val products: MutableList<Product> = mutableListOf()

    init {
        val product1 = Product("Dartboard", 49.99, "Gutes Dartboard für Anfänger")
        val product2 = Product("Dartpfeile (Set of 3)", 19.99, "Hochwertige Dartpfeile für den professionellen Gebrauch")
        val product3 = Product("Dartmatte", 29.99, "Rutschfeste Dartmatte für präzises Werfen")
        val product4 = Product("Dart Case", 14.99, "Transportkoffer für Dartpfeile")
        val product5 = Product("Dart Flights (Set of 10)", 9.99, "Verschiedene Designs für individuelle Dartpfeile")
        val product6 = Product("Dart Shafts (Set of 5)", 25.99, "Ersatz-Schäfte für Dartpfeile")
        val product7 = Product("Dart Point Sharpener", 6.99, "Schleifwerkzeug zum Schärfen der Dartspitzen")
        val product8 = Product("Dartboard Surround", 24.99, "Schutzring für das Dartboard")
        val product9 = Product("Dart Scoreboard", 35.99, "Elektronisches Scoreboard für die Dartspiele")
        val product10 = Product("Dart Trainingsbuch", 8.99, "Trainingsbuch mit Tipps und Übungen für Dartspieler")
        val product11 = Product("Dart Barrels(Set bestehend aus 3 Barrels)", 17.99, "Dart Barrels für Dartpfeile")
        val product12 = Product("Dart Spitzen(Beutel mit 1000 Spitzen)", 15.99, "Dart Spitzen für Dartpfeile")
        addProduct(product1)
        addProduct(product2)
        addProduct(product3)
        addProduct(product4)
        addProduct(product5)
        addProduct(product6)
        addProduct(product7)
        addProduct(product8)
        addProduct(product9)
        addProduct(product10)
        addProduct(product11)
        addProduct(product12)
    }

    fun addProduct(product: Product) {
        products.add(product)
    }

    fun printProducts() {
        println("Verfügbare Produkte:")
        for (product in products) {
            println("Name: ${product.name}")
            println("Preis: ${product.price}")
            println("Kundenrezension: ${product.review}")
            println("---------------------------")
        }
    }

    fun printProductReviews() {
        println("Kundenrezensionen:")
        for (product in products) {
            println("Produkt: ${product.name}")
            println("Kundenrezension: ${product.review}")
            println("---------------------------")
        }
    }

    fun getProductsSortedByPriceAscending(): List<Product> {
        return products.sortedBy { it.price }
    }

    fun getProductsSortedAlphabetically(): List<Product> {
        return products.sortedBy { it.name }
    }
    private val specialOffers: MutableMap<String, Int> = mutableMapOf()
    private val couponCodes: MutableMap<String, Double> = mutableMapOf()

    // Funktion zum Hinzufügen von Sonderangeboten
    fun addSpecialOffer(productName: String, quantity: Int) {
        specialOffers[productName] = quantity
    }

    // Funktion zum Hinzufügen von Gutscheincodes
    fun addCouponCode(code: String, discountPercentage: Double) {
        couponCodes[code] = discountPercentage
    }

    // Funktion zum Überprüfen und Anwenden eines Gutscheincodes
    fun applyCouponCode(code: String, totalPrice: Double): Double {
        val discountPercentage = couponCodes[code]
        if (discountPercentage != null) {
            val discountAmount = totalPrice * discountPercentage / 100
            return totalPrice - discountAmount
        }
        return totalPrice
    }

    // Funktion zum Berechnen des Gesamtpreises mit angewendetem Sonderangebot
    private fun calculateSpecialOfferPrice(product: Product, quantity: Int): Double {
        val offerQuantity = specialOffers[product.name]
        if (offerQuantity != null && quantity >= offerQuantity) {
            val regularPrice = product.price * quantity
            val discountedPrice = product.price * (quantity - (quantity / offerQuantity))
            return minOf(regularPrice, discountedPrice)
        }
        return product.price * quantity
    }

    // Funktion zum Berechnen des Gesamtpreises des Warenkorbs
    fun calculateCartTotalPrice(cart: List<Product>): Double {
        var totalPrice = 0.0
        for (product in cart) {
            val quantity = cart.count { it == product }
            totalPrice += calculateSpecialOfferPrice(product, quantity)
        }
        return totalPrice
    }
}
