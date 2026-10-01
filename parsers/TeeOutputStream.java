package parsers;

import java.io.IOException;
import java.io.OutputStream;

public class TeeOutputStream extends OutputStream {
  private final OutputStream one;
  private final OutputStream two;

  public TeeOutputStream(OutputStream one, OutputStream two) {
    this.one = one;
    this.two = two;
  }

  @Override
  public void write(int b) throws IOException {
    one.write(b);
    two.write(b);
  }

  @Override
  public void flush() throws IOException {
    one.flush();
    two.flush();
  }

  @Override
  public void close() throws IOException {
    one.close();
    two.close();
  }
}
