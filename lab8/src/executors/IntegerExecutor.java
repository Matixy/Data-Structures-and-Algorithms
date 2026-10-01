package executors;

import interfaces.Executor;

import java.util.ArrayList;
import java.util.List;

public class IntegerExecutor implements Executor<Integer, List<Integer>> {
  private List<Integer> list;

  public IntegerExecutor() {
    list = new ArrayList<Integer>();
  }

  @Override
  public void execute(Integer value) {
    list.add(value);
  }

  @Override
  public List<Integer> getResult() {
    return list;
  }
}
