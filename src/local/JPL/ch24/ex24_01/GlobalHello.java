/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch24.ex24_01;

import java.util.Locale;
import java.util.ResourceBundle;

public class GlobalHello {
    public static void main(String[] args) {
        // Locale.setDefault(Locale.ENGLISH);
        // Locale.setDefault(Locale.JAPAN);
        Locale.setDefault(Locale.JAPANESE);
        ResourceBundle res = ResourceBundle.getBundle("local.JPL.ch24.ex24_01.GlobalRes");
        String msg;
        if (args.length > 0)
            msg = res.getString(GlobalRes.HELLO);
        else
            msg = res.getString(GlobalRes.GOODBYE);
        System.out.println(msg);
    }
}
