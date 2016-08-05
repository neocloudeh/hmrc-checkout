package checkout

/**
  * Created by elias on 05/08/2016.
  */

object SimpleCheckout extends CheckoutSystem {
  val products = Map(("apple", BigDecimal(0.25)), ("orange", BigDecimal(2.05)))

  override def checkout(purchases: Seq[String]): BigDecimal = purchases.foldLeft(BigDecimal(0)){
    case (subtotal, product) =>
      products.getOrElse(product, throw new IllegalArgumentException(s"$products is not a supported item.")) + subtotal
  }
}
