/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch20.ex20_11;

import java.io.File;
import java.io.FilenameFilter;

public class SuffixFileFilter implements FilenameFilter {
    
    private final File dir;
    private final String suffix;
    
    public static void main(String[] args) {
        SuffixFileFilter filter = new SuffixFileFilter("/Applications", "app");
        for (String list : filter.list())
            System.out.println(list);
    }
    
    public SuffixFileFilter(String dir, String suffix) {
        this.dir = new File(dir);
        this.suffix = suffix;
    }
    
    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(suffix);
    }
    
    public String[] list() {
        return dir.list(this);
    }
}
