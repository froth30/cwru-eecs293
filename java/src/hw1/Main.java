package hw1;

import java.io.*;
import java.util.*;

/**
 * Main class for Programming Assignment 1
 *
 * Case Western Reserve University
 * EECS 293  |  Software Craftsmanship  |  2016 Fall Semester
 * Due at beginning of discussion on Wednesday, September 7, 2016
 *
 * @see <a href="Hw1.pdf">https://blackboard.case.edu/bbcswebdav/pid-1366059-dt-content-rid-4244627_1/courses/eecs293_vxl11/Hw1.pdf</a>
 *
 * Theodore Frohlich <ttf10@case.edu>
 */
public class Main {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        System.out.println("\n< Programming Assignment 1 (Java) >");

        System.out.print("\nList A:  ");
        List a = readList();

        System.out.print("\nList B:  ");
        List b = readList();

        System.out.print("\nLongest common prefix:  ");
        System.out.println(longestPrefix(a, b,
                (s1, s2) -> ((String) s1).compareTo((String) s2)));
    }

    /**
     * Reads a line of text as a list of comma-separated strings.
     * @return a list of strings, with leading and trailing whitespace removed
     * @throws IOException if an I/O error occurs
     */
    private static List<String> readList() throws IOException {
        List<String> list = Arrays.asList(reader.readLine().split(","));
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i).trim());
        }
        return list;
    }

    /**
     * Finds the longest common prefix in two lists.
     * @param a the first list
     * @param b the second list
     * @param cmp an admissible comparing function (see {@link Comparator})
     * @param <T> the type of the list elements to be compared
     * @return the prefix of maximum length that is common to both lists
     */
    static <T> List<T> longestPrefix(List<T> a,
                                     List<T> b,
                                     Comparator<? super T> cmp)
    {
        int maxLen = Math.min(a.size(), b.size());
        List<T> prefix = new ArrayList<>(maxLen);

        for (int i = 0; i < maxLen; i++) {
            if (cmp.compare(a.get(i), b.get(i)) == 0) {
                prefix.add(a.get(i));
            }
        }
        return prefix;
    }

}
