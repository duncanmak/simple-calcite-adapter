import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Main(args).run();
    }
    Store store;
    public Main(String[] args) {
        List<Integer> numbers = new ArrayList<>(args.length);
        for (String s : args) numbers.add(Integer.parseInt(s));

        this.store = new Store(numbers);


    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        while (true) {
            if (line.equals("exit")) System.exit(0);
        }
    }
}