import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by dheeraj on 25/2/15.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://www.vision.caltech.edu/html-files/EE148-2005-Spring/pprs/viola04ijcv.pdf");
        URL urlTemp = new URL("http://www.vision.caltech.edu/html-files/EE148-2005-Spring/pprs/viola04ijcv.pdf");
        File fileTemp = new File(new File("/home/dheeraj/Desktop/test"),"tester.pdf");

        int length = (int) fileTemp.length();
        if(length == urlTemp.openConnection().getContentLength()){
            System.out.println("already downloaded");
            return;
        }
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("Range", "bytes=" + length + "-");
        InputStream inputStream = connection.getInputStream();

        FileOutputStream fileOutputStream = new FileOutputStream(fileTemp,true);

        byte[] bytes = new byte[2048];
        int k;
        long count = 0;


        while ((k = inputStream.read(bytes)) != -1) {
            count = count + k;
                fileOutputStream.write(bytes, 0, k);
        }

        System.out.println(count);
    }
}