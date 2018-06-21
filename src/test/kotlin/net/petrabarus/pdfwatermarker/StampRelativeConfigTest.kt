package net.petrabarus.pdfwatermarker

import com.tngtech.java.junit.dataprovider.DataProvider
import com.tngtech.java.junit.dataprovider.DataProviderRunner
import com.tngtech.java.junit.dataprovider.DataProviders.`$$`
import com.tngtech.java.junit.dataprovider.DataProviders.`$`
import com.tngtech.java.junit.dataprovider.UseDataProvider
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.common.PDRectangle
import org.apache.pdfbox.pdmodel.graphics.image.PDImage
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@RunWith(DataProviderRunner::class)
class StampRelativeConfigTest {

    companion object {

        @DataProvider
        @JvmStatic
        fun constructDataProvider(): Array<Array<Any>> = `$$`(
                // @formatter:off
                `$`( 595, 842,  2952, 1248, 297.5, 125.7, 148.75, 358.15),
                `$`( 595, 842,  1248, 2952, 177.9, 421, 208.55, 210.5)
                // @formatter:on
        )
    }

    @Test
    @UseDataProvider(value = "constructDataProvider")
    fun construct(
            pageWidth: Double,
            pageHeight: Double,
            stampWidth: Double,
            stampHeight: Double,
            expectedNewStampWidth: Double,
            expectedNewStampHeight: Double,
            expectedStampX: Double,
            expectedStampY: Double
    ) {
        val pageMock = mock(PDPage::class.java)
        val pageMediaBox = mock(PDRectangle::class.java)
        `when`(pageMediaBox.width).thenReturn(pageWidth.toFloat())
        `when`(pageMediaBox.height).thenReturn(pageHeight.toFloat())
        //
        `when`(pageMock.mediaBox).thenReturn(pageMediaBox)

        val stampImage = mock(PDImage::class.java)
        `when`(stampImage.width).thenReturn(stampWidth.toInt())
        `when`(stampImage.height).thenReturn(stampHeight.toInt())

        val config = StampRelativeConfig(pageMock, stampImage)

        Assert.assertEquals(expectedNewStampWidth, config.stampWidth, 0.1)
        Assert.assertEquals(expectedNewStampHeight, config.stampHeight, 0.1)
        Assert.assertEquals(expectedStampX, config.x, 0.1)
        Assert.assertEquals(expectedStampY, config.y, 0.1)
    }
}
