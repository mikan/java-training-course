/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch07.ex07_03;

/** Pascal's triangle */
public class PascalsTriangle {

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
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1 - i; j++)
                System.out.print("  ");
            for (int num : table[i])
                System.out.printf("%4d", num);
            System.out.println();
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
        for (int i = 0; i < size; i++) {
            table[i] = new int[i + 1];
            table[i][0] = 1;
            table[i][i] = 1;
        }
        for (int i = 2; i < size; i++)
            for (int j = 1; j < table[i].length - 1; j++)
                table[i][j] = table[i - 1][j - 1] + table[i - 1][j];
        return table;
    }
    
    /**
     * Print triangle to console. 
     * 
     * @param size Size of triangle
     */
    public void printSimple(int size) {
        for (int[] row : createTriangle(size)) {
            for (int num : row)
                System.out.printf("%4d", num);
            System.out.println();
        }
    }
}
