package at.stnwtr.moklaud;

import java.util.function.Function;

public enum Formatter {

  SHOUT("/shout", String::toUpperCase),
  WHISPER("/whisper", String::toLowerCase),
  TRIM("/trim", String::trim),
  ALTERNATE("/alternate", StringUtility::alternate),
  MOCK("/mock", StringUtility::mock),
  INVALID("invalid", input -> "invalid", false);

  private final String name;
  private final Function<String, String> mapper;
  private final boolean route;

  Formatter(String name, Function<String, String> mapper, boolean route) {
    this.name = name;
    this.mapper = mapper;
    this.route = route;
  }

  Formatter(String name, Function<String, String> mapper) {
    this(name, mapper, true);
  }

  public String getName() {
    return name;
  }

  public String format(String input) {
    return mapper.apply(input);
  }

  public boolean isRoute() {
    return route;
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
