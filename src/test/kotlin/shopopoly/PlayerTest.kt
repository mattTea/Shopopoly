package shopopoly

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.mockk.every
import io.mockk.mockk
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object PlayerTest : Spek({

    describe("Player") {
        it("should have a name") {
            val player = Player("Matt")
            assertThat(player.name).isEqualTo("Matt")
        }
    }
})