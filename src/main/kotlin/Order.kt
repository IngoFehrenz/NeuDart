import java.time.LocalDate

class Order(val customer: CustomerAccount, val products: List<Product>) {
    val orderNumber: Int = generateOrderNumber()
    val orderDate: LocalDate = LocalDate.now()
    val totalPrice: Double = calculateTotalPrice()

    private fun generateOrderNumber(): Int {
        return (Math.random() * 100000).toInt()
    }

    private fun calculateTotalPrice(): Double {
        return products.sumOf { it.price }
    }
}
