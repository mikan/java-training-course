/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch23.ex23_02;

import java.io.IOException;

public class ArgExec {

    public static void main(String[] args) throws IOException {
        Process proc = Runtime.getRuntime().exec(args);
        ProcessUtil.plugTogether(System.in, proc.getOutputStream());
        ProcessUtil.plugTogether(System.out, proc.getInputStream());
    }

}
