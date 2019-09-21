package shopopoly

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object GameLedgerTest : Spek({
    describe("GameLedger") {
        val gameLedger = GameLedger()
        gameLedger.transferStartingBalance(100, "Matt")

        it("should return a list of entries") {
            assertThat(gameLedger.entries.size).isEqualTo(1)
        }

        it("should record transfer of £100 starting balance to player") {
            assertThat(gameLedger.entries[0].amount).isEqualTo(-100)
            assertThat(gameLedger.entries[0].reason).isEqualTo("Starting balance transfer")
        }

        it("should record transfer of starting balance from 'Bank' to player 'Matt'") {
            assertThat(gameLedger.entries[0].from).isEqualTo("Bank")
            assertThat(gameLedger.entries[0].to).isEqualTo("Matt")
        }
    }
})