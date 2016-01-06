import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.AbstractMap;

/**
 * Created by dheeraj on 4/1/16.
 */
public class Detest {
    private static final String STRING = "{\"data\":\"one\",\"aata\":\"two\",\"test\":{\"k\":1452071815002,\"l\":1452071815002}}";

    public static class Test {
        Long k;
        Long l;

        public Test() {
        }

        public Test(Long k, Long l) {
            this.k = k;
            this.l = l;
        }

        public Long getK() {
            return k;
        }

        public void setK(Long k) {
            this.k = k;
        }

        public Long getL() {
            return l;
        }

        public void setL(Long l) {
            this.l = l;
        }
    }

    public interface Mixin {
        @JsonIgnore
        Long getK();
    }

    private String data;
    private String aata;
    private Test test;

    public Detest(String data, String aata) {
        this.data = data;
        this.aata = aata;
        this.test = new Test(System.currentTimeMillis(), System.currentTimeMillis());
    }

    public Detest() {
    }

    public String getData() {
        return data;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAata() {
        return aata;
    }

    public void setAata(String aata) {
        this.aata = aata;
    }

    public static void main(String[] jhdsvbj) {
        System.out.println(JsonHandler.stringify(new Detest("one", "two")));
        Detest detest = JsonHandler.parseNormal(STRING, Detest.class);
        System.out.println(JsonHandler.stringify(detest));
        System.out.println(JsonHandler.stringifyMixIn(detest, new AbstractMap.SimpleEntry<Class, Class>(Test.class, Mixin.class)));
    }
}
