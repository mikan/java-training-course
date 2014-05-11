/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch23.ex23_03;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class ArgExec2Test {

    @Test
    public void testMain_ls() {
        String[] args = {"ls", "-al"};
        try {
            ArgExec2.main(args);
        } catch (IOException e) {
            fail();
        }
    }
}
