package shopopoly

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.mockk.every
import io.mockk.mockk
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object PlayerTest : Spek({

    describe("Player") {
        it("should not be charged a fee to visit FreeParking location") {
            val player = Player()

            val freeParking = mockk<FreeParking>()
            every { freeParking.visitorFeeOrAward } returns 0

            player.landOnOrPass(freeParking)

            assertThat(player.bankBalance).isEqualTo(0)
        }
    }
})