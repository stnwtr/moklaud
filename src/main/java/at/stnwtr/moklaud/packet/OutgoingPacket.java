package at.stnwtr.moklaud.packet;

import at.stnwtr.moklaud.Formatter;
import java.util.Objects;

public class OutgoingPacket extends Packet {

  public static final OutgoingPacket INVALID = new OutgoingPacket(Formatter.INVALID.getName(),
      Formatter.INVALID);

  private String output;

  public OutgoingPacket(String input, Formatter formatter) {
    super(input);
    this.output = formatter.format(input);
  }

  public String getOutput() {
    return output;
  }

  public void setOutput(String output) {
    this.output = output;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    OutgoingPacket that = (OutgoingPacket) o;
    return Objects.equals(output, that.output);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), output);
  }

  @Override
  public String toString() {
    return "OutgoingPacket{" +
        "output='" + output + '\'' +
        '}';
  }
}
