import java.util.*;
import java.io.*;

class DSAAlgorithms {

  static Scanner scanner = new Scanner(System.in);


  public static int getInt(String prompt) {
    while (true) {
      try {
        System.out.print(prompt);
        return scanner.nextInt();
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter an integer.");
        scanner.next(); // clear invalid input
      }
    }
  }

  public static double[] getDoubleArrayFromUser() {
    int n = getInt("Enter array size: ");
    double[] arr = new double[n];
    System.out.println("Enter " + n + " numbers (integers or decimals):");
    for (int i = 0; i < n; i++) {
      while (true) {
        try {
          System.out.print("Element " + (i + 1) + ": ");
          arr[i] = scanner.nextDouble();
          break;
        } catch (InputMismatchException e) {
          System.out.println("Invalid input. Please enter a number.");
          scanner.next();
        }
      }
    }
    return arr;
  }

  public static int[] getIntArrayFromUser() {
    int n = getInt("Enter array size: ");
    int[] arr = new int[n];
    System.out.println("Enter " + n + " integers:");
    for (int i = 0; i < n; i++) {
      arr[i] = getInt("Element " + (i + 1) + ": ");
    }
    return arr;
  }

  public static int[][] getMatrixFromUserDynamic() {
    int rows = getInt("Enter number of rows: ");
    int cols = getInt("Enter number of columns: ");
    int[][] matrix = new int[rows][cols];

    System.out.println("Enter matrix elements row by row:");
    for (int i = 0; i < rows; i++)
      for (int j = 0; j < cols; j++)
        matrix[i][j] = getInt("Element [" + i + "][" + j + "]: ");

    return matrix;
  }

  public static int[] readArrayFromFile(String filename) throws FileNotFoundException {
    Scanner fileScanner = new Scanner(new File(filename));
    List<Integer> list = new ArrayList<>();
    while (fileScanner.hasNextInt()) list.add(fileScanner.nextInt());
    fileScanner.close();
    return list.stream().mapToInt(i -> i).toArray();
  }





  /*
Flowcharts for Algorithms:

1. Factorial
Start -> Input n -> Is n < 0? -> Yes -> Print 'undefined' -> End
No -> Initialize result = 1 -> Loop i=2 to n -> result *= i -> Print result -> End

2. Binary Length
Start -> Input n -> Initialize count = 1 -> While n > 1 -> count++ and n = n / 2 -> Print count -> End

3. Unique Elements
Start -> Input array -> Initialize set and duplicates -> Loop through array -> If in set, add to duplicates -> Else add to set -> Remove duplicates from set -> Print set -> End

4. Sequential Search
Start -> Input array and key -> Loop through array -> If array[i] == key -> Print index -> End
If not found -> Print 'not found' -> End

5. Max Element (Decimal)
Start -> Input array -> Initialize max = array[0] -> Loop through array -> If array[i] > max -> max = array[i] -> Print max -> End

6. Matrix Multiplication
Start -> Input matrix A and B -> If A.columns != B.rows -> Print 'Error' -> End
Initialize result matrix -> Triple nested loop to compute product -> Print result -> End

7. Gaussian Elimination
Start -> Input augmented matrix -> For each row, make upper triangular by row operations -> Print triangular matrix -> Back-substitute to get variables -> Print solution -> End


----------------------------------
Time Complexity Analysis (Big O):

1. Factorial:
   - Best Case: O(n)
   - Average Case: O(n)
   - Worst Case: O(n)

2. Binary Length:
   - Best Case: O(1)
   - Average Case: O(log n)
   - Worst Case: O(log n)

3. Unique Elements:
   - Best Case: O(1) (duplicate found early)
   - Average Case: O(n^2)
   - Worst Case: O(n^2) (all elements are unique)

4. Sequential Search:
   - Best Case: O(1) (key at beginning)
   - Average Case: O(n)
   - Worst Case: O(n) (key not found)

5. Max Element:
   - Best Case: O(n)
   - Average Case: O(n)
   - Worst Case: O(n)

6. Matrix Multiplication:
   - Best Case: O(n × m × p)
   - Average Case: O(n × m × p)
   - Worst Case: O(n × m × p)
   (where n, m, p are the matrix dimensions)

7. Gaussian Elimination:
   - Best Case: O(n^3)
   - Average Case: O(n^3)
   - Worst Case: O(n^3)
*/



  //Algorithms

  public static Integer factorialIterative(int n) {
    if (n < 0) {
      return null;
    }
    int result = 1;
    for (int i = 2; i <= n; i++) result *= i;
    return result;
  }

  public static int binLengthIterative(int n) {
    if (n < 0) {
      System.out.println("Binary length is undefined for negative integers.");
      return -1;
    }
    if (n == 0) {
      return 1;
    }

    int count = 0;
    while (n > 0) {
      count++;
      n /= 2;
    }
    return count;
  }

  public static int binLengthRecursive(int n) {
    if (n < 0) {
      System.out.println("Binary length is undefined for negative integers.");
      return -1;
    }
    if (n <= 1) return 1;
    return 1 + binLengthRecursive(n / 2);
  }


