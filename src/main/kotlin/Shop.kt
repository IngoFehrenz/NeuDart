class Shop {
    val products: MutableList<Product> = mutableListOf()

    init {
        val product1 = Product("Dart Board", 49.99, "Gutes Dartboard fuer Anfaenger")
        val product2 = Product("Dart Pfeile (Set of 3)", 19.99, "Hochwertige Dartpfeile für den professionellen Gebrauch.")
        val product3 = Product("Dartcase", 14.99, "Transportkoffer fuer Dartpfeile")
        val product4 = Product("Dartmatte", 29.99, "Rutschfeste Matte fuer praezises werfen.")
        val product5 = Product("Dart Flights(Set of 10)", 9.99, "Verschiedene Designs fuer Dartpfeile")
        val product6 = Product("Dart Shafts (Set of 5)", 25.99, "Ersatz-Schaefte für DartPfeile")
        val product7 = Product("Dart Point Sharpener)", 6.99, "Schleifwerkzeug zum schaerfen der Dartspitzen")
        val product8 = Product("Dartboard Surround", 24.99, "Schutzring fuer das Dartboard")
        val product9 = Product("Dart Scoreboard", 35.99, "Elektronisches Scoreboard fuer die Dartspiele")
        val product10 = Product("Dart Trainingsbuch", 8.99, "Trainingsbuch mit Tipps und Uebungen fuer Dartspieler")
        val product11 = Product("Dart Barrels (Set bestehend aus 3 Barrels)", 17.99, "Dart Barrels fuer Dartpfeile")
        val product12 = Product("Dart Spitzen(Beutel mit 1000 Spitzen)", 15.99, "Dart Spitzen fuer ")
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
        println("Verfuegbare Produkte:")
        for (product in products) {
            println("Name: ${product.name}")
            println("Preis ${product.price}")
            println("Kundenrezension: ${product.review}")
            println("---------------------------")
        }
    }

    fun printProductReviews() {
        println("Kundenrezensionen:")
        for (product in products) {
            println("Produkt: ${product.name}")
            println("Kundenrezensionen: ${product.review}")
            println("-----------------------------")
        }
    }
    fun getProductsSortedByPriceAscending(): List<Product>  {
          return products.sortedBy { it.price}
    }
    fun geetProductsSortedAlphabetically(): List<Product> {
         return products.sortedBy { it.name}
    }
    private val specialOffers: MutableMap<String, Int>   = mutableMapOf()
    private val couponCodes: MutableMap<String, Double> = mutableMapOf()

    fun addSpecialOffer(quantity: Int, productName: String) {
        specialOffers[productName] = quantity
    }

    fun addCoupnCode(code: String, discountPercentage: Double) {
        couponCodes[code] = discountPercentage

    }
    fun applyCouponCode(code: String, totalPrice: Double): Double {
        val discountPercentage = couponCodes[code]
        if (discountPercentage != null) {
            val discountAmount = totalPrice * discountPercentage / 100
            return totalPrice - discountAmount

        }
        return totalPrice
    }
    private fun calculateSpeciaalOfferPrice(product: Product, quantity: Int): Double {
        val offerQuantity = specialOffers[product.name]
        if (offerQuantity != null && quantity >= offerQuantity){
            val regularPrice = product.price * quantity
            val discountedPrice = product.price * (quantity - (quantity/offerQuantity))
            return minOf(regularPrice,discountedPrice)
        }
        return product.price * quantity
    }
    fun calculateCartTotalPrice(cart: List<Product>): Double {
        var totalPrice = 0.0
        for (product in cart) {
            val quantity = cart.count { it == product }
            totalPrice += calculateSpeciaalOfferPrice(product, quantity)
        }

        return totalPrice
    }

 }










