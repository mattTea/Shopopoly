package shopopoly

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object FreeParkingTest : Spek({

    describe("FreeParking location type") {
        it("should be free to visit") {
            val freeParking = FreeParking()
            assertThat(freeParking.visitorFeeOrAward).isEqualTo(0)
        }
    }
})