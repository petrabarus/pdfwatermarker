package net.petrabarus.pdfwatermarker

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

    var ratioHeightToPageHeight = 0.5

    var ratioWidthToPageWidth = 0.5

    var keepImageRatio = true

    var relativeHorizontalPosition = HORIZONTAL_POSITION_CENTER

    var relativeVerticalPosition = VERTICAL_POSITION_CENTER

    var x = 0.0
        private set

    var y = 0.0
        private set

    var height = 0.0
        private set

    var width = 0.0
        private set

    constructor(page: PDPage, image: PDImage) {
        val imageDimension = calculateImageDimension(page, image)
        val imageWidth = imageDimension.first
        val imageHeight = imageDimension.second
        x = calculateX(page.mediaBox.width.toDouble(), imageWidth)
        y = calculateY(page.mediaBox.height.toDouble(), imageHeight)
    }


    private fun calculateImageDimension(page: PDPage, image: PDImage)
            : Pair<Double, Double> {
        return Pair(0.toDouble(), 0.toDouble())
    }

    private fun calculateY(pageHeight: Double, imageHeight: Double)
            = when(relativeVerticalPosition) {
        VERTICAL_POSITION_CENTER -> 0.0 //TODO
        VERTICAL_POSITION_TOP -> 0.0 //TODO
        VERTICAL_POSITION_BOTTOM -> 0.0 //TODO
        else -> 0.0
    }

    private fun calculateX(pageWidth: Double, imageWidth: Double)
            = when(relativeHorizontalPosition) {
        HORIZONTAL_POSITION_CENTER -> 0.0 //TODO
        HORIZONTAL_POSITION_LEFT -> 0.0 //TODO
        HORIZONTAL_POSITION_RIGHT -> 0.0 //TODO
        else -> 0.0
    }
}
