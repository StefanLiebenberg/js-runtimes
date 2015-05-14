package org.slieb.runtimes;


/**
 * A interface to a javascript runtime.
 */
public interface JavascriptRuntime {

    Object execute(String command, String sourceName);

    default Object execute(String command) {
        return execute(command, ":inline");
    }
}
