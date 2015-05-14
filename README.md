

# js-runtimes


## Interface

 JavaScriptRuntime is a simple interface intented for use in libraries that want to remain agnostic as to which runtime they are operating on.

## Implementations:



###RhinoRuntime

The rhino runtime provides a wrapper around the rhino javascript engine.  

```java
  RhinoRuntime runtime = new RhinoRuntime();
  runtime.initialize();
  runtime.execute("var x = 1;", "/path.js");
  runtime.execute("x;"); // 1
  runtime.close();
```

###EnvJsRuntime

This runtime extends the rhino runtime and adds envjs support for the DOM.

```java
  EnvJsRuntime runtime = new EvnJsRuntime();
  runtime.initialize();
  // do stuff with runtime
  runtime.close();
```

