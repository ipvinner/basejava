package core.java8.lambda;

import java.util.Arrays;

public class LambdaExamples {
    public static void main(String[] args) {
        Arrays.asList( "a", "b", "d" ).forEach(System.out::println);
        Arrays.asList( "a", "b", "d" ).forEach(( String e ) -> System.out.println( e ));
    }
}
