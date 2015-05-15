package org.slieb.runtimes;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slieb.runtimes.rhino.RhinoRuntime;

import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;

import static org.junit.Assert.*;
import static org.slieb.runtimes.Runtimes.*;

public class RuntimesTest {

    RhinoRuntime runtime;

    @Before
    public void setUp() throws Exception {
        runtime = new RhinoRuntime();
        runtime.initialize();
    }

    @After
    public void tearDown() throws Exception {
        runtime.close();
    }

    @Test
    public void testGetBooleanFromJsRuntime() throws Exception {
        runtime.execute("var x = true; var y = false;");
        assertTrue(getBoolean(runtime, "x"));
        assertFalse(getBoolean(runtime, "y"));
    }

    @Test
    public void testGetStringFromJsRuntime() throws Exception {
        runtime.execute("var x = 'content';");
        assertEquals("content", Runtimes.getString(runtime, "x"));
    }

    @Test
    public void testGetIntegerFromJsRuntime() throws Exception {
        runtime.execute("var x = 1.0;");
        assertEquals(Integer.valueOf(1), getInteger(runtime, "x"));
    }

    @Test
    public void testEvaluateReader() throws Exception {
        runtime.execute("var x = 1;");
        try (Reader reader = new StringReader("x = 2;")) {
            evaluateReader(runtime, reader, "/some/path.js");
        }
        assertEquals(Integer.valueOf(2), getInteger(runtime, "x"));
    }

    @Test
    public void testEvaluateInputStream() throws Exception {
        runtime.execute("var x = 1;");
        try (InputStream inputStream = IOUtils.toInputStream("x = 2;")) {
            evaluateInputStream(runtime, inputStream, "/some/path.js");
        }
        assertEquals(Integer.valueOf(2), getInteger(runtime, "x"));
    }

    @Test
    @Ignore
    public void testEvaluateResource() throws Exception {
        throw new RuntimeException("ignored");
    }

    @Test
    @Ignore
    public void testEvaluateURL() throws Exception {

    }

    @Test
    @Ignore
    public void testEvaluateURI() throws Exception {

    }

    @Test
    @Ignore
    public void testEvaluateFile() throws Exception {

    }
}