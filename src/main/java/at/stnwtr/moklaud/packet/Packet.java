package at.stnwtr.moklaud.packet;

import java.util.Objects;

public abstract class Packet {

  protected String input;

  public Packet(String input) {
    this.input = input;
  }

  public String getInput() {
    return input;
  }

  public void setInput(String input) {
    this.input = input;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Packet packet = (Packet) o;
    return Objects.equals(input, packet.input);
  }

  @Override
  public int hashCode() {
    return Objects.hash(input);
  }

  @Override
  public String toString() {
    return "Packet{" +
        "input='" + input + '\'' +
        '}';
  }
}
