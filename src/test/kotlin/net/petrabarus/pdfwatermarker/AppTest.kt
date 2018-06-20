package net.petrabarus.pdfwatermarker

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AppTest {

    @Test
    fun shouldShowHelp() {
        val app = App(arrayOf("--help"))
        assertEquals(true, app.help)
    }

    @Test
    fun shouldHandleFileInputParams() {
        val app = App(arrayOf(
                "./src/test/resources/PDFSample.pdf",
                "./src/test/resources/shape.png"
        ))
        assertTrue(app.pdfFile.isFile)
        assertTrue(app.stampFile.isFile)
    }
}
