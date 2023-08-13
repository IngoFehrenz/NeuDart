class Payment {
    fun selectPayment() {
        val Zahlungsmittel = mutableListOf<Zahlungsmethode>(
            Zahlungsmethode("Paypal", "€"),
            Zahlungsmethode("Klarna", "€"),
            Zahlungsmethode("Sofortüberweisung", "$"),
        )
        println("Welches Zahlungsmittel möchten sie nutzen an Hand Nummer 1-3")
        for (i in Zahlungsmittel) {
            val j = Zahlungsmittel.indexOf(i)
            println("[${j + 1}]Name:${i.name},Währung: ${i.währung}")
        }

        val input = readln().toInt()

    }
}