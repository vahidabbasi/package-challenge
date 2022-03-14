package com.assignment.knapsack;

import java.util.*;
import java.util.stream.Collectors;

import static com.assignment.util.PackageChallengeUtils.COMMA;
import static com.assignment.util.PackageChallengeUtils.DASH;

/**
 *  A Dynamic Programming (DP) Solution for the Knapsack / Package Problem
 */
public class Packer {
    // optimal items to store in the package
    private List<Item> items;

    public void findAndStoreOptimalCombinationOfItems(List<Item> items, int maxWeight){

        this.items = new ArrayList<>();

        // step 1: find the maximum value/cost that can be put in a package of `maxWeight` capacity
        int n = items.size();

        // current index, weight
        int i, w;

        // create dynamic programming (DP) value table
        double [][] itemWeightMatrix = new double [n + 1][maxWeight + 1];

        for (i = 1; i<= n; i++) {
            Item currentItem = items.get(i-1);

            for (w = 1; w<= maxWeight; w++) {
                if (currentItem.getWeight() <= w){
                    // decide whether to choose item `i` by getting the maximum
                    // possible value of the two cases where `i` is and is NOT selected
                    itemWeightMatrix[i][w] = Math.max(itemWeightMatrix[i - 1][w],
                         itemWeightMatrix[i - 1][w - currentItem.getWeight()] + currentItem.getCost() );
                }
                else{
                    // if item `i` with weight `w` is not selected,
                    // then itemWeightMatrix[i][w] is the maximum possible value by selecting among
                    // packages 1 to i-1 with the same weight limit `w`, i.e., itemWeightMatrix[i-1][w]
                    itemWeightMatrix[i][w] = itemWeightMatrix[i - 1][w];
                }
            }
        }

        // given `n` items, the optimal total value/cost of items 
        // put into a package of capacity `maxWeight` is itemWeightMatrix[n][W]
        double optimalCost = itemWeightMatrix[n][maxWeight];


        // step 2: find the selected items and add them to the package
        w = maxWeight;
        while(w > 0 && itemWeightMatrix[n][w-1] == optimalCost ){
            w--;
        }

        // check if item `i` was selected or not 
        for(i = n; i > 0; i--){
            if(itemWeightMatrix[i][w] != itemWeightMatrix[i-1][w]){
                Item currentItem = items.get(i-1);
                this.items.add(currentItem);
                w = w - currentItem.getWeight();
            }
        }
    }

    public String getOptimalPackageIds(){
        String optimalPackages = this.items.stream()
                .mapToInt(Item::getId)
                .sorted()
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(COMMA));

        return optimalPackages.isEmpty()? DASH : optimalPackages;
    }
}
