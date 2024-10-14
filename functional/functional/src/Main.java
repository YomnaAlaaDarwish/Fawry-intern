import java.util.function.Function;

public class Main {
    // Function that accepts a Function as a parameter
    public static void processFunction(int value, Function<Integer, Integer> func) {
        // Apply the function
        int result = func.apply(value);
        System.out.println("Result: " + result);
    }

    // Function that returns a Function
    public static Function<Integer, Integer> createMultiplier(int factor) {
        return (x) -> x * factor;
    }

    public static void main(String[] args) {
        //assign function to variables
        Function<Integer, Integer> multiplyByTwo = (x) -> x * 2;

        // Passing a lambda as a function
        processFunction(5, multiplyByTwo);  // Output: Result: 10

        // Get a function that multiplies by 3
        Function<Integer, Integer> multiplier = createMultiplier(3);

        // Use the returned function
        System.out.println(multiplier.apply(5));  // Output: 15
    }
}
