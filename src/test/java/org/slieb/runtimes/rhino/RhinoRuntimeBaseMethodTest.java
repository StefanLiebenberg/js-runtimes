package org.slieb.runtimes.rhino;

import org.junit.Test;
import org.mozilla.javascript.Undefined;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class RhinoRuntimeBaseMethodTest {

    /**
     * Create a goog.base method and check awesomeness.
     */
    @Test
    public void testBase() {
        try (RhinoRuntime runtime = new RhinoRuntime()) {
            runtime.initialize();
            runtime.execute("var goog = { base : function () { return arguments.callee.caller; } };");
            runtime.execute("function Y () { return goog.base(); } ");
            Object result = runtime.execute("Y()");
            assertNotNull(result);
            assertNotEquals(Undefined.instance, result);
        }
    }
}
