import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by dheeraj on 27/2/15.
 */
public class HttpLength {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://frontend.test.superprofs.com:1935/vod_android/_definst_/mp4:sp_high_4.mp4/chunk_ctvideo_cfm4s_ridp0a0r0_cs12614400_1287793103_mpd.m4s");
        URLConnection urlConnection = url.openConnection();
        System.out.println(urlConnection.getContentLengthLong());
        //urlConnection.getInputStream().skip()
    }
}
