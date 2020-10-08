package at.stnwtr.moklaud;

import java.util.function.Function;

public enum Formatter {

  SHOUT("shout", String::toUpperCase),
  WHISPER("whisper", String::toLowerCase),
  TRIM("trim", String::trim),
  ALTERNATE("alternate", StringUtility::alternate),
  MOCK("mock", StringUtility::mock),
  INVALID("invalid", input -> "invalid");

  private final String name;
  private final Function<String, String> mapper;

  Formatter(String name,
      Function<String, String> mapper) {
    this.name = name;
    this.mapper = mapper;
  }

  public static Formatter byName(String name) {
    for (Formatter formatter : values()) {
      if (formatter.name.equals(name)) {
        return formatter;
      }
    }
    return INVALID;
  }
}
