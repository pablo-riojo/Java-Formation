package org.block1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Person> listPerson = new ArrayList<>();
        Path myPath = Paths.get("C:\\Users\\pablo.riojo\\Desktop\\Java-Formation\\JF\\block1-process-file-and-streams\\Persons.csv");
        readFile(myPath);
    }

    public static void readFile(Path path) {
        String line;
        String[] values;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path.toFile(), StandardCharsets.UTF_8));

            while ((line = reader.readLine()) != null) {
                values = line.split(":");

                for (int i = 0; i < values.length; i++) {
                    System.out.println(values[i]);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}