package org.block1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

public class SetupFile {

    public static List<String[]> readFile(Path path) {
        String line;
        String[] values;
        List<String[]> rowsList = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path.toFile(), StandardCharsets.UTF_8));

            while ((line = reader.readLine()) != null) {
                values = line.split(":");
                System.out.println(line);

                if (line.chars().filter(c -> c == ':').count() == 2) rowsList.add(values);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rowsList;
    }

    public static List<String[]> cleanRows(List<String[]> rowsList) throws InvalidPropertiesFormatException {
        String[] rowArr;
        List<String[]> listClean = new ArrayList<>();

        for (String[] strings : rowsList) {
            rowArr = strings;
            String name = rowArr[0];
            String town = rowArr[1];


            if (name.isEmpty() || name.isBlank())
                throw new InvalidPropertiesFormatException("There must be name in person");
            if (town.isEmpty()) town = "Unknown";
            // TODO: RESIZE ARRAY -> 105 JAVA COURSE


            listClean.add(rowArr);
        }

        return listClean;
    }
}

