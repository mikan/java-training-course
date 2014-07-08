/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch16.ex16_11;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PlayerLoader extends ClassLoader {

    private static final String PLAYER_SITE = "playersite";
    private static final String PREFIX = "bin";
    private static final String SEP = File.separator;

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
	name = getClass().getPackage().getName() + "." + name;
	try {
	    byte[] buf = bytesForClass(name);

	    // Convert class name
	    System.out.println("[PlayerLoader] specified name:\t" + name);
	    name = getRealName(name);
	    System.out.println("[PlayerLoader] real name:\t" + name);

	    return defineClass(name, buf, 0, buf.length);
	} catch (IOException e) {
	    throw new ClassNotFoundException(e.toString());
	}
    }

    protected byte[] bytesForClass(String name) throws IOException, ClassNotFoundException {
	// Base path (e.g. local/JPL/ch16/ex16_11)
	String base = name.substring(0, name.lastIndexOf('.')).replaceAll("\\.", SEP);
	// Target name (e.g. StubPlayer)
	String target = name.substring(name.lastIndexOf('.') + 1);
	FileInputStream in = null;
	try {
	    in = streamFor(PREFIX + SEP + base + SEP + PLAYER_SITE + SEP + target + ".class");
	    int length = in.available(); // Get number of bytes
	    if (length == 0)
		throw new ClassNotFoundException(name);
	    byte[] buf = new byte[length];
	    in.read(buf);
	    return buf;
	} finally {
	    if (in != null)
		in.close();
	}
    }

    private FileInputStream streamFor(String path) throws IOException {
	return new FileInputStream(new File(path));
    }

    /**
     * Get real name by specified name.
     * 
     * @param name specified name
     * @return real name
     */
    private String getRealName(String name) {
	name = name.substring(0, name.lastIndexOf('.')) + "." + PLAYER_SITE + "."
		+ name.substring(name.lastIndexOf('.') + 1);
	return name;
    }
}