  public static void printUniqueElements(int[] arr) {
    Set<Integer> seen = new HashSet<>();
    Set<Integer> duplicates = new HashSet<>();
    for (int num : arr) {
      if (!seen.add(num)) {
        duplicates.add(num);
      }
    }
    seen.removeAll(duplicates);

    if (seen.isEmpty()) {
      System.out.println("No unique elements found.");
    } else {
      System.out.println("Unique elements:");
      for (int num : seen) {
        System.out.print(num + " ");
      }
      System.out.println();
    }
  }

  public static int sequentialSearch(int[] arr, int key) {
    for (int i = 0; i < arr.length; i++)
      if (arr[i] == key) return i;
    return -1;
  }

  public static double maxElement(double[] arr) {
    double max = arr[0];
    for (int i = 1; i < arr.length; i++)
      if (arr[i] > max) max = arr[i];
    return max;
  }

  public static int[][] matrixMultiplicationFlexible(int[][] A, int[][] B) {
    int rowsA = A.length;
    int colsA = A[0].length;
    int rowsB = B.length;
    int colsB = B[0].length;

    if (colsA != rowsB) {
      System.out.println("Cannot multiply: columns of A must match rows of B.");
      return null;
    }

    int[][] result = new int[rowsA][colsB];
    for (int i = 0; i < rowsA; i++) {
      for (int j = 0; j < colsB; j++) {
        for (int k = 0; k < colsA; k++) {
          result[i][j] += A[i][k] * B[k][j]; /*iterate through the cols of A and B and multiply*/
        }
      }
    }
    return result;
  }

  public static void gaussianElimination(double[][] matrix) {
    int n = matrix.length;
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        double ratio = matrix[j][i] / matrix[i][i];
        for (int k = 0; k <= n; k++) {
          matrix[j][k] -= ratio * matrix[i][k];
        }
      }
    }

    System.out.println("Upper triangular matrix:");
    for (double[] doubles : matrix) {
      for (int j = 0; j <= n; j++) {
        System.out.printf("%.2f\t", doubles[j]);
      }
      System.out.println();
    }

    double[] x = new double[n];
    for (int i = n - 1; i >= 0; i--) {
      x[i] = matrix[i][n];
      for (int j = i + 1; j < n; j++) {
        x[i] -= matrix[i][j] * x[j];
      }
      x[i] /= matrix[i][i];
    }

    System.out.println("Solution:");
    for (int i = 0; i < n; i++) {
      System.out.printf("x%d = %.2f\n", i + 1, x[i]);
    }
  }





  //Menu
  public static void main(String[] args) {
    while (true) {
      System.out.println("\nSelect an algorithm to run:");
      System.out.println("1. Factorial");
      System.out.println("2. Binary Length");
      System.out.println("3. Check Unique Elements");
      System.out.println("4. Sequential Search");
      System.out.println("5. Max Element");
      System.out.println("6. Matrix Multiplication");
      System.out.println("7. Gaussian Elimination");
      System.out.println("0. Exit");

      int choice = getInt("Enter your choice: ");
      switch (choice) {
        case 1: {
          int n = getInt("Enter a number: ");
          Integer result = factorialIterative(n);
          if (result == null)
            System.out.println("Factorial is not defined for negative numbers.");
          else
            System.out.println("Factorial: " + result);
          break;
        }
        case 2: {
          int n = getInt("Enter a number: ");
          System.out.println("Choose binary length method:");
          System.out.println("1. Iterative");
          System.out.println("2. Recursive");
          int methodChoice = getInt("Enter choice: ");
          if (methodChoice == 1) {
            System.out.println("Binary Length (Iterative): " + binLengthIterative(n));
          } else if (methodChoice == 2) {
            System.out.println("Binary Length (Recursive): " + binLengthRecursive(n));
          } else {
            System.out.println("Invalid method choice.");
          }
          break;
        }
        case 3: {
          int[] array = getIntArrayFromUser();
          printUniqueElements(array);
          break;
        }
        case 4: {
          int[] array = getIntArrayFromUser();
          int key = getInt("Enter key to search: ");
          int result = sequentialSearch(array, key);
          if (result >= 0)
            System.out.println("Found at index: " + result);
          else
            System.out.println("Key not found.");
          break;
        }
        case 5: {
          double[] array = getDoubleArrayFromUser();
          System.out.println("Max Element: " + maxElement(array));
          break;
        }
        case 6: {
          System.out.println("Enter Matrix A:");
          int[][] A = getMatrixFromUserDynamic();

          System.out.println("Enter Matrix B:");
          int[][] B = getMatrixFromUserDynamic();

          int[][] C = matrixMultiplicationFlexible(A, B);
          if (C != null) {
            System.out.println("Resulting Matrix:");
            for (int[] row : C) {
              for (int val : row)
                System.out.print(val + " ");
              System.out.println();
            }
          }
          break;
        }
        case 7: {
          int n = getInt("Enter size of augmented matrix (n x n+1): ");
          double[][] matrix = new double[n][n + 1];
          System.out.println("Enter augmented matrix:");
          for (int i = 0; i < n; i++)
            for (int j = 0; j <= n; j++)
              matrix[i][j] = getInt("[" + i + "][" + j + "]: ");
          gaussianElimination(matrix);
          break;
        }
        case 0:
          System.out.println("Exiting...");
          return;
        default:
          System.out.println("Invalid choice. Try again.");
      }
    }
  }
}








