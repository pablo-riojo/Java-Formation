package org.block1;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.block1.SetupFile.cleanRows;
import static org.block1.SetupFile.readFile;

public class Main {
    private static List<Person> nameStartsWithA;

    public static void main(String[] args) throws InvalidPropertiesFormatException {
         Path myPath = Paths.get(
                "C:\\Users\\pablo.riojo\\Desktop\\Java-Formation\\JF\\block1-process-file-and-streams\\Persons.csv"
         );
         ArrayList<Person> listPerson = new ArrayList<>();

        List<String[]> cleanRows = cleanRows(readFile(myPath));

        for (String[] row : cleanRows) {
            Person person = new Person();

            person.setName(row[0]);
            person.setTown(row[1]);
            person.setAge((row.length < 3) ? 0 : Integer.parseInt(row[2]));

            listPerson.add(person);
        }

        System.out.println("AGE NOT 0: ");
        listPerson.stream()
                .filter(person -> person.getAge() != 0)
                .forEach(person -> System.out.println(person));

        System.out.println("AGE LESS THAN 25: ");
        List<Person> ageFiltered = listPerson.stream()
                .filter(person -> person.getAge() < 25)
                .collect(Collectors.toList());
        ageFiltered.forEach(person -> System.out.println(person));

        System.out.println("NAME STARTS WITH 'A': ");
        nameStartsWithA = listPerson.stream()
                .filter(person -> !person.getName().startsWith("A"))
                        .collect(Collectors.toList());
        nameStartsWithA.forEach(person -> System.out.println(person));

        System.out.println("TOWN IS MADRID: ");
        Optional<Person> Madrid = listPerson.stream().filter(person -> person.getTown().equals("Madrid")).findFirst();
        System.out.println(Madrid);

        System.out.println("TOWN IS BARCELONA: ");
        Optional<Person> Barcelona = listPerson.stream().filter(person -> person.getTown().equals("Barcelona")).findFirst();
        System.out.println(Barcelona);
    }
}

