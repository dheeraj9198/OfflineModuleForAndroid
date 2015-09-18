import org.apache.commons.io.FileUtils;

import javax.net.ssl.SSLException;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by dheeraj on 9/9/15.
 */
public class HlsDownloader {
    private static final File lectureFolder = new File("/home/dheeraj/Desktop/offlineContent/");
    private static final String chunklistFileName = "playlist.m3u8";
    private static final String hlsUrl = "http://devimages.apple.com/iphone/samples/bipbop/gear1/prog_index.m3u8";

    public static void main(String[] args) throws Exception {
        downloadLecture();
    }

    public static void downloadLecture() throws Exception {
        BufferedReader bufferedReader = null;
        URL url = new URL(hlsUrl);
        File chunkFile = null;
        ArrayList<String> chunkNameArrayList = new ArrayList<String>();
        chunkFile = new File(lectureFolder + File.separator + chunklistFileName);
        FileUtils.copyURLToFile(url, chunkFile, 30000, 30000);
        bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(chunkFile)));
        String line = null;
        String location = hlsUrl.substring(0, hlsUrl.lastIndexOf("/")) + "/";
        while ((line = bufferedReader.readLine()) != null) {
            if (line.contains("#EXTINF")) {
                String time = line.substring(line.lastIndexOf(":") + 1, line.lastIndexOf(","));
            } else if (line.endsWith(".ts")) {
                chunkNameArrayList.add(line);
            }
        }

        try {
            bufferedReader.close();
        } catch (Exception e) {
        }

        for (String currentChunkName : chunkNameArrayList) {
            url = new URL(location + currentChunkName);
            File file = new File(lectureFolder + File.separator + currentChunkName);
            FileUtils.copyURLToFile(url, file, 30000, 30000);
        }
    }
}
