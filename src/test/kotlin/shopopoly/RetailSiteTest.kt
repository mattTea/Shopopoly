package shopopoly

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.mockk.mockk
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object RetailSiteTest : Spek({
    describe("RetailSite location type") {

        context("for undeveloped retail site") {

            val retailSite = RetailSite(
                name = "John's shop",
                purchasePrice = 125,
                costToBuildMinistore = 50,
                costToBuildSupermarket = 100,
                costToBuildMegastore = 150
            )

            it("should have a name of 'John's shop'") {
                assertThat(retailSite.name).isEqualTo("John's shop")
            }

            it("should have a purchase price of £125") {
                assertThat(retailSite.purchasePrice).isEqualTo(125)
            }

            it("should cost £50 to build a ministore") {
                assertThat(retailSite.calculateCostToBuild(Store.MINISTORE)).isEqualTo(50)
            }

            it("should cost £100 to build a supermarket") {
                assertThat(retailSite.calculateCostToBuild(Store.SUPERMARKET)).isEqualTo(100)
            }

            it("should cost £150 to build a megastore") {
                assertThat(retailSite.calculateCostToBuild(Store.MEGASTORE)).isEqualTo(150)
            }

            it("should cost £10 to visit the site") {
                assertThat(retailSite.visitorFeeOrAward).isEqualTo(10)
            }

            it("should cost £10 to rent the undeveloped site by default") {
                assertThat(retailSite.visitorFeeOrAward).isEqualTo(Store.UNDEVELOPED.amount)
            }

            it("should be owned by 'Unattached' by default") {
                assertThat(retailSite.owner).isEqualTo("Unattached")
            }
        }

        context("for a developed retail site") {

            val retailSite = RetailSite(
                name = "Jill's shop",
                purchasePrice = 125,
                costToBuildMinistore = 50,
                costToBuildSupermarket = 100,
                costToBuildMegastore = 150
            )

            val mockPlayer = mockk<Player>()

            retailSite.setStoreTypeAndVisitorFee(Store.MINISTORE)

            it("should change store type to MINISTORE when ministore is built") {
                assertThat(retailSite.store).isEqualTo(Store.MINISTORE)
            }

            it("should change visitorFeeOrAward to £25 when ministore is built") {
                assertThat(retailSite.visitorFeeOrAward).isEqualTo(Store.MINISTORE.amount)
            }
        }
    }
})