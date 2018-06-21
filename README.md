# PDF Watermarker

## Usage

To execute the tool

```text
java -jar pdfwatermarker.jar [options] <pdf file> <stamp file>
```

Example

```text
java -jar pdfwatermarker.jar \
    --output test.pdf \
    src/test/resources/PDFSample.pdf \
    src/test/resources/shape.png
```

To see list of available helps

```text
java -jar pdfwatermarker.jar --help
```

## Building

To build the project, execute

```bash
mvn install
```
