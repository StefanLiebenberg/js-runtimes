package org.slieb.runtimes.rhino;

import org.slieb.runtimes.Runtimes;

import java.io.IOException;
import java.io.InputStream;


public class EnvJSRuntime extends RhinoRuntime {

    private static final String
            ENV_RHINO_PATH = "/org/slieb/runtimes/rhino/env.rhino.js",
            ENV_LOAD_PATH = "/org/slieb/runtimes/rhino/load.rhino.js",
            ENV_WAIT_JS = "Envjs.wait(%s);";

    public void initialize() {
        super.initialize();
        try (InputStream inputStream = EnvJSRuntime.class.getResourceAsStream(ENV_RHINO_PATH)) {
            Runtimes.evaluateInputStream(this, inputStream, ENV_RHINO_PATH);
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    public void doLoad() {
        try (InputStream inputStream = EnvJSRuntime.class.getResourceAsStream(ENV_LOAD_PATH)) {
            Runtimes.evaluateInputStream(this, inputStream, ENV_LOAD_PATH);
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
        doWait();
    }

    public void doWait(int delay) {
        execute(String.format(ENV_WAIT_JS, delay));
    }

    public void doWait() {
        execute(String.format(ENV_WAIT_JS, ""));
    }

    @Override
    public void close() {
        super.close();
    }


}
