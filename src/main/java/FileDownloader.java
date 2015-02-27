import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by dheeraj on 27/2/15.
 */
public class FileDownloader {
    private static long totalsize;
    private static long size;
    
    public static void main(String[] args) throws IOException{
        URL url = new URL("http://download.wavetlan.com/SVV/Media/HTTP/sample_100kbit.mp4");
        URLConnection urlConnection = url.openConnection();
        System.out.println(urlConnection.getContentLengthLong());
        
        
        
        InputStream in = new URL( "http://commons.apache.org" ).openStream();
        try {
            InputStreamReader inR = new InputStreamReader( in );
            BufferedReader buf = new BufferedReader( inR );
            String line;
            while ( ( line = buf.readLine() ) != null ) {
                System.out.println( line );
            }
        } finally {
            in.close();
        }
    }
}
