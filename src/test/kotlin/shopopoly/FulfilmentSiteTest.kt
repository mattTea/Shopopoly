package shopopoly

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object FulfilmentSiteTest : Spek ({
    describe("FulfilmentSite location type") {
        val fulfilmentSite = FulfilmentSite()

        it("should cost £20 to visit") {
            assertThat(fulfilmentSite.feeOrAward).isEqualTo(-20)
        }

        it("should cost £100 to purchase") {
            assertThat(fulfilmentSite.purchasePrice).isEqualTo(-100)
        }
    }
})