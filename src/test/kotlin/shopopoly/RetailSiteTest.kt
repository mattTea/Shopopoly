package shopopoly

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object RetailSiteTest : Spek({
    describe("RetailSite location type") {
        val shop = RetailSite("John's shop", 125)

        it("should have a name of 'John's shop'") {
            assertThat(shop.name).isEqualTo("John's shop")
        }

        it("should have a purchase price of £125") {
            assertThat(shop.purchasePrice).isEqualTo(125)
        }
    }
})