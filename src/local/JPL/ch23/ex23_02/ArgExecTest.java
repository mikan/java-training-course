/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch23.ex23_02;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class ArgExecTest {

    @Test
    public void testMain_ls() {
        String[] args = {"ls", "-al"};
        try {
            ArgExec.main(args);
        } catch (IOException e) {
            fail();
        }
    }
}
