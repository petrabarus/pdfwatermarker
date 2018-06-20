package net.petrabarus.pdfwatermarker

import com.beust.jcommander.JCommander
import com.beust.jcommander.Parameter
import com.beust.jcommander.ParameterException
import java.io.File

fun main(args: Array<String>) {

    val app = App(args)

    try {
        app.parseArgs()
    } catch (e: ParameterException) {
        System.err.println(e.message)
        app.printUsage()
        return
    }

    if (app.help) {
        app.printUsage()
        return
    }

    app.run()
}

class App(private val args: Array<String>) {

    @Parameter(names = ["-o", "--output"], required = false, description = "Output of the result, if empty will overwrite the PDF file")
    var outputFilePath: String? = null

    @Parameter(names = ["--help"], help = true)
    var help = false

    //TODO: Validator
    @Parameter(arity = 2, description = "<Path to PDF file> <Path to PNG stamp file>")
    lateinit var params: List<String>

    private var jCommander: JCommander = JCommander.newBuilder()
            .addObject(this)
            .build()

    val pdfFile: File
        get() = File(params[0])

    val stampFile: File
        get() = File(params[1])

    val outputFile: File
        get() = if (outputFilePath != null) File(outputFilePath) else pdfFile

    fun parseArgs() {
        jCommander.parse(*args)
    }

    fun printUsage() {
        jCommander.usage()
    }

    fun run () {
        val watermarker = Watermarker(pdfFile, stampFile, outputFile)
        watermarker.watermark()
    }
}
