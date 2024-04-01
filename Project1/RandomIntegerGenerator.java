package Project1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class RandomIntegerGenerator {
    public static void main(String[] args) {
        int arraySize = 20000;
        int[] randomIntegers = generateRandomIntegers(arraySize);

        // Specify the file path
        String filePath = "random_integers.txt";

        // Write the random integers to the text file
        writeToFile(filePath, randomIntegers);

        System.out.println("Random integers have been written to: " + filePath);
    }

    private static int[] generateRandomIntegers(int size) {
        int[] randomIntegers = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            randomIntegers[i] = random.nextInt();
        }

        return randomIntegers;
    }

    private static void writeToFile(String filePath, int[] randomIntegers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int number : randomIntegers) {
                writer.write(Integer.toString(number));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

