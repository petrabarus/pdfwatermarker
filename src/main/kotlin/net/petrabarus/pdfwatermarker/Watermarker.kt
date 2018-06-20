package net.petrabarus.pdfwatermarker

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject
import java.io.File
import org.apache.pdfbox.pdmodel.PDPageContentStream

class Watermarker (
        val pdfFile: File,
        val stampFile: File,
        val outputFile: File = pdfFile
){

    private val pdfDoc = PDDocument.load(pdfFile)

    private val stampImage = PDImageXObject
            .createFromFileByContent(stampFile, pdfDoc)

    fun watermark() {
        pdfDoc.use {
            it.pages.mapIndexed { index, pdPage ->
                watermarkPage(pdPage)
            }

            pdfDoc.save(outputFile)
        }
    }

    private fun watermarkPage(pdPage: PDPage) {
        val config = StampRelativeConfig(pdPage, stampImage)
        val contentStream = PDPageContentStream(pdfDoc, pdPage)

        contentStream.use {
            val x = config.x.toFloat()
            val y = config.y.toFloat()
            val height = config.height.toFloat()
            val width = config.width.toFloat()

            it.drawImage(stampImage, x, y, width, height)
        }
    }
}