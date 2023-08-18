class Payment {
    fun selectPayment() {
        val Zahlungsmittel = mutableListOf<Zahlungsmethode>(
            Zahlungsmethode("Paypal", "€"),
            Zahlungsmethode("Klarna", "€"),
            Zahlungsmethode("Sofortüberweisung", "$")
        )
        println("Welches Zahlungsmittel möchten Sie nutzen? (Geben Sie die Nummer 1-3 ein)")
        for (i in Zahlungsmittel) {
            val j = Zahlungsmittel.indexOf(i)
            println("[${j + 1}] Name: ${i.name}, Währung: ${i.währung}")
        }

        val input = readLine()?.toIntOrNull()

        if (input != null && input >= 1 && input <= Zahlungsmittel.size) {
            val selectedPayment = Zahlungsmittel[input - 1]
            println("Sie haben ${selectedPayment.name} als Zahlungsmittel ausgewählt.")
            println("Die Zahlung wurde erfolgreich durchgeführt.")
        } else {
            println("Ungültige Eingabe. Die Zahlung konnte nicht durchgeführt werden.")
        }
    }
}

