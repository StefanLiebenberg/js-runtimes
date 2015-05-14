package org.slieb.runtimes.webdriver;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.slieb.runtimes.JavascriptRuntime;


public class RemoteWebDriverRuntime implements JavascriptRuntime {

    private final RemoteWebDriver remoteWebDriver;

    public RemoteWebDriverRuntime(RemoteWebDriver remoteWebDriver) {
        this.remoteWebDriver = remoteWebDriver;
    }

    @Override
    public Object execute(String command, String sourceName) {
        return this.remoteWebDriver.executeScript(command);
    }
    
}
