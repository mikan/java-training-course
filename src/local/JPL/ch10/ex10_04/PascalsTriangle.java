/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch10.ex10_04;

/** Pascal's triangle */
public class PascalsTriangle {

    // Rewrite ex07_03 with while loop
    public static void main(String[] args) {
        new PascalsTriangle().print(12);
    }
    
    /**
     * Print triangle to console. 
     * 
     * @param size Size of triangle
     */
    public void print(int size) {
        int[][] table = createTriangle(size);
        int i = 0;
        while (i < size) {
            int j = 0;
            while (j < size - 1 - i) {
                System.out.print("  ");
                j++;
            }
            int k = 0;
            while (k < table[i].length) {
                System.out.printf("%4d", table[i][k]);
                k++;
            }
            System.out.println();
            i++;
        }
    }

    /**
     * Create table of triangle.
     * 
     * @param size Size of triangle
     * @return Data
     */
    private int[][] createTriangle(int size) {
        int[][] table = new int[size][];
        int i = 0;
        while (i < size) {
            table[i] = new int[i + 1];
            table[i][0] = 1;
            table[i][i] = 1;
            i++;
        }
        int j = 2;
        while (j < size) {
            int k = 1;
            while (k < table[j].length - 1) {
                table[j][k] = table[j - 1][k - 1] + table[j - 1][k];
                k++;
            }
            j++;
        }
        return table;
    }
}
