package shopopoly

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object FreeParkingTest : Spek({

    describe("FreeParking") {
        it("should be free to visit") {
            assertThat(FreeParking.fee).isEqualTo(0)
        }
    }
})