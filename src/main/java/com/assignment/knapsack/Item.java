package com.assignment.knapsack;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {
    private int id;
    private int weight;
    private double cost;
}
