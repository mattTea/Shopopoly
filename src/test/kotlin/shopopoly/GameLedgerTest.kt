package shopopoly

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.mockk.every
import io.mockk.mockk
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object GameLedgerTest : Spek({
    describe("transferStartingBalance()") {
        val gameLedger = GameLedger()
        val player = mockk<Player>()
        every { player.name } returns "Matt"
        gameLedger.transferStartingBalance(100, player)

        it("should add an entry to GameLedger") {
            assertThat(gameLedger.entries.size).isEqualTo(1)
        }

        it("should record transfer of £100 starting balance to player") {
            assertThat(gameLedger.entries[0].amount).isEqualTo(-100)
            assertThat(gameLedger.entries[0].reason).isEqualTo("Starting balance transfer")
        }

        it("should record transfer of starting balance from 'Bank' to player 'Matt'") {
            assertThat(gameLedger.entries[0].from).isEqualTo("Bank")
            assertThat(gameLedger.entries[0].to).isEqualTo(player.name)
        }
    }

    // Bank paying a fee to a `Player`, e.g. when the player passes through the `Go` location.
    describe("payAward()") {
        val gameLedger = GameLedger()
        val player = mockk<Player>()
        every { player.name } returns "Matt"

        val mockGoLocation = mockk<Go>()
        every { mockGoLocation.visitorFeeOrAward } returns 100
        gameLedger.payAward(mockGoLocation, player)

        it("should add entry to GameLedger") {
            assertThat(gameLedger.entries.size).isEqualTo(1)
        }

        it("should record award of £100 for passing Go") {
            assertThat(gameLedger.entries[0].amount).isEqualTo(-100)
            assertThat(gameLedger.entries[0].reason).isEqualTo("Award fee")
        }

        it("should record award from 'Bank' to player 'Matt'") {
            assertThat(gameLedger.entries[0].from).isEqualTo("Bank")
            assertThat(gameLedger.entries[0].to).isEqualTo(player.name)
        }
    }
})