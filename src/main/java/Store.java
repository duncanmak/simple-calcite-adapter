import java.util.List;

public class Store {
    List<Integer> numbers;

    public Store(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public void insert(int n) {
        this.numbers.add(n);
    }

    public void remove(int idx) {
        this.numbers.remove(idx);
    }
}
