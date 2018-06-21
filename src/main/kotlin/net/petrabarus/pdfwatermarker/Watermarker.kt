package net.petrabarus.pdfwatermarker

import java.io.File
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject
import org.apache.pdfbox.pdmodel.PDPageContentStream
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState


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
        val contentStream = PDPageContentStream(pdfDoc, pdPage, PDPageContentStream.AppendMode.APPEND, true, true)
        contentStream.use {
            setOpacity(it, config.opacity)
            drawStamp(it, config)
        }
    }

    private fun setOpacity(contentStream: PDPageContentStream, opacity: Float) {
        val extendedGraphicsState = PDExtendedGraphicsState()
        extendedGraphicsState.nonStrokingAlphaConstant = opacity
        contentStream.setGraphicsStateParameters(extendedGraphicsState)
    }

    private fun drawStamp(contentStream: PDPageContentStream, config: StampRelativeConfig) {
        val x = config.x.toFloat()
        val y = config.y.toFloat()
        val height = config.stampHeight.toFloat()
        val width = config.stampWidth.toFloat()

        contentStream.drawImage(stampImage, x, y, width, height)
    }
}
