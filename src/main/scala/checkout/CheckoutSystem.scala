package checkout

/**
  * Created by elias on 05/08/2016.
  */
trait CheckoutSystem {
  //I would normally use a Try here or create my own outcome class
  //but I've left the signature as follows to adhere to the requirements
  def checkout(purchases: Seq[String]): BigDecimal
}
