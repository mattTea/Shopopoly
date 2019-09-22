package shopopoly

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import io.mockk.every
import io.mockk.mockk
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object GameLedgerTest : Spek({
    describe("transferStartingBalance()") {
        val gameLedger = GameLedger()
        val startingBalance = 100

        val player = mockk<Player>()
        every { player.name } returns "Matt"

        gameLedger.transferStartingBalance(startingBalance, player)

        it("should add an entry to GameLedger") {
            assertThat(gameLedger.entries.size).isEqualTo(1)
        }

        it("should record transfer of £100 starting balance to player") {
            assertThat(gameLedger.entries[0].amount).isEqualTo(startingBalance)
            assertThat(gameLedger.entries[0].reason).isEqualTo("Starting balance transfer")
        }

        it("should record transfer of starting balance from 'Bank' to player 'Matt'") {
            assertThat(gameLedger.entries[0].from).isEqualTo(Bank)
            assertThat(gameLedger.entries[0].to).isEqualTo(player)
        }
    }

    describe("payAward()") {
        val gameLedger = GameLedger()
        val awardAmount = 100

        val player = mockk<Player>()
        every { player.name } returns "Matt"

        val mockGoLocation = mockk<Go>()
        every { mockGoLocation.visitorFeeOrAward } returns awardAmount

        gameLedger.payAward(mockGoLocation, player)

        it("should add entry to GameLedger") {
            assertThat(gameLedger.entries.size).isEqualTo(1)
        }

        it("should record award of £100 for passing Go") {
            assertThat(gameLedger.entries[0].amount).isEqualTo(awardAmount)
            assertThat(gameLedger.entries[0].reason).isEqualTo("Award fee")
        }

        it("should record award from 'Bank' to player 'Matt'") {
            assertThat(gameLedger.entries[0].from).isEqualTo(Bank)
            assertThat(gameLedger.entries[0].to).isEqualTo(player)
        }
    }

    describe("payVisitorFee()") {
        val gameLedger = GameLedger()
        val visitorFee = 35

        val mockWarehouse = mockk<FulfilmentSite>()
        every { mockWarehouse.visitorFeeOrAward } returns visitorFee

        val playerPayingFee = mockk<Player>()
        every { playerPayingFee.name } returns "Karsten"

        val playerReceivingFee = mockk<Player>()
        every { playerReceivingFee.name } returns "Katie"

        gameLedger.payVisitorFee(mockWarehouse, playerReceivingFee, playerPayingFee)

        it("should add entry to GameLedger") {
            assertThat(gameLedger.entries.size).isEqualTo(1)
        }

        it("should record payment of £35 visitor fee between players") {
            assertThat(gameLedger.entries[0].amount).isEqualTo(visitorFee)
            assertThat(gameLedger.entries[0].reason).isEqualTo("Visitor payment")
        }

        it("should record fee paid by 'Karsten' to 'Katie'") {
            assertThat(gameLedger.entries[0].from).isEqualTo(playerPayingFee)
            assertThat(gameLedger.entries[0].to).isEqualTo(playerReceivingFee)
        }
    }

    describe("buyLocation()") {
        val gameLedger = GameLedger()
        val purchasePrice = 200

        val mockRetailSite = mockk<RetailSite>()
        every { mockRetailSite.purchasePrice } returns purchasePrice

        val buyer = mockk<Player>()
        every { buyer.name } returns "Catherine"

        gameLedger.buyLocation(mockRetailSite, buyer)

        it("should add entry to GameLedger") {
            assertThat(gameLedger.entries.size).isEqualTo(1)
        }

        it("should record payment of £200 purchase price from player") {
            assertThat(gameLedger.entries[0].amount).isEqualTo(purchasePrice)
            assertThat(gameLedger.entries[0].reason).isEqualTo("Location purchase")
        }

        it("should record fee paid by buyer to 'Bank'") {
            assertThat(gameLedger.entries[0].from).isEqualTo(buyer)
            assertThat(gameLedger.entries[0].to).isEqualTo(Bank)
        }
    }

    describe("payBuildingFee()") {
        val gameLedger = GameLedger()
        val buildingFee = 125
        val player = mockk<Player>()

        val retailSite = mockk<RetailSite>()
        val mockMiniStore = mockk<Store>()
        every { retailSite.calculateCostToBuild(mockMiniStore) } returns buildingFee
        every { retailSite.setStoreType(mockMiniStore) } returns Unit

        gameLedger.payBuildingFee(retailSite, mockMiniStore, player)

        it("should add entry to GameLedger") {
            assertThat(gameLedger.entries.size).isEqualTo(1)
        }

        it("should record payment of £125 to build ministore on retail site") {
            assertThat(gameLedger.entries[0].amount).isEqualTo(buildingFee)
            assertThat(gameLedger.entries[0].reason).isEqualTo("Fee to build $mockMiniStore on retail site")
        }
    }

    describe("calculatePlayerBalance()") {
        context("when player balance is positive") {

            val gameLedger = GameLedger()
            val startingBalance = 1000
            val passGoAward = 100

            val mockGo = mockk<Go>()
            every { mockGo.visitorFeeOrAward } returns passGoAward

            val mockPlayer = mockk<Player>()

            gameLedger.transferStartingBalance(startingBalance, mockPlayer)
            gameLedger.payAward(mockGo, mockPlayer)

            it("should return player in credit for 2 positive entries in GameLedger") {
                assertThat(gameLedger.calculatePlayerBalance(mockPlayer)).isInstanceOf(Credit::class.java)
            }

            it("should return player total credit amount for 2 positive entries in GameLedger") {
                assertThat(gameLedger.calculatePlayerBalance(mockPlayer).amount).isEqualTo(startingBalance + passGoAward)
            }
        }

        context("when player balance is negative") {

            val gameLedger = GameLedger()
            val visitFulfilmentSiteFee = 20
            val purchaseFulfilmentSitePrice = 100

            val mockFulfilmentSite = mockk<FulfilmentSite>()
            every { mockFulfilmentSite.visitorFeeOrAward } returns visitFulfilmentSiteFee
            every { mockFulfilmentSite.purchasePrice } returns purchaseFulfilmentSitePrice

            val mockPlayer1 = mockk<Player>()
            val mockPlayer2 = mockk<Player>()

            gameLedger.payVisitorFee(
                location = mockFulfilmentSite,
                locationOwner = mockPlayer2,
                feePayer = mockPlayer1
            )

            gameLedger.buyLocation(
                location = mockFulfilmentSite,
                buyer = mockPlayer1
            )

            it("should return player in debt for a negative total entry in GameLedger") {
                assertThat(gameLedger.calculatePlayerBalance(mockPlayer1)).isInstanceOf(Debt::class.java)
            }

            it("should return player total debt amount for negative total entries in GameLedger") {
                assertThat(gameLedger.calculatePlayerBalance(mockPlayer1).amount).isEqualTo(visitFulfilmentSiteFee + purchaseFulfilmentSitePrice)
            }
        }

        context("when player balance is 0") {
            val gameLedger = GameLedger()
            val mockPlayer = mockk<Player>()

            it("should return player in credit for 0 balance") {
                assertThat(gameLedger.calculatePlayerBalance(mockPlayer)).isInstanceOf(Credit::class.java)
            }

            it("should return player credit amount of 0") {
                assertThat(gameLedger.calculatePlayerBalance(mockPlayer).amount).isEqualTo(0)
            }
        }
    }

    describe("getPremisesOwnedBy()") {
        it("should return empty list if player owns no locations") {
            val gameLedger = GameLedger()
            val mockPlayer = mockk<Player>()

            assertThat(gameLedger.getPremisesOwnedBy(mockPlayer)).isEqualTo(emptyList<Location>())
        }

        it("should return list of single Location owned by player") {
            val gameLedger = GameLedger()
            val mockPlayer = mockk<Player>()
            val mockFulfilmentSite = mockk<FulfilmentSite>()
            every { mockFulfilmentSite.purchasePrice } returns 100

            gameLedger.buyLocation(mockFulfilmentSite, mockPlayer)

            assertThat(gameLedger.getPremisesOwnedBy(mockPlayer).size).isEqualTo(1)
            assertThat(gameLedger.getPremisesOwnedBy(mockPlayer)).isEqualTo(listOf(mockFulfilmentSite))
        }

        it("should return list of 2 Locations owned by player") {
            val gameLedger = GameLedger()
            val mockPlayer = mockk<Player>()

            val mockFulfilmentSite = mockk<FulfilmentSite>()
            every { mockFulfilmentSite.purchasePrice } returns 100

            val mockRetailSite = mockk<RetailSite>()
            every { mockRetailSite.purchasePrice } returns 150

            gameLedger.buyLocation(mockFulfilmentSite, mockPlayer)
            gameLedger.buyLocation(mockRetailSite, mockPlayer)

            assertThat(gameLedger.getPremisesOwnedBy(mockPlayer).size).isEqualTo(2)
            assertThat(gameLedger.getPremisesOwnedBy(mockPlayer)).isEqualTo(listOf(mockFulfilmentSite, mockRetailSite))
        }
    }
})