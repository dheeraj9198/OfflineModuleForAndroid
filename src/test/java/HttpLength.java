import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by dheeraj on 27/2/15.
 */
public class HttpLength {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://frontend.test.superprofs.com:1935/vod_android/mp4:sample.mp4/chunk_ctvideo_cfm4s_ridp0a0r0_cs0_w1716892166_mpd.m4s");
        URLConnection urlConnection = url.openConnection();
        System.out.println(urlConnection.getContentLengthLong());
    }
}
