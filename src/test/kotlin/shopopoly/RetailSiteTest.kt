package shopopoly

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object RetailSiteTest : Spek({
    describe("RetailSite location type") {

        val retailSite = RetailSite(
            name = "John's shop",
            purchasePrice = 125,
            costToBuildMinistore = 50,
            costToBuildSupermarket = 100,
            costToBuildMegastore = 150,
            visitorFeeOrAward = -25
        )

        it("should have a name of 'John's shop'") {
            assertThat(retailSite.name).isEqualTo("John's shop")
        }

        it("should have a purchase price of £125") {
            assertThat(retailSite.purchasePrice).isEqualTo(125)
        }

        it("should cost £50 to build a ministore") {
            assertThat(retailSite.costToBuildMinistore).isEqualTo(50)
        }

        it("should cost £100 to build a supermarket") {
            assertThat(retailSite.costToBuildSupermarket).isEqualTo(100)
        }

        it("should cost £150 to build a megastore") {
            assertThat(retailSite.costToBuildMegastore).isEqualTo(150)
        }

        it("should cost £25 to visit the site") {
            assertThat(retailSite.visitorFeeOrAward).isEqualTo(-25)
        }

        it("should cost £10 to rent the undeveloped site by default") {
            assertThat(retailSite.rent).isEqualTo(Rent.UNDEVELOPED)
            assertThat(retailSite.rent.amount).isEqualTo(-10)
        }

        it("should be owned by 'Unattached' by default") {
            assertThat(retailSite.owningGroup).isEqualTo("Unattached")
        }
    }
})