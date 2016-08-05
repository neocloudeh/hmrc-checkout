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

  "A checkout" should "return the correct total in the standard correct case" in {
    assert(SimpleCheckout.checkout(List("apple", "orange")) === BigDecimal(2.30))
  }

  "A checkout" should "throw an exception if there is an unsupported item in the list of valid items" in {
    assertThrows[IllegalArgumentException] { // Result type: Assertion
      SimpleCheckout.checkout(List("apple", "banana", "orange"))
    }
  }

  "A checkout" should "correctly apply apple discounts if two are purchased" in {
    assert(SimpleCheckout.checkout(List("apple", "apple")) === BigDecimal(0.25))
  }

  "A checkout" should "correctly apply apple discounts if seven are purchased" in {
    assert(SimpleCheckout.checkout(List.fill(7)("apple")) === BigDecimal(1))
  }

  "A checkout" should "correctly apply apple discounts if eight are purchased" in {
    assert(SimpleCheckout.checkout(List.fill(8)("apple")) === BigDecimal(1))
  }

  "A checkout" should "correctly apply orange discounts if three are purchased" in {
    assert(SimpleCheckout.checkout(List.fill(3)("orange")) === BigDecimal(4.10))
  }

  "A checkout" should "correctly apply orange discounts if eight are purchased" in {
    assert(SimpleCheckout.checkout(List.fill(8)("orange")) === BigDecimal(12.3))
  }

  "A checkout" should "correctly apply orange discounts if nine are purchased" in {
    assert(SimpleCheckout.checkout(List.fill(9)("orange")) === BigDecimal(12.3))
  }
}
