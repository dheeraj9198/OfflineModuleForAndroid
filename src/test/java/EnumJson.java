import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by dheeraj on 2/12/15.
 */
public class EnumJson {

    public enum TestEnum {
        one, two;
    }

    public static class Data {
        private TestEnum testEnum;
        private String testString;
        private LocalDateTime localDateTime;

        public Data() {
        }

        public Data(TestEnum testEnum, String testString, LocalDateTime localDateTime) {
            this.testEnum = testEnum;
            this.testString = testString;
            this.localDateTime = localDateTime;
        }

        public TestEnum getTestEnum() {
            return testEnum;
        }

        public void setTestEnum(TestEnum testEnum) {
            this.testEnum = testEnum;
        }

        public String getTestString() {
            return testString;
        }

        public void setTestString(String testString) {
            this.testString = testString;
        }

        public LocalDateTime getLocalDateTime() {
            return localDateTime;
        }

        public void setLocalDateTime(LocalDateTime localDateTime) {
            this.localDateTime = localDateTime;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "testEnum=" + testEnum +
                    ", testString='" + testString + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        getDate(System.currentTimeMillis());

 /*       int number = 1234567;
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        String numberAsString = numberFormat.format(number);
        System.out.println(numberAsString);

        Data data = new Data(TestEnum.one, "dheeraj", LocalDateTime.now());
        String json = JsonHandler.stringify(data);
        System.out.println(json);

        Data data1 = JsonHandler.parseUnderScoredResponse(json, Data.class);
        System.out.println(data1);*/

    }

    private static void getDate(long time) {
        Calendar calendar = Calendar.getInstance();
        TimeZone tz = TimeZone.getDefault();
        calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.getTimeInMillis()));
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        java.util.Date currenTimeZone = new java.util.Date(time);
        System.out.println(sdf.format(currenTimeZone).toString());
    }

}
