package shopopoly

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object GameLedgerFunctionalTest : Spek ({
    describe("GameLedger functional test") {
        it("should return building built on retail site owned by player") {
            val gameLedger = GameLedger()
            val player = Player("Matt")

            val retailSite = RetailSite(
                name = "New shop",
                purchasePrice = 100,
                costToBuildMinistore = 50,
                costToBuildSupermarket = 100,
                costToBuildMegastore = 200,
                owner = Bank,
                visitorFeeOrAward = 10
            )

            gameLedger.buyLocation(retailSite, player)
            gameLedger.payBuildingFee(retailSite, Store.MINISTORE, player)

            assertThat(gameLedger.getPremisesOwnedBy(player)[0].store).isEqualTo(Store.MINISTORE)
        }
    }
})