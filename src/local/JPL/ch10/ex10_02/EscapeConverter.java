/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch10.ex10_02;

/** Convert escape sequence to text. */
public class EscapeConverter {
    
    public static void main(String[] args) {
        String source = "te\bst\ttest\'\n\\\f";
        System.out.println("-----INPUT-----");
        System.out.println(source);
        System.out.println("-----OUTPUT-----");
        System.out.println(new EscapeConverter().convert(source));
    }

    /**
     * Convert escape sequence to text.
     * 
     * @param text Source text
     * @return converted text
     */
    public String convert(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            result.append(replace(text.charAt(i)));
        }
        return result.toString();
    }

    /**
     * Replace a character if source is escape sequence.
     * 
     * @param c source character
     * @return converted string
     */
    private String replace(char c) {
        switch (c) {
        case '\n': return "\\n";
        case '\t': return "\\t";
        case '\b': return "\\b";
        case '\r': return "\\r";
        case '\f': return "\\f";
        case '\\': return "\\\\";
        case '\'': return "\\'";
        case '\"': return "\\\"";
        default: return String.valueOf(c);
        }
    }
}
