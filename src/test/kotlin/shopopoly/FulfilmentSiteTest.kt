package shopopoly

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object FulfilmentSiteTest : Spek ({
    describe("FulfilmentSite location type") {
        val factory = FulfilmentSite("factory")

        it("should cost £20 to visit") {
            assertThat(factory.feeOrAward).isEqualTo(-20)
        }

        it("should cost £100 to purchase") {
            assertThat(factory.purchasePrice).isEqualTo(-100)
        }

        it("should have a name of 'factory'") {
            assertThat(factory.name).isEqualTo("factory")
        }
    }
})