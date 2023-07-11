val userAccounts: MutableList<Account> = mutableListOf()
fun main() {
    val text = "Guten Tag in unserem Dartshop..................."
    for (i in 0 until text.length) {
        print(text[i])
        Thread.sleep(100)         // Wartezeit zwischen den Buchstaben (200 Millisekunden)
    }
        println()

    val shop = Shop()
    val adminAccount = AdminAccount("admin", "admin", shop)            //  Admin Account erstellen und zum Benutzerkonto zu fügen
    userAccounts.add(adminAccount)
    CustomerAccount("customer", "password", shop)                     //Kunden Acccount erstellen und zum Benutzerkonto zu fügen
    println("\u001B[34m" +"""
                              ██████╗░░█████╗░██████╗░████████╗░██████╗██╗░░██╗░█████╗░██████╗░
                              ██╔══██╗██╔══██╗██╔══██╗╚══██╔══╝██╔════╝██║░░██║██╔══██╗██╔══██╗
                              ██║░░██║███████║██████╔╝░░░██║░░░╚█████╗░███████║██║░░██║██████╔╝
                              ██║░░██║██╔══██║██╔══██╗░░░██║░░░░╚═══██╗██╔══██║██║░░██║██╔═══╝░
                              ██████╔╝██║░░██║██║░░██║░░░██║░░░██████╔╝██║░░██║╚█████╔╝██║░░░░░
                              ╚═════╝░╚═╝░░╚═╝╚═╝░░╚═╝░░░╚═╝░░░╚═════╝░╚═╝░░╚═╝░╚════╝░╚═╝░░░░░                           
                   """+ "\u001B[0m")
    Thread.sleep(2000)

    println("\u001B[36mBenutzer-Login oder Registrierung:")    // Login als Customer oder Admin
    println("\u001B[36m1. Login")
    println("\u001B[36m2. Registrierung")                      // Registrierung neuer Customer
    when (readln().toIntOrNull()) {
        1 -> {
            // Benutzer-Login
            val loggedInAccount = login()
            if (loggedInAccount != null) {                          // Login Überpfüfung
                displayMenu(loggedInAccount, shop)
            } else {
                println("Falscher Benutzername oder Passwort.Programm wird beendet.")        // Fehlermeldung bei falschen Eingaben
            }
        }
        2 -> {
            val registeredAccount = register(shop)
            if (registeredAccount != null) {
                userAccounts.add(registeredAccount)
                displayMenu(registeredAccount, shop)

            } else {
                println("Benutzerregstrierung fehlgeschlagen.Programm wird beendet.")
            }
        }

        else -> {
            println("Ungueltige Eingabe.Programm wird beendet.")
        }
    }

}

fun login(): Account? {
    println("\u001B[34m" +"""
               
                              ██████╗░░█████╗░██████╗░████████╗░██████╗██╗░░██╗░█████╗░██████╗░
                              ██╔══██╗██╔══██╗██╔══██╗╚══██╔══╝██╔════╝██║░░██║██╔══██╗██╔══██╗
                              ██║░░██║██╔══██║██╔══██╗░░░██║░░░░╚═══██╗██╔══██║██║░░██║██╔═══╝░
                              ██████╔╝██║░░██║██║░░██║░░░██║░░░██████╔╝██║░░██║╚█████╔╝██║░░░░░
                              ╚═════╝░╚═╝░░╚═╝╚═╝░░╚═╝░░░╚═╝░░░╚═════╝░╚═╝░░╚═╝░╚════╝░╚═╝░░░░░              """+ "\u001B[0m")

    Thread.sleep(1000)


    println("Benutzer-Login:")
    println("\u001B[36mBitte geben sie Ihren benutzernamen ein:")
    val username = readln()
    println("\u001B[36mBittegeben sie Ihr Passwort ein:")
    val password = readln()
    println("\u001B[36mBitte geben sie Ihr Alter ein:")

    val age = readln().toInt()

    return when {
        username == "admin" && password == "admin" -> AdminAccount("admin", "admin", Shop())
        username == "customer" && password == "password" && age >= 12 -> CustomerAccount("customer", "password", Shop())
        /*username == "customer" && password == "password" && age >= 12 -> Account("customer", "password", Shop())*/


        else -> null
    }

}

fun register(shop: Shop): CustomerAccount? {


    println("\u001B[36mBenutzerregistrierung:")
    println("\u001B[36mBitte geben Sie einen Benutzernamen ein:")
    val username = readln()
    println("\u001B[36mBitte geben Sie ein Passwort ein:")
    val password = readln()
    println("\u001B[36mBitte geben Sie Ihr Alter ein:")
    val age = readln().toInt()
    if (age < 12) {
        println("Sie müesssen mindestens 12 Jahre alt sein,um sich zu registrieren.")
        return null
    }
    return CustomerAccount(username, password, Shop())
}

