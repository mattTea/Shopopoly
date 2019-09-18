package shopopoly

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object GoTest : Spek({

    describe("Go location type") {
        // TODO amend to test behaviour (not state) when a player is introduced
        it("should award visitors £100") {
            val go = Go()
            assertThat(go.visitorFeeOrAward).isEqualTo(100)
        }
    }
})