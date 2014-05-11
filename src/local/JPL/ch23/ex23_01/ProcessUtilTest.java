/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch23.ex23_01;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class ProcessUtilTest {

    @Test
    public void testUserProg_ls() {
        try {
            Process proc = ProcessUtil.userProg("ls -al");
            System.out.println(proc.toString());
        } catch (IOException e) {
            fail();
        }
    }

}