fun displayMenu(account: Account, shop: Shop) {
    when (account) {
        is AdminAccount -> {
            println("\u001B[35mWillkommen, ${account.username}!\u001b[35m")
            println("\u001B[36mBitte wählen Sie eine Option:\u001B[36m")
            println("\u001B[93m1. Artikel zum Bestand hinzufügen\u001B[34m")
            println("\u001B[94m2. Bewertungen anzeigen\u001B[33m")
            println("\u001B[95m3. Nach Preis sortieren\u001B[32m")
            println("\u001B[96m4. Nach Alphabet sortieren\u001B[39m")
            println("\u001B[97m5. Sonderangebote hinzufügen\u001B[35m")
            println("\u001B[35m6. Gutscheincode hinzufügen\u001B[35m")
            println("\u001b[91m7. Logout\u001B[31m")
            when (readln().toIntOrNull()) {
                1 -> {
                    if (account is AdminAccount) {
                        account.addProduct()
                    } else {
                        println("Produkt wurde hinzugefuegt..")
                    }
                }

                2 -> {
                    shop.printProductReviews()
                }

                3 -> {
                    val sortedByPrice = shop.getProductsSortedByPriceAscending()
                    println("Verfügbare Produkte nach Preis (aufsteigend):")
                    sortedByPrice.forEach { product ->
                        println("Name: ${product.name}")
                        println("Preis: ${product.price}")
                        println("Kundenrezension: ${product.review}")
                        println("------------------------------")
                    }
                }

                4 -> {
                    val sortedAlphabetically = shop.geetProductsSortedAlphabetically()
                    println("Verfügbare Produkte alphabetisch sortiert:")
                    sortedAlphabetically.forEach { product ->
                        println("Name: ${product.name}")
                        println("Preis: ${product.price}")
                        println("Kundenrezension: ${product.review}")
                        println("------------------------------")
                    }
                }

                5 -> {
                    println("Bitte geben Sie den Namen des Produkts ein:")
                    val productName = readln()
                    println("Bitte geben Sie die Mindestmenge für das Sonderangebot ein:")
                    val quantity = readln().toIntOrNull()
                    if (productName != null && quantity != null) {
                        shop.addSpecialOffer(quantity, productName)
                        println("Sonderangebote erfolgreich hinzugefügt.")
                    } else {
                        println("Ungültige Eingabe. Sonderangebot konnte nicht hinzugefügt werden.")
                    }
                }

                6 -> {
                    println("Bitte geben Sie den Gutscheincode ein:")
                    val code = readln()
                    println("Bitte geben Sie den Rabatt in Prozent ein:")
                    val discount = readln().toDoubleOrNull()
                    if (code != null && discount != null) {
                        shop.addCoupnCode(code, discount)
                        println("Gutscheincode erfolgreich hinzugefügt.")
                    } else {
                        println("Ungültige Eingabe. Gutscheincode konnte nicht hinzugefügt werden.")
                    }
                }

                7 -> { val loggedInAccount = login()
                    if (loggedInAccount != null) {
                        displayMenu(loggedInAccount, shop)
                    } else {
                        println("Falscher Benutzername oder Passwort.Programm wird beendet.")
                    }
                }

                else -> {
                    println("Ungültige Eingabe. Bitte wählen Sie eine andere Option.")
                }
            }
        }

        is CustomerAccount -> {
            println("\u001B[35m Willkommen, ${account.username}!\u001b[35m")
            println("\u001b[36m Bitte wählen Sie eine Option:\u001B[36m")
            println("\u001B[34m1. Artikel zum Warenkorb hinzufügen \u001B[34m")
            println("\u001B[33m2. Warenkorb anzeigen \u001B[33m")
            println("\u001B[36m3. Bewertungen anzeigen\u001B[33m")
            println("\u001B[39m4. Nach Preis sortieren\u001B[39m")
            println("\u001B[32m5. Nach Alphabet sortieren\u001B[35m")
            println("\u001B[34m6. Sonderangebote aussuchen\u001B[35m")
            println("\u001B[35m7. Gutscheine einlösen\u001B[35m")
            println("\u001b[91m8. Logout\u001B[31m")
            when (readln().toIntOrNull()) {
                1 -> {
                    if (account is CustomerAccount) {
                        account.addProductToCart()
                    } else {
                        println("Keine Berechtigung. Bitte wählen Sie eine andere Option.")
                    }

                }

                2 -> {
                    if (account is CustomerAccount) {
                        account.printCart()
                    } else {
                        println("Keine Berechtigung. Bitte wählen Sie eine andere Option.")
                    }

                }

                3 -> {
                    shop.printProductReviews()
                }

                4 -> {
                    val sortedByPrice = shop.getProductsSortedByPriceAscending()
                    println("Verfügbare Produkte nach Preis (aufsteigend):")
                    sortedByPrice.forEach { product ->
                        println("Name: ${product.name}")
                        println("Preis: ${product.price}")
                        println("Kundenrezension: ${product.review}")
                        println("------------------------------")
                    }
                }

                5 -> {
                    val sortedAlphabetically = shop.geetProductsSortedAlphabetically()
                    println("Verfügbare Produkte alphabetisch sortiert:")
                    sortedAlphabetically.forEach { product ->
                        println("Name: ${product.name}")
                        println("Preis: ${product.price}")
                        println("Kundenrezension: ${product.review}")
                        println("------------------------------")
                    }
                }

                6 -> {
                    shop.displaySpecialOffers()
                    shop.addSpecialOffer(3, "Produkt A")
                    shop.addSpecialOffer(5, "Produkt B")

                }

                7 -> {
                    shop.displayCouponCodes()
                    shop.addCoupnCode("CODE123", 10.0)
                    shop.addCoupnCode("CODE456", 20.0)
                }

                8 -> {
                    println("Das Programm wird beendet.")
                    login()
                }

                else -> {
                    println("Ungültige Eingabe. Bitte wählen Sie eine andere Option.")
                }
            }
        }

        else -> {
            println("Ungültiger Account. Bitte erneut einloggen.")
            login()
        }
    }
    displayMenu(account, shop)
}




