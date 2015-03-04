import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Fibonacci {

    public static void main(String[] args) {

        int input = Integer.parseInt(args[0]);

        System.out.println(immutableFibonacci(input));

        System.out.println(mutableFibonacci(input));

        System.out.println(recursiveFibonacci(input));
    }

    private static ArrayList<Integer> concatenateFibSeries(final List<Integer> fib) {
        final int next = (fib.size() > 1 ? fib.get(fib.size() - 1) + fib.get(fib.size() - 2) : 1);
        final ArrayList<Integer> fibSeries = Stream.concat(fib.stream(),
                Stream.of(next)).collect(Collectors.toCollection(ArrayList::new));
        return fibSeries;
    }

    private static List<Integer> immutableFibonacci(final int position) {
        List<Integer> fibSeries = Stream.generate(() -> (new ArrayList<Integer>()))
                .limit(position)
                .reduce(new ArrayList<>(),
                        (fib, b) -> (concatenateFibSeries(fib)));
        return fibSeries;
    }

    private static List<Integer> mutableFibonacci(int position) {
        List<Integer> fibSeries = new ArrayList<>();
        for (int i = 0; i < position; i++) {
            if (i > 1) {
                fibSeries.add((fibSeries.get(i - 1) + (fibSeries.get(i - 2))));
            } else {
                fibSeries.add(1);
            }
        }
        return fibSeries;
    }

    private static List<Integer> recursiveFibonacci(final int position) {
        return recursiveFibonacciHelper(new ArrayList<>(), position);
    }

    private static List<Integer> recursiveFibonacciHelper(List<Integer> fibSeries, int position) {
        if (position <= 0) {
            return fibSeries;
        } else {
            final int size = fibSeries.size();
            List<Integer> newFibSeries = (Stream.concat(fibSeries.stream(),
                    Stream.of(size > 1 ? (fibSeries.get(size - 1) + (fibSeries.get(size - 2))) : 1))
                    .collect(Collectors.toList()));
            return recursiveFibonacciHelper(newFibSeries, position - 1);
        }
    }
}
