package interfaces;

public interface Executor<T,R> {
  void execute(T value);
  R getResult();
}

