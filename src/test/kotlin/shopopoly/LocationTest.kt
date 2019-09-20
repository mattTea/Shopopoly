package shopopoly

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object LocationTest : Spek({
    describe("Location type") {
        val location = Location(
            purchasePrice = -200,
            costToBuildMinistore = -300,
            costToBuildSupermarket = -400,
            costToBuildMegastore = -500,
            visitorFeeOrAward = -50,
            rent = Rent.SUPERMARKET,
            owningGroup = "mattTea Corp"
        )

        it("should have purchasePrice of £200") {
            assertThat(location.purchasePrice).isEqualTo(-200)
        }

        it("should have costToBuildMinistore of £300") {
            assertThat(location.costToBuildMinistore).isEqualTo(-300)
        }

        it("should have costToBuildSupermarket of £400") {
            assertThat(location.costToBuildSupermarket).isEqualTo(-400)
        }

        it("should have costToBuildMegastore of £500") {
            assertThat(location.costToBuildMegastore).isEqualTo(-500)
        }

        it("should have visitorFeeOrAward of £50") {
            assertThat(location.visitorFeeOrAward).isEqualTo(-50)
        }

        it("should have rent of £30") {
            assertThat(location.rent.amount).isEqualTo(-30)
        }

        it("should have owningGroup of mattTea Corp") {
            assertThat(location.owningGroup).isEqualTo("mattTea Corp")
        }
    }
})