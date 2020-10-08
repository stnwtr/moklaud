package at.stnwtr.moklaud;

import java.security.InvalidParameterException;

public class Launcher {

  public static final int MIN_PORT = 1025;
  public static final int MAX_PORT = (int) (Math.pow(2, 16) - 1);
  public static final int DEFAULT_PORT = 7001;

  public static final String USAGE =
      "Usage: ./moklaud.jar <port:" + Launcher.MIN_PORT + "-" + MAX_PORT + ":"
          + Launcher.DEFAULT_PORT + ">";

  public static void main(String[] args) {
    int port = 0;

    if (args.length == 1) {
      try {
        port = Integer.parseInt(args[0]);
        if (port < MIN_PORT || port > MAX_PORT) {
          throw new InvalidParameterException();
        }
      } catch (NumberFormatException | InvalidParameterException e) {
        System.out.println(Launcher.USAGE);
        System.exit(0);
      }
    }

    new MoklaudServer(port == 0 ? DEFAULT_PORT : port);
  }
}
