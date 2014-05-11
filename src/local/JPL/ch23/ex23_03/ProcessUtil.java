/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch23.ex23_03;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ProcessUtil {
    
    private static final String EXIT_TEXT = ".DS_Store";

    public static Process userProg(String cmd) throws IOException {
        Process proc = Runtime.getRuntime().exec(cmd);
        plugTogether(System.in, proc.getOutputStream(), proc);
        plugTogether(System.out, proc.getInputStream(), proc);
        plugTogether(System.err, proc.getErrorStream(), proc);
        return proc;
    }
    
    public static void plugTogether(InputStream in, OutputStream out, Process proc) {
        new Thread(new Executor(in, out, proc)).start();
    }
    
    public static void plugTogether(OutputStream out, InputStream in, Process proc) {
        new Thread(new Executor(in, out, proc)).start();
    }
    
    private static class Executor implements Runnable {
        
        private final Scanner in;
        private final PrintStream out;
        private final Process proc;
        
        public Executor(InputStream in, OutputStream out, Process proc) {
            this.in = new Scanner(in);
            this.in.useDelimiter(System.getProperty("line.separator"));
            this.out = new PrintStream(out);
            this.proc = proc;
        }

        @Override
        public void run() {
            int i = 0;
            while (in.hasNext()) {
                String line = in.next();
                if (line.contains(EXIT_TEXT)) {
                    out.println("\t<<\"" + EXIT_TEXT + "\" detected.>>");
                    proc.destroy();
                    break;
                }
                out.printf("%3d %s%n", ++i, line);
            }
        }
    }
}
