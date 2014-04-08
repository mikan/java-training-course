/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch20.ex20_03;

import java.io.IOException;

public class FilterStreamTest {

    public static void main(String[] args) {
        final int key = 2014;
        DecryptInputStream input = new DecryptInputStream(System.in, key);
        EncryptOutputStream output = new EncryptOutputStream(System.out, key);
        try {
            int enc;
            while ((enc = input.read()) != -1) {
                // System.out.println("Encoded: " + (char) enc);
                System.out.println("Encoded: " + enc);
                System.out.print("Decoded: ");
                output.write(enc);
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
