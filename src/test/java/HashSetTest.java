import com.sun.javafx.binding.StringFormatter;

import java.text.MessageFormat;
import java.util.Formatter;
import java.util.HashSet;

/**
 * Created by dheeraj on 7/12/15.
 */
public class HashSetTest {
    private static int test = 0;

    public static void main(String[] args) {

        String test = "abc/{0}/def";
        System.out.println(MessageFormat.format(test, "dheeraj"));

    }

    private static class Data {
        private int d;
        private int test;

        public Data(int d, int t) {
            this.d = d;
            this.test = t;
        }

        @Override
        public boolean equals(Object o) {

            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Data data = (Data) o;

            return d == data.d;

        }

        @Override
        public int hashCode() {
            return d;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "d=" + d +
                    ", test=" + test +
                    '}';
        }
    }

}
