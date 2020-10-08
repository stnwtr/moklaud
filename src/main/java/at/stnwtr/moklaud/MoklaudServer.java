package at.stnwtr.moklaud;

import at.stnwtr.moklaud.packet.IncomingPacket;
import at.stnwtr.moklaud.packet.OutgoingPacket;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import java.util.concurrent.locks.ReentrantLock;

public class MoklaudServer {

  private final int port;

  private final Javalin app;

  private final ReentrantLock lock;

  private final ObjectMapper mapper;

  public MoklaudServer(int port) {
    this.port = port;
    this.app = Javalin.create();
    this.lock = new ReentrantLock();
    this.mapper = new ObjectMapper();

    addRoutes();

    start();
    Runtime.getRuntime().addShutdownHook(new Thread(this::stop));
  }

  private void addRoutes() {
    for (Formatter value : Formatter.values()) {
      if (!value.isRoute()) {
        continue;
      }
      app.post(value.getName(), ctx -> {
        try {
          lock.lock();
          IncomingPacket packet = mapper.readValue(ctx.body(), IncomingPacket.class);
          lock.unlock();
          ctx.json(new OutgoingPacket(packet.getInput(), Formatter.byName(ctx.path())));
        } catch (JsonProcessingException e) {
          lock.unlock();
          ctx.json(OutgoingPacket.INVALID);
        }
      });

      app.get(value.getName(), ctx -> {
        String input = ctx.queryParam("input");
        if (input == null) {
          ctx.json(OutgoingPacket.INVALID);
          return;
        }
        ctx.json(new OutgoingPacket(input, Formatter.byName(ctx.path())));
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
