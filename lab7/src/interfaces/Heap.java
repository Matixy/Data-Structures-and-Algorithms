package interfaces;

public interface Heap<T> {
  public void clear(); // metoda czyszcząca kopiec (usuwającą wszystkie elementy)
  public void add(T element); // metoda wstawiająca nowy element do kopca
  public T minimum(); // metoda zwracająca minimalny element kopca wraz z jego usunięciem
  public int size(); // metoda zwracajaca rozmiar kopca
}
