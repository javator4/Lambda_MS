package pl.sda.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@FunctionalInterface
interface Square {
    int square(int x);
}

public class App {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Kasia", "Ania", "Zosia", "Bartek");
//        Collections.sort(names, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o1.compareTo(o2);
//
//                }
//            });
//        System.out.println(names);

        Collections.sort(names, (String s1, String s2) -> s1.compareTo(s2));
        System.out.println(names);


        Collections.sort(SampleData.membersOfTheBeatles, new Comparator<Artist>() {
            @Override
            public int compare(Artist o1, Artist o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        System.out.println(SampleData.membersOfTheBeatles);

        Square square = (int x) -> x * x;
        System.out.println(square.square(2));

        // LAMBDA JAKO PĘTLA

        String[] a = {"cat", "dog", "mouse", "rat", "pig", "rabbit", "hamster", "parrot"};
        List<String> animals = Arrays.asList(a);
        // Tradycyjna pętla
        for(String animal : animals) {
            System.out.print(animal +"; ");
        }
        // Wyrażenie lambd
        animals.forEach((animal) -> System.out.print(animal +"; "));
        animals.forEach(System.out::println);

        // LAMBA JAKO STRUMIEŃ
        List<String> fruits = Arrays.asList("apple","banana","cherry","plum","pear","pinapple");
        fruits.stream()
                .filter((x) -> x.startsWith("a"))
                .map((x) -> x.toUpperCase())
                .forEach(System.out::println);

        List<String> dataWithNumbers = Arrays.asList("a", "a123", "23", "b32ss", "3a");
        dataWithNumbers
                .stream()
                .filter(x -> Character.isDigit(x.charAt(0)))
                .collect(Collectors.toList());

        List<Integer> flat = Stream.of(Arrays.asList(2,3,4), Arrays.asList(33,32,34))
                .flatMap(numbers -> numbers.stream())
                .collect(Collectors.toList());

        List<Track> tracks = Arrays.asList(
                new Track("Bakai", 524),
                new Track("haha", 120),
                new Track("Jujujuj :D", 450));

        Track minTrack = tracks.stream().min(Comparator.comparing(track -> track.getLenght())).get();
        System.out.println(minTrack);
    }
}

