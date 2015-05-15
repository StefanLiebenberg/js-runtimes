package org.slieb.runtimes;


import com.google.common.base.Preconditions;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URI;
import java.net.URL;

public class Runtimes {

    /**
     * This fetches object as a boolean from runtime.
     *
     * {@code
     * runtime.execute("x = true"); // do some javascript stuff
     * Boolean value = Runtimes.getBoolean(runtime, "x"); // will return true.
     * }
     *
     * @param runtime The javascript runtime.
     * @param command Command that should return the expected value.
     * @return Returns a boolean result gained from executing the command.
     */
    public static Boolean getBoolean(JavascriptRuntime runtime, String command) {
        return (Boolean) runtime.execute(command);
    }

    /**
     * This fetches object a string from runtime.
     *
     * {@code
     * runtime.execute("var x = 'value';"); // do sometime javascript stuff.
     * String value = Runtimes.getString(runtime, "x"); // will return 'value';
     * }
     *
     * @param runtime The javascript runtime.
     * @param command An executable javascript command
     * @return The string result of executing the command on the runtime.
     */
    public static String getString(JavascriptRuntime runtime, String command) {
        return (String) runtime.execute(command);
    }

    /**
     * {@code
     * runtime.execute("var x = 1;");
     * Integer intValue = Runtimes.getInteger("x");
     * }
     *
     * @param runtime The javascript runtime.
     * @param command The javascript command to execute to get a integer result.
     * @return the result gained from executing the javascript command.
     */
    public static Integer getInteger(JavascriptRuntime runtime, String command) {
        Number number = (Number) runtime.execute(command);
        if (number != null) {
            return number.intValue();
        } else {
            return null;
        }
    }

    /**
     * @param runtime The javascript runtime.
     * @param reader  A Reader that will supply javascript executable code.
     * @param path    The filename that this reader will be read as.
     * @return Any object that the javascript executable code returns.
     * @throws IOException throws an ioException if there is trouble reading the reader.
     */
    public static Object evaluateReader(JavascriptRuntime runtime, Reader reader, String path) throws IOException {
        return runtime.execute(IOUtils.toString(reader), path);
    }

    /**
     * @param runtime The javascript runtime.
     * @param stream  An InputStream that will supply the javascript executable code.
     * @param path    The filename path executable code will be run as.
     * @return Any object the executable code returns.
     * @throws IOException Throws an ioException of there is trouble reading the inputStream.
     */
    public static Object evaluateInputStream(JavascriptRuntime runtime, InputStream stream, String path) throws IOException {
        try (Reader reader = new InputStreamReader(stream)) {
            return evaluateReader(runtime, reader, path);
        }
    }

    /**
     * @param runtime     The javascript runtime.
     * @param classLoader The classloader to use.
     * @param resource    The resource path to extract from the classResource.
     * @return Any object the executable code runs.
     * @throws IOException throws an ioException if there is any trouble reading the resource from the class loader.
     */
    public static Object evaluateClassResource(JavascriptRuntime runtime, ClassLoader classLoader, String resource) throws IOException {
        try (InputStream inputStream = classLoader.getResourceAsStream(resource)) {
            Preconditions.checkNotNull(inputStream, "%s is not found.", resource);
            return evaluateInputStream(runtime, inputStream, resource);
        }
    }

    /**
     * @param runtime A Javascript Runtime.
     * @param url     The url where javascript executable code is located.
     * @return An object.
     * @throws IOException when there isd an error reading from the url.
     */
    public static Object evaluateURL(JavascriptRuntime runtime, URL url) throws IOException {
        try (InputStream inputStream = url.openStream()) {
            return evaluateInputStream(runtime, inputStream, url.getPath());
        }
    }

    /**
     * @param runtime A javascript runtime.
     * @param uri     The uri where javascript content could be run from.
     * @param path    The path we intend to open this uri as.
     * @return an Object.
     * @throws IOException when there is an error reading from the uri.
     */
    public static Object evaluateURI(JavascriptRuntime runtime, URI uri, String path) throws IOException {
        return evaluateURL(runtime, uri.toURL());
    }

    /**
     * @param runtime The javascript runtime.
     * @param file    The file that contains executable javascript code.
     * @return An object.
     * @throws IOException when there is an error reading the contents of file.
     */
    public static Object evaluateFile(JavascriptRuntime runtime, File file) throws IOException {
        try (Reader reader = new FileReader(file)) {
            return evaluateReader(runtime, reader, file.getPath());
        }
    }

}
