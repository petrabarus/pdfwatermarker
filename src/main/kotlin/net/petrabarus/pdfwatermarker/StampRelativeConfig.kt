package net.petrabarus.pdfwatermarker

import java.lang.IllegalArgumentException
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.graphics.image.PDImage

class StampRelativeConfig {

    companion object {
        const val HORIZONTAL_POSITION_CENTER = 0;

        const val HORIZONTAL_POSITION_LEFT = 1

        const val HORIZONTAL_POSITION_RIGHT = 2

        const val VERTICAL_POSITION_CENTER = 0;

        const val VERTICAL_POSITION_TOP = 1

        const val VERTICAL_POSITION_BOTTOM = 2
    }

    var ratioStampHeightToPageHeight = 0.5

    var ratioStampWidthToPageWidth = 0.5

    var keepStampRatio = true

    var relativeHorizontalPosition = HORIZONTAL_POSITION_CENTER

    var relativeVerticalPosition = VERTICAL_POSITION_CENTER

    var x = 0.0
        private set

    var y = 0.0
        private set

    var stampHeight = 0.0
        private set

    var stampWidth = 0.0
        private set

    var opacity = 0.5f

    private var originalStampHeight: Double = 0.0

    private var originalStampWidth: Double = 0.0

    private var pageHeight: Double = 0.0

    private var pageWidth: Double = 0.0

    constructor(page: PDPage, stamp: PDImage) {
        originalStampHeight = stamp.height.toDouble()
        originalStampWidth = stamp.width.toDouble()
        pageHeight = page.mediaBox.height.toDouble()
        pageWidth = page.mediaBox.width.toDouble()

        val stampDimension = calculateStampDimension()

        stampWidth = stampDimension.first
        stampHeight = stampDimension.second
        x = calculateX()
        y = calculateY()
    }

    /**
     * Return pair of stampWidth and stampHeight, respectively
     */
    private fun calculateStampDimension()
            : Pair<Double, Double> {

        val newStampHeight = pageHeight * ratioStampHeightToPageHeight
        val newStampWidth = pageWidth * ratioStampWidthToPageWidth

        if (keepStampRatio) {
            return calculateAdjustedRatio(newStampWidth, newStampHeight)
        }

        return Pair(newStampWidth, newStampHeight)
    }

    /**
     * Return pair of stampWidth and stampHeight, respectively
     */
    private fun calculateAdjustedRatio(newStampWidth: Double, newStampHeight: Double)
            : Pair<Double, Double> {
        val widthToHeightRatio = originalStampWidth / originalStampHeight
        val newWidthToHeightRatio = newStampWidth / newStampHeight
        var adjustedHeight = newStampHeight
        var adjustedWidth = newStampWidth
        if (widthToHeightRatio > newWidthToHeightRatio) {
            adjustedHeight = originalStampHeight * (newStampWidth / originalStampWidth)
        } else {
            adjustedWidth = originalStampWidth * (newStampHeight / originalStampHeight)
        }
        return Pair(adjustedWidth, adjustedHeight)
    }

    private fun calculateY()
            = when(relativeVerticalPosition) {
        VERTICAL_POSITION_CENTER
            -> (pageHeight - stampHeight) / 2
        VERTICAL_POSITION_TOP
            -> throw UnsupportedOperationException("Not implemented")
        VERTICAL_POSITION_BOTTOM
            -> throw UnsupportedOperationException("Not implemented")
        else
            -> throw IllegalArgumentException("Option not recognized: $relativeVerticalPosition")
    }

    private fun calculateX()
            = when(relativeHorizontalPosition) {
        HORIZONTAL_POSITION_CENTER
            -> (pageWidth - stampWidth) / 2
        HORIZONTAL_POSITION_LEFT
            -> throw UnsupportedOperationException("Not implemented")
        HORIZONTAL_POSITION_RIGHT
            -> throw UnsupportedOperationException("Not implemented")
        else
            -> throw IllegalArgumentException("Option not recognized: $relativeHorizontalPosition")
    }
}
