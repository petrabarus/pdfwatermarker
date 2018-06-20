package net.petrabarus.pdfwatermarker

import com.beust.jcommander.JCommander
import com.beust.jcommander.Parameter
import java.io.File

fun main(args: Array<String>) {

    val app = App(args)

    if (app.help) {
        app.printUsage()
        return
    }
}

class App(args: Array<String>) {

    @Parameter(names = arrayOf("-o", "--output"), required = false, description = "Output of the result, if empty will overwrite the PDF file")
    lateinit var outputFilePath: String

    @Parameter(names = arrayOf("--help"), help = true)
    var help = false

    //TODO: Validator
    @Parameter(description = "<Path to PDF file> <Path to PNG stamp file>")
    lateinit var params: String

    private var jCommander: JCommander = JCommander.newBuilder()
            .addObject(this)
            .build()
    init {
        jCommander.parse(args.joinToString(" "))
    }

    val pdfFile: File
        get() = File(params.split(" ")[0])

    val stampFile: File
        get() = File(params.split(" ")[1])

    val outputFile: File
        get() = if (outputFilePath != null) File(outputFilePath) else pdfFile

    fun printUsage() {
        jCommander.usage()
    }
}
