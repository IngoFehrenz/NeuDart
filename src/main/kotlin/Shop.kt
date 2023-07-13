class Shop {
    val products: MutableList<Product> = mutableListOf()
    private val couponCodes: MutableMap<String, Double> = mutableMapOf()
    private val specialOffers: MutableMap<String, Int> = mutableMapOf()

    init {         // Erstellung der Produktliste mit Preis und Bezeichnung
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
        val product12 = Product("Dart Spitzen(Beutel mit 1000 Spitzen)", 15.99, "Dart Spitzen fuer Dartpfeile ")
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

    fun addProduct(product: Product) {              // Hier werden Produkte hinzugefügt
        products.add(product)
    }

    fun printProducts() {                           // Gibt die Verfügbaren Produkte aus
        println("Verfuegbare Produkte:")
        for (product in products) {
            println("Name: ${product.name}")
            println("Preis ${product.price}")
            println("Kundenrezension: ${product.review}")
            println("---------------------------")
        }
    }

    fun printProductReviews() {                   // Gibt die Beschreibungen der Produkte aus
        println("Kundenrezensionen:")
        for (product in products) {
            println("Produkt: ${product.name}")
            println("Kundenrezensionen: ${product.review}")
            println("-----------------------------")
        }
    }
    fun getProductsSortedByPriceAscending(): List<Product>  {       //Sortierte Preisliste der Produkte
          return products.sortedBy { it.price}
    }
    fun geetProductsSortedAlphabetically(): List<Product> {        // Alphabetische Liste sortiert
         return products.sortedBy { it.name}
    }



    fun addSpecialOffer(quantity: Int?, productName: String?) {     // Eingabe Produktname und Menge
        if (quantity != null && productName != null) {
            specialOffers[productName] = quantity

        } else {
            println("Ungültige Eingabe. Menge und Produktname dürfen nicht null sein.")
        }
    }


    fun addCoupnCode(code: String, discountPercentage: Double) {   // Eingabe der Gutscheincodes und des Rabatts
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
    private fun calculateSpeciaalOfferPrice(product: Product, quantity: Int): Double {      // Rechnung der Sonderanderangebote
        val offerQuantity = specialOffers[product.name]
        if (offerQuantity != null && quantity >= offerQuantity){
            val regularPrice = product.price * quantity
            val discountedPrice = product.price * (quantity - (quantity/offerQuantity))
            return minOf(regularPrice,discountedPrice)
        }
        return product.price * quantity
    }
    fun calculateCartTotalPrice(cart: List<Product>): Double {     // Erstellt den Gesamtpreis
        var totalPrice = 0.0
        for (product in cart) {
            val quantity = cart.count { it == product }
            totalPrice += calculateSpeciaalOfferPrice(product, quantity)
        }

        return totalPrice
    }
    fun getSpecialOffers(): Map<String, Int> {      //Hier werden die Sonderangebote angelegt
        return specialOffers
    }

    fun getCouponCodes(): Map<String, Double> {   // Hier werden die Gutscheincodes angelegt
        return couponCodes
    }

    fun displaySpecialOffers() {               // Hier werden die Sonderangebote angezeigt
        println("Spezielle Angebote:")
        for ((productName, quantity) in specialOffers) {
            println("$productName - $quantity")
        }
    }

    fun displayCouponCodes() {                 // Hier werden die Gutscheincodes angezeigt
        println("Gutschein-Codes:")
        for ((code, discountPercentage) in couponCodes) {
            println("$code - $discountPercentage%")
        }
    }
 }


