/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch24.ex24_01;

import java.util.ListResourceBundle;

public class GlobalRes_en extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return contents;
    }
    
    /* @formatter:off */
    private static final Object[][] contents = {
        { GlobalRes.HELLO, "Hello" },
        { GlobalRes.GOODBYE, "Goodbye" },
    };
    /* @formatter:on */
}
