package net.petrabarus.pdfwatermarker

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class AppTest {

    @Test
    fun shouldShowHelp() {
        val app = App(arrayOf("--help"))
        app.parseArgs()

        assertEquals(true, app.help)
    }

    @Test
    fun shouldHandleFileInputParams() {
        val app = App(arrayOf(
                "./src/test/resources/PDFSample.pdf",
                "./src/test/resources/shape.png"
        ))
        app.parseArgs()

        assertTrue(app.pdfFile.isFile)
        assertTrue(app.stampFile.isFile)
        assertEquals(app.pdfFile, app.outputFile)
    }

    @Test
    fun shouldHandleOutputFileParams() {
        val outputFile = createTempFile()
        val app = App(arrayOf(
                "-o ",
                outputFile.absolutePath,
                "./src/test/resources/PDFSample.pdf",
                "./src/test/resources/shape.png"
        ))
        app.parseArgs()

        assertTrue(app.pdfFile.isFile)
        assertTrue(app.stampFile.isFile)
        assertNotEquals(app.pdfFile, app.outputFile)
    }
}
