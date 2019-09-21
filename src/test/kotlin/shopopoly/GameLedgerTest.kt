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
            assertThat(gameLedger.entries[0].amount).isEqualTo(100)
            assertThat(gameLedger.entries[0].reason).isEqualTo("Starting balance transfer")
        }

        it("should record transfer of starting balance from 'Bank' to player 'Matt'") {
            assertThat(gameLedger.entries[0].from).isEqualTo("Bank")
            assertThat(gameLedger.entries[0].to).isEqualTo(player.name)
        }
    }

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
            assertThat(gameLedger.entries[0].amount).isEqualTo(100)
            assertThat(gameLedger.entries[0].reason).isEqualTo("Award fee")
        }

        it("should record award from 'Bank' to player 'Matt'") {
            assertThat(gameLedger.entries[0].from).isEqualTo("Bank")
            assertThat(gameLedger.entries[0].to).isEqualTo(player.name)
        }
    }

    describe("payVisitorFee()") {
        val gameLedger = GameLedger()

        val mockWarehouse = mockk<FulfilmentSite>()
        every { mockWarehouse.visitorFeeOrAward } returns 35

        val playerPayingFee = mockk<Player>()
        every { playerPayingFee.name } returns "Karsten"

        val playerReceivingFee = mockk<Player>()
        every { playerReceivingFee.name } returns "Katie"

        gameLedger.payVisitorFee(mockWarehouse, playerReceivingFee, playerPayingFee)

        it("should add entry to GameLedger") {
            assertThat(gameLedger.entries.size).isEqualTo(1)
        }

        it("should record payment of £35 visitor fee between players") {
            assertThat(gameLedger.entries[0].amount).isEqualTo(35)
            assertThat(gameLedger.entries[0].reason).isEqualTo("Visitor payment")
        }

        it("should record fee paid by 'Karsten' to 'Katie'") {
            assertThat(gameLedger.entries[0].from).isEqualTo(playerPayingFee.name)
            assertThat(gameLedger.entries[0].to).isEqualTo(playerReceivingFee.name)
        }
    }
})