package Stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args){
        String[] arr1 = {"1", "2", "3"};
        Stream<String> stream1 = Arrays.stream(arr1);


        List<String> arr2 = Arrays.asList("1", "2", "3");
        Stream<String> stream2 = arr2.stream();

        Stream<String> stream3 = Stream.<String> builder().add("1").add("2").add("3").build();

        IntStream stream4 = IntStream.range(0, 5);

        Stream<String> stream5 = Stream.generate(() -> "1").limit(1);

        Stream<Integer> stream6 = Stream.iterate(0, n->n+3).limit(3); // 0, 3, 6

        Stream<Integer> stream7 = Stream.iterate(0, n->n+3).limit(3).parallel();
        Stream<String> stream8 = arr2.parallelStream();



        // sum은 기본타입만 처리해준다. Integer라면 Integer::valueOf 적용 후 사용
        int result1 = IntStream.range(0, 5).sum();
        System.out.println(result1);
        List<Integer> array = Arrays.asList(1, 2, 3, 4);
        int result2 = array.stream().mapToInt(Integer::valueOf).skip(2).sum();
        System.out.println("result2 : " + result2);


        int sum = array.stream().filter(arr -> arr % 2 == 0).mapToInt(Integer::intValue).sum();
        System.out.println(sum);

        array.stream().mapToDouble(Double::valueOf);

        array.stream().sorted().mapToInt(Integer :: valueOf).average();
        array.stream().sorted(Comparator.comparing(arr -> -arr));
        List<String> array2 = new ArrayList<>();
        array2.stream().sorted();
        HashSet<String> a = array2.stream().sorted(Comparator.comparing(arr -> arr.length())).collect(Collectors.toCollection(HashSet::new));

        String b = array2.stream().collect(Collectors.joining());

        Map<Integer, List<String>> result = array2.stream().collect(Collectors.groupingBy(String::length));
        array2.stream().allMatch(arr -> arr.length() >= 2);


    }
}
