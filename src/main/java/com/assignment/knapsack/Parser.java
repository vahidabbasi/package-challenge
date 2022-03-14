package com.assignment.knapsack;


import com.assignment.exception.InputConstraintsViolatedException;
import com.assignment.util.PackageChallengeUtils;
import lombok.Data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static com.assignment.util.PackageChallengeUtils.*;

@Data
public class Parser {

    private String inputPath;
    public Parser(String inputPath) {
        this.inputPath = inputPath;
    }

    List<Package> createTestScenarios() throws InputConstraintsViolatedException {
        List<Package> testScenarios = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputPath))) {
            String line = bufferedReader.readLine();
            int lineNo = 0;
            while (line != null) {
                lineNo++;
                testScenarios.add(parseInputLine(lineNo, line));
                line = bufferedReader.readLine();
            }

        } catch (IOException e) {
            throw new InputConstraintsViolatedException(e);
        }

        return testScenarios;
    }

    private Package parseInputLine(int lineNumber, String line) {
        // run the default test inputs if no cmd args is given
        int maxWeight;
        List<Item> items = new ArrayList<>();

        // divide each input line to two parts based on the delimiter `:`
        String[] lineParts = line.split(SEMI_COLON);
        if (lineParts.length != 2) {
            throw new InputConstraintsViolatedException(String.format("Line %s in input file must contain one " +
                    "semi-colon only.", lineNumber));
        }

        // step 1: read the left part into `this.maxWeight`
        try {
            String leftPart = lineParts[0].trim();
            maxWeight = (int) (Double.parseDouble(leftPart) * 100);
        } catch (NumberFormatException e) {
            throw new InputConstraintsViolatedException(String.format("Left side of : in line %s input file must be a" +
                    " number.", lineNumber));
        }

        if (maxWeight > MAX_WEIGHT ) {
            throw new InputConstraintsViolatedException(String.format("max weight in line %s input file must be in " +
                            "range (0, %s)",
                    lineNumber, MAX_WEIGHT/100));
        }

        // step 2: read the right part into `List<Item> item`
        String rightPart = lineParts[1].trim();
        String[] stringItems = rightPart.split(SPACE);

        if (stringItems.length > MAX_ITEMS_PER_LINE) {
            throw new InputConstraintsViolatedException(String.format("Number of items in line %s in input file must be " +
                    "in range (1, %d)", lineNumber, MAX_ITEMS_PER_LINE));
        }

        for (String str : stringItems) {
            Pattern pattern = Pattern.compile(REGEX);
            if (!pattern.matcher(str).matches()) {
                throw new InputConstraintsViolatedException(String.format("mal-formed item input in line %d in input file ",
                        lineNumber));
            }

            String stringItem = str.trim();
            stringItem = PackageChallengeUtils.removeLeadingChar(stringItem, OPENINGS_CHAR);
            stringItem = PackageChallengeUtils.removeTrailingChar(stringItem, CLOSING_CHAR);
            String[] stringItemParts = stringItem.split(COMMA);

            if (stringItemParts.length != 3) {
                throw new InputConstraintsViolatedException(String.format("mal-formed item input in line %s. Each tuple item must contain exactly three parts", lineNumber));
            }

            int itemId = Integer.parseInt(stringItemParts[0].trim());
            int weight = (int) (Double.parseDouble(stringItemParts[1]) * 100);
            double cost = Double.parseDouble(PackageChallengeUtils.removeLeadingChar(stringItemParts[2], EURO)) * 100;

            if (cost > MAX_VALUE || cost < 0) {
                throw new InputConstraintsViolatedException(String.format("item cost/value in line %s input file for item with id %s " +
                        "must be in range (0, %s)", lineNumber, itemId,  MAX_VALUE/100));
            }

            if (weight > MAX_WEIGHT || weight < 0) {
                throw new InputConstraintsViolatedException(String.format("item weight in line %s in input file " +
                        "for item with id %s must be in range (0, %s)", lineNumber, itemId, MAX_WEIGHT/100));
            }

            Item item = new Item(itemId, weight, cost);
            items.add(item);
        }

        return Package.builder()
                .maxWeight(maxWeight)
                .items(items)
                .build();
    }

}
