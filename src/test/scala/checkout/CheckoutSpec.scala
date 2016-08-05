package checkout

import org.scalatest._
import org.scalatest.prop.PropertyChecks

/**
  * Created by elias on 05/08/2016.
  */
class CheckoutSpec extends FlatSpec with Matchers with PropertyChecks {

  "A checkout" should "return a cost of zero when purchasing nothing" in {
    assert(SimpleCheckout.checkout(Nil) === BigDecimal(0))
  }

  "A checkout" should "throw an exception if an unsupported item is purchased" in {
    assertThrows[IllegalArgumentException] { // Result type: Assertion
      SimpleCheckout.checkout(List("banana"))
    }
  }

  "A checkout" should "return the correct total in the correct case" in {
    assert(SimpleCheckout.checkout(List("apple", "orange")) === BigDecimal(2.30))
  }

  "A checkout" should "throw an exception if there is an unsupported item in the list of valid items" in {
    assertThrows[IllegalArgumentException] { // Result type: Assertion
      SimpleCheckout.checkout(List("apple", "banana", "orange"))
    }
  }
}
