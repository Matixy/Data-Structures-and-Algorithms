package executors;

import interfaces.Executor;

public class PrintTreeExecutor implements Executor<String, String> {
  private final StringBuilder result = new StringBuilder();

  @Override
  public void execute(String line) {
    result.append(line).append("\n");
  }

  @Override
  public String getResult() {
    return result.toString();
  }
}
