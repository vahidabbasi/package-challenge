package com.assignment.knapsack;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class Package {
    private int maxWeight;
    private List<Item> items;
}
