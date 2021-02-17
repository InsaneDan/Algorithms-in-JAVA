package lesson5;

import java.util.LinkedHashSet;
import java.util.Set;

public class Anagram {

    private final char[] chars;
    private String word;
    private Set<String> anagrams = new LinkedHashSet<>();

    public Anagram(String word) {
        this.word = word;
        this.chars = word.toCharArray();
    }

    public Set<String> getAnagrams() {
        anagrams.clear();
        processAnagram(word.length());
        return anagrams;
    }

    private void processAnagram(int length) {
        if (length <= 1) {
            return;
        }

        for (int i = 0; i < length; i++) {
            processAnagram(length - 1);
            anagrams.add(String.valueOf(chars));
            rotate(length);
        }
    }

    private void rotate(int length) {
        int pos = word.length() - length;
        char temp = chars[pos];
        for (int i = pos + 1; i < chars.length; i++) {
            chars[i - 1] = chars[i];
        }
        chars[chars.length - 1] = temp;
    }

    public static void main(String[] args) {
//        Anagram anagramm = new Anagram("cat");
        Anagram anagramm = new Anagram("abcd");
        Set<String> result = anagramm.getAnagrams();
        System.out.println(result.size());
        for (String value : result) {
            System.out.println(value);
        }
    }
}
