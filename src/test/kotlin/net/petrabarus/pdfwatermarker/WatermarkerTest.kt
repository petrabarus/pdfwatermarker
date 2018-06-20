package net.petrabarus.pdfwatermarker

import org.junit.Test
import java.io.File

class WatermarkerTest
{
    @Test
    fun testWatermark() {
        val pdfFile = File(this.javaClass.getResource("/PDFSample.pdf").file)
        val outputFile = createTempFile("PDFSample-", ".pdf")
        val stampFile = File(this.javaClass.getResource("/shape.png").file)
        val watermarker = Watermarker(
                pdfFile = pdfFile,
                stampFile = stampFile,
                outputFile = outputFile
        )
        watermarker.watermark()
    }
}