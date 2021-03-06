package org.slieb.runtimes.rhino;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.slieb.runtimes.Runtimes.getBoolean;


public class RhinoRuntimeTest {


    RhinoRuntime runtime;

    @Before
    public void setupRuntime() {
        runtime = new RhinoRuntime();
    }

    @After
    public void tearDownRuntime() {
        runtime.close();
    }

    @Test
    public void testInitializesWithoutComplaint() throws Exception {
        runtime.initialize();
    }

    @Test
    public void testStoresValues() throws Exception {
        runtime.initialize();
        runtime.execute("var x = true;");
        assertTrue(getBoolean(runtime, "x"));
        assertFalse(getBoolean(runtime, "!x"));
    }


}