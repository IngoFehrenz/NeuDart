val userAccounts: MutableList<Account> = mutableListOf()
fun main() {
    val shop = Shop()
    val adminAccount = AdminAccount("admin", "admin", shop)
    userAccounts.add(adminAccount)
    val customerAccount = CustomerAccount("customer", "password", shop)
    println("\u001B[36mBenutzer-Login oder Registrierung:")
    println("\u001B[36m1. Login")
    println("\u001B[36m2. Registrierung")
    val input = readln().toIntOrNull()

    when (input) {
        1 -> {
            // Benutzer-Login
            val loggedInAccount = login(shop)
            if (loggedInAccount != null) {
                displayMenu(loggedInAccount, shop)
            } else {
                println("Falscher Benutzername oder Passwort. Programm wird beendet.")
            }
        }

        2 -> {
            // Benutzerregistrierung
            val registeredAccount = register(shop)
            if (registeredAccount != null) {
                userAccounts.add(registeredAccount) // Hinzufügen des registrierten Kontos zur Liste der Benutzerkonten
                displayMenu(registeredAccount, shop)
            } else {
                println("Benutzerregistrierung fehlgeschlagen. Programm wird beendet.")
            }
        }

        else -> {
            println("Ungültige Eingabe. Programm wird beendet.")
        }

    }
    val loggedInAccount = login(shop)
    if (loggedInAccount != null) {
        // Benutzer-Menü
        displayMenu(loggedInAccount, shop)
    } else {
        println("Falscher Benutzername oder Passwort. Programm wird beendet.")
    }
}


fun login(shop: Shop): Account? {

    println(
        """
                            #######       ###     #########  ########  #######  ##    ##  ########  ##########
                            ##     ##    #####    ##     ##     ##     ##   ##  ##    ##  ##    ##  ##      ##
                            ##     ##   ##   ##   ##     ##     ##     ##       ##    ##  ##    ##  ##      ##
                            ##     ##  ##     ##  #########     ##     #######  ########  ##    ##  ##########
                            ##     ##  #########  ##     ##     ##          ##  ##    ##  ##    ##  ##
                            ##     ##  ##     ##  ##      ##    ##     ##   ##  ##    ##  ##    ##  ##
                            #######    ##     ##  ##       ##   ##     #######  ##    ##  ########  ##        """
    )

    println("\u001B[36mBenutzer-Login:")
    println("\u001B[36mBitte geben Sie Ihren Benutzernamen ein:")
    val username = readln()
    println("\u001B[36mBitte geben Sie Ihr Passwort ein:")
    val password = readln()
    println("\u001B[36mBitte geben Sie Ihr Alter ein:")
    val age = readln().toInt()

    return when {
        username == "admin" && password == "admin" -> AdminAccount("admin", "admin", Shop())
        username == "customer" && password == "password" && age >= 12 -> CustomerAccount("customer", "password", Shop())
        username == "customer" && password == "password" && age >= 12 -> Account("customer", "password", Shop())
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
        println("Sie müssen mindestens 12 Jahre alt sein, um sich zu registrieren.")
        return null
    }
    return CustomerAccount(username, password, Shop())
}

fun displayMenu(account: Account, shop: Shop) {
    when (account) {
        is AdminAccount -> {
            println("\u001B[35mWillkommen, ${account.username}!\u001B[35m")
            println("\u001B[36mBitte wählen Sie eine Option:\u001B[36m")
            println("\u001B[37m1. Artikel hinzufügen\u001B[37m")
            println("\u001B[38m2. Warenkorb anzeigen\u001B[38m")
            println("\u001B[34m3. Artikel zum Warenkorb hinzufügen\u001B[34m")
            println("\u001B[33m4. Bewertungen anzeigen\u001B[33m")
            println("\u001B[32m5. Nach Preis\u001B[32m")
            println("\u001B[39m6. Nach Alphabet\u001B[31m")
            println("\u001B[37m7. Sonderangebote\u001B[31m")
            println("\u001B[35m8. Gutscheincode\u001B[35m")
            println("\u001B[31m9. Beenden\u001B[31m")
            val input = readln().toIntOrNull()
            when (input) {
                1 -> {
                    if (account is AdminAccount) {
                        account.addProduct()
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
                    if (account is CustomerAccount) {
                        account.addProductToCart(shop)
                    } else {
                        println("Keine Berechtigung. Bitte wählen Sie eine andere Option.")
                    }
                }

                4 -> {
                    shop.printProductReviews()
                }

                5 -> {
                    // Print products sorted by price
                    val sortedByPrice = shop.getProductsSortedByPriceAscending()
                    println("Verfügbare Produkte nach Preis (aufsteigend):")
                    sortedByPrice.forEach { product ->
                        println("Name: ${product.name}")
                        println("Preis: ${product.price}")
                        println("Kundenrezension: ${product.review}")
                        println("---------------------------")
                    }
                }

                6 -> {
                    // Print products sorted alphabetically
                    val sortedAlphabetically = shop.getProductsSortedAlphabetically()
                    println("Verfügbare Produkte alphabetisch sortiert:")
                    sortedAlphabetically.forEach { product ->
                        println("Name: ${product.name}")
                        println("Preis: ${product.price}")
                        println("Kundenrezension: ${product.review}")
                        println("---------------------------")
                    }
                }

                7 -> {
                    println("Bitte geben sie den Namen des Produkts ein.")
                    val productName = readln()
                    println("Bitte geben Sie die Mindestmenge für das Sonderangebot ein:")
                    val quantity = readln().toIntOrNull()
                    if (productName != null && quantity != null) {
                        shop.addSpecialOffer(productName, quantity)
                        println("Sonderangebote erfolgreich hinzugefügt.")
                    } else {
                        println("Ungültige Eingabe.Sonderangebot konnte nicht hinzugefügt werde.")
                    }
                }

                8 -> {
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

                9 -> {
                    println("Das Programm wird beendet.")
                    return
                }

                else -> {
                    println("Ungültige Eingabe. Bitte wählen Sie eine andere Option.")
                }

            }
            displayMenu(account, shop)

        }

        is CustomerAccount -> {
            println("\u001B[35mWillkommen, ${account.username}!\u001B[35m")
            println("\u001B[36mBitte wählen Sie eine Option:\u001B[36m")
            println("\u001B[37m1. Artikel hinzufügen\u001B[37m")
            println("\u001B[38m2. Warenkorb anzeigen\u001B[38m")
            println("\u001B[34m3. Artikel zum Warenkorb hinzufügen\u001B[34m")
            println("\u001B[33m4. Bewertungen anzeigen\u001B[33m")
            println("\u001B[32m5. Nach Preis\u001B[32m")
            println("\u001B[39m6. Nach Alphabet\u001B[31m")
            println("\u001B[33m7. Sonderangebote\u001B[31m")
            println("\u001B[35m8. Gutscheincode\u001B[35m")
            println("\u001B[31m9. Beenden\u001B[31m")
            val input = readln().toIntOrNull()
            when (input) {
                1 -> account.addProductToCart(shop)
                2 -> account.printCart()
                3 -> return
                else -> println("Ungültige Eingabe.")
            }

            displayMenu(account, shop)
        }
    }
}



