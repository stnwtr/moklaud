package at.stnwtr.moklaud;

import io.javalin.Javalin;

public class MoklaudServer {

  private final int port;

  private final Javalin app;

  public MoklaudServer(int port) {
    this.port = port;
    this.app = Javalin.create();

    addRoutes();

    start();
    Runtime.getRuntime().addShutdownHook(new Thread(this::stop));
  }

  private void addRoutes() {
    for (Formatter value : Formatter.values()) {
      app.post("/" + value.name(), ctx -> {
        // json body
      });

      app.get("/" + value.name(), ctx -> {
        // query param
      });
    }
  }

  public void start() {
    app.start("0.0.0.0", port);
  }

  public void stop() {
    app.stop();
  }
}
