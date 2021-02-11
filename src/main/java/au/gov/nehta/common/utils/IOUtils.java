package au.gov.nehta.common.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Utility class that common I/O functions.
 */
public final class IOUtils {

    /*
     * Size of buffer array for read() methods.
     */
    private static final int CHAR_BUFFER_SIZE = 1024;

    /**
     * Reads the content of a file into a string.
     *
     * @param inputFile File to read from. Cannot be null.
     * @return Text content from the file.
     * @throws IOException Thrown when the file cannot be found, or there are I/O errors
     *                     reading from the file.
     */
    public static String read(File inputFile) throws IOException {
        assert (inputFile != null) : "'inputFile' is null.";

        return read(new FileReader(inputFile));
    }

    /**
     * Reads the content of a byte stream into a string. The byte stream will be
     * closed after the contents are read.
     *
     * @param inputStream Byte stream to read from. Cannot be null.
     * @return Text content from the byte stream.
     * @throws IOException Thrown when there are I/O errors reading from the byte stream.
     */
    public static String read(InputStream inputStream) throws IOException {
        assert (inputStream != null) : "'inputStream' is null.";

        return read(new InputStreamReader(inputStream));
    }

    /**
     * Reads the content of a character stream into a string. The character stream
     * will be closed after the contents are read.
     *
     * @param reader Character stream to read from. Cannot be null.
     * @return Text content from the character stream.
     * @throws IOException Thrown when there are I/O errors reading from the character
     *                     stream.
     */
    public static String read(Reader reader) throws IOException {
        assert (reader != null) : "'reader' is null.";

        try {
            StringWriter sw = new StringWriter();
            char[] buffer = new char[CHAR_BUFFER_SIZE];
            int readIdx = reader.read(buffer, 0, buffer.length);
            while (readIdx >= 0) {
                sw.write(buffer, 0, readIdx);
                readIdx = reader.read(buffer, 0, buffer.length);
            }
            sw.flush();
            return sw.toString();
        } finally {
            reader.close();
        }
    }

    /**
     * Writes text content to a file.
     *
     * @param outputFile File to write to. Cannot be null.
     * @param contents   Text content to write to the file. Cannot be null nor a blank
     *                   string.
     * @throws IOException Thrown when there are I/O errors writing to the file.
     */
    public static void write(File outputFile, String contents) throws IOException {
        assert (outputFile != null) : "'outputFile' is null.";
        assert (contents != null) : "'contents' is null.";
        assert (contents.trim().length() > 0) : "'contents' is a blank string.";

        write(new FileWriter(outputFile), contents);
    }

    /**
     * Writes text content to a character stream.
     *
     * @param outputStream Character stream to write to. Cannot be null.
     * @param contents     Text content to write to the character stream. Cannot be null nor
     *                     a blank string.
     * @throws IOException Thrown when there are I/O errors writing to the character stream.
     */
    public static void write(OutputStream outputStream, String contents)
            throws IOException {
        assert (outputStream != null) : "'outputStream' is null.";
        assert (contents != null) : "'contents' is null.";
        assert (contents.trim().length() > 0) : "'contents' is a blank string.";

        write(new OutputStreamWriter(outputStream), contents);
    }

    /**
     * Writes text content to a byte stream.
     *
     * @param writer   Byte stream to write to. Cannot be null.
     * @param contents Text content to write to the byte stream. Cannot be null nor
     *                 a blank string.
     * @throws IOException Thrown when there are I/O errors writing to the byte stream.
     */
    public static void write(Writer writer, String contents) throws IOException {
        assert (writer != null) : "'writer' is null.";
        assert (contents != null) : "'contents' is null.";
        assert (contents.trim().length() > 0) : "'contents' is a blank string.";

        try {
            BufferedWriter bw = new BufferedWriter(writer);
            bw.append(contents);
            bw.flush();
        } finally {
            writer.close();
        }
    }

    /*
     * Private constructor to prevent instantiation of a utility class.
     */
    private IOUtils() {
    }

}