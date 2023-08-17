/* Die Classe Shop ist auch eine Klasse nur die val product wurde ausgelagert
damit sie Global ist, damit man aus jeder Classe drauf zugreifen kann.

*/
val products: MutableList<Product> = mutableListOf(
    Product("Dart Board", 49.99, "Gutes Dartboard fuer Anfaenger"),
    Product("Dart Pfeile (Set of 3)", 19.99, "Hochwertige Dartpfeile für den professionellen Gebrauch."),
    Product("Dartcase", 14.99, "Transportkoffer fuer Dartpfeile"),
    Product("Dartmatte", 29.99, "Rutschfeste Matte fuer praezises werfen."),
    Product("Dart Flights(Set of 10)", 9.99, "Verschiedene Designs fuer Dartpfeile"),
    Product("Dart Shafts (Set of 5)", 25.99, "Ersatz-Schaefte für DartPfeile"),
    Product("Dart Point Sharpener)", 6.99, "Schleifwerkzeug zum schaerfen der Dartspitzen"),
    Product("Dartboard Surround", 24.99, "Schutzring fuer das Dartboard"),
    Product("Dart Scoreboard", 35.99, "Elektronisches Scoreboard fuer die Dartspiele"),
    Product("Dart Trainingsbuch", 8.99, "Trainingsbuch mit Tipps und Uebungen fuer Dartspieler"),
    Product("Dart Barrels (Set bestehend aus 3 Barrels)", 17.99, "Dart Barrels fuer Dartpfeile"),
    Product("Dart Spitzen(Beutel mit 1000 Spitzen)", 15.99, "Dart Spitzen fuer Dartpfeile ")
)

class Shop {

    private val couponCodes: MutableMap<String, Double> = mutableMapOf()
    private val specialOffers: MutableMap<String, Int> = mutableMapOf()

    fun addProduct(product: Product) {              // Hier werden Produkte hinzugefügt
        if (products.none { it.name == product.name }) {
            products.add(product)

        } else {
            println("Das Product mit name '${product.name}'existiert bereits.")
        }
    }

    fun printProducts() {                           // Gibt die verfügbaren Produkte aus
        println("verfuegbare Produkte:")
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

    fun getProductsSortedByPriceAscending(): List<Product> {       //Sortierte Preisliste der Produkte
        return products.sortedBy { it.price }
    }

    fun getProductsSortedAlphabetically(): List<Product> {       // Alphabetische Liste sortiert
        return products.sortedBy { it.name }
    }


    fun addSpecialOffer(quantity: Int?, productName: String?) {     // Eingabe Produktname und Menge
        if (quantity != null && productName != null) {
            specialOffers[productName] = quantity

        } else {
            println("Ungültige Eingabe. Menge und Produktname dürfen nicht null sein.")
        }
    }


    fun addCoupnCode(code: String, rabattProzente: Double) {   // Eingabe der Gutscheincodes und des Rabatts
        couponCodes[code] = rabattProzente

    }

    // Funktion zum Einlösen eines Gutscheincodes und Anwenden des Rabatts auf den Gesamtpreis
    fun applyCouponCode(code: String, totalPrice: Double): Double {
        val rabattProzente = couponCodes[code]
        if (rabattProzente != null) {
            val Rabattbetrag = totalPrice * rabattProzente / 100
            return totalPrice - Rabattbetrag
        }
        return totalPrice
    }

    private fun calculateSpecialOfferPrice(
        product: Product, quantity: Int
    ): Double {      // Rechnung der Sonderanderangebote
        val Angebotsmenge = specialOffers[product.name]
        if (Angebotsmenge != null && quantity >= Angebotsmenge) {
            val regulaererPreis = product.price * quantity
            val herabgesetzterPreis = product.price * (quantity - (quantity / Angebotsmenge))
            return minOf(regulaererPreis, herabgesetzterPreis)
        }
        return product.price * quantity
    }

    // Funktion zum Berechnen des Gesamtpreises des Warenkorbs mit Berücksichtigung von Sonderangeboten
    fun calculateCartTotalPrice(cart: List<Product>): Double {
        var totalPrice = 0.0
        for (product in cart) {
            val quantity = cart.count { it == product }
            totalPrice += calculateSpecialOfferPrice(product, quantity)
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
        for ((code, rabattProzente) in couponCodes) {
            println("$code - $rabattProzente%")
        }
    }

    // Filtert zwischen tiefsten und höchsten Preis
    fun filterByPrice(minPrice: Double, maxPrice: Double): List<Product> {
        return products.filter { it.price in (minPrice + 0.01)..maxPrice }
    }

}


