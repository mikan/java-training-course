/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch23.ex23_02;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ProcessUtil {

    public static Process userProg(String cmd) throws IOException {
        Process proc = Runtime.getRuntime().exec(cmd);
        plugTogether(System.in, proc.getOutputStream());
        plugTogether(System.out, proc.getInputStream());
        plugTogether(System.err, proc.getErrorStream());
        return proc;
    }
    
    public static void plugTogether(InputStream in, OutputStream out) {
        new Thread(new Executor(in, out)).start();
    }
    
    public static void plugTogether(OutputStream out, InputStream in) {
        new Thread(new Executor(in, out)).start();
    }
    
    private static class Executor implements Runnable {
        
        private final Scanner in;
        private final PrintStream out;
        
        public Executor(InputStream in, OutputStream out) {
            this.in = new Scanner(in);
            this.in.useDelimiter(System.getProperty("line.separator"));
            this.out = new PrintStream(out);
        }

        @Override
        public void run() {
            int i = 0;
            while (in.hasNext())
                out.printf("%3d %s%n", ++i, in.next());
        }
    }
}
