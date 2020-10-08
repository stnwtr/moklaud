package at.stnwtr.moklaud;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class StringUtility {

  private static char toCase(char c, boolean upper) {
    return upper ? Character.toUpperCase(c) : Character.toLowerCase(c);
  }

  public static String alternate(String input) {
    StringBuilder stringBuilder = new StringBuilder();
    AtomicInteger counter = new AtomicInteger(0);

    input.chars()
        .mapToObj(value -> Character.isLetter(value) ? toCase((char) value, counter.getAndIncrement() % 2 == 0) : (char) value)
        .forEach(stringBuilder::append);

    return stringBuilder.toString();
  }

  public static String mock(String input) {
    StringBuilder stringBuilder = new StringBuilder();

    input.chars()
        .mapToObj(value -> toCase((char) value, ThreadLocalRandom.current().nextBoolean()))
        .forEach(stringBuilder::append);

    return stringBuilder.toString();
  }
}
