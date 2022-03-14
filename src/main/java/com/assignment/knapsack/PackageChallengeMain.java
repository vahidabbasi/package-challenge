package com.assignment.knapsack;

import com.assignment.exception.InputConstraintsViolatedException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.util.List;

public class PackageChallengeMain {

    private static final Logger logger = Logger.getLogger(PackageChallengeMain.class);

    public static void main(String[] args) throws InputConstraintsViolatedException {
        BasicConfigurator.configure();

        if (args.length != 1){
            logger.error("Please provide file location contain test scenarios");
            System.exit(1);
        }

        Parser parser = new Parser(args[0]);
        List<Package> testScenarios = parser.createTestScenarios();
        // instantiate a package for each test case
        Packer packItems = new Packer();

        System.out.println("***********************************************************************");
        System.out.println("Optimal packages for input file:");
        for (Package testScenario: testScenarios) {
            packItems.findAndStoreOptimalCombinationOfItems(testScenario.getItems(),
                    testScenario.getMaxWeight());
            // print the final output for each line
            System.out.println(packItems.getOptimalPackageIds());
        }
        System.out.println("***********************************************************************");
    }
}
