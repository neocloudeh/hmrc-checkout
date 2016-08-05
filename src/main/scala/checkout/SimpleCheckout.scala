package checkout

/**
  * Created by elias on 05/08/2016.
  */

object SimpleCheckout extends CheckoutSystem {
  val products = Map(("apple", BigDecimal(0.25)), ("orange", BigDecimal(2.05)))

  override def checkout(purchases: Seq[String]): BigDecimal = {
    val zeroState: (Map[String, Long], BigDecimal) = (Map().withDefaultValue(0L), BigDecimal(0))

    val (finalItems, finalSubtotal) = purchases.foldLeft(zeroState) {
      case ((itemsSoFar, totalSoFar), product) =>
        val newItemsSoFar = itemsSoFar.updated(product, itemsSoFar(product) + 1)
        val newSubtotal = products.getOrElse(product, throw new IllegalArgumentException(s"$products is not a supported item.")) + totalSoFar
        (newItemsSoFar, newSubtotal)
    }

    val orangesDiscount = Math.floor(finalItems("orange") / 3) * products("orange")
    val appleDiscount = Math.floor(finalItems("apple") / 2) * products("apple")

    finalSubtotal - orangesDiscount - appleDiscount
  }
}
