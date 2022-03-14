# How to build and run
go to the root of the project and run
1) mvn clean install 
2) mvn exec:java -Dexec.mainClass="com.assignment.knapsack.PackageChallengeMain" -Dexec.args="src/main/resources/input.txt"


# Problem explanation
The knapsack problem is a problem in combinatorial optimization: Given a set of items, each with a weight and a value,
determine the number of each item to include in a collection so that the total weight is less than or equal to a given 
limit, and the total value is as large as possible.

# Methods to solve knapsack problem
Various algorithms can be used to solve this problem namely Greedy Algorithm or dynamic programming.

# Compression of algorithms
### Dynamic Programming:

Dynamic algorithm is an algorithm design method, which can be used when the problem breaks down into simpler sub-problems.
Wherever there is a recursive solution that has repeated calls for the same inputs, it can be optimized by using dynamic
programming. The idea is to simply store the results of sub-problems so that they do not have to be re-computed when 
needed later.

#### Time Complexity: O (N*W).
where ‘N’ is the number of weight elements and ‘W’ is the capacity of the knapsack.

### Greedy Algorithm:
Greedy is an algorithmic method that builds up a solution piece by piece, always choosing the next piece that offers the
most obvious and immediate benefit. So the problems where choosing locally optimal solutions also lead to the global 
solution are best fit for Greedy.

#### Time Complexity: O(n*logn)
If using a simple sort algorithm (selection, bubble) then the complexity of the whole problem is O(n²).
If using quick sort or merge sort then the complexity of the whole problem is) O(n*logn)
As the main time taking step is sorting, the whole problem can be solved in O(n*logn) only.
Sometimes not optimal.


### My Solution:
A greedy algorithm seems to be the most efficient (time complexity), but it fails to give the correct optimal solution for 
the 0/1 knapsack problem. The Dynamic programming technique is also very efficient, and the most favorable algorithm to 
solve the 0/1 knapsack problem in general, but the memory utilized by this technique is the highest in compression 
with Greedy algorithm.

I used dynamic programing for solving this problem where I define following classes:
1) InputConstraintsViolatedException.class where I define the exception that will be thrown in runtime if validation 
is failed 
2) Item.class used to model each item
3) Package.class used to model knapsack
4) PackageChallengeMain main class for running
5) Packer.class find the optimal combination of items 
6) Parser.class to parse the input file
7) PackageChallengeUtils.class some utils methods

### Improvements:
define validation class and move all validation that is done inside Parser.class in different class
add more log in code 


