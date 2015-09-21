import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.BindException;
import java.util.Map;

import nanoHTTPServer.NanoHTTPD;
import nanoHTTPServer.Status;

/**
 * Created by dheeraj on 25/2/15.
 */
public class FileServer extends NanoHTTPD {
    public static void main(String[] args) {
        FileServer.startServer();
    }

    private static final String TAG = FileServer.class.getSimpleName();
    public static int port = 50000;
    private String data;
    private static FileServer fileServer;


    private FileServer() {
        super(port);
    }

/*    public static class Mystream extends FileInputStream {

        private FileInputStream fileInputStream;

        public Mystream(String s) throws FileNotFoundException {
            super(s);
            fileInputStream = new FileInputStream(s);
        }

        @Override
        public int read(byte b[], int off, int len) throws IOException {
            byte[] bytes = new byte[b.length];
            int k = fileInputStream.read(bytes, off, len);
            if (k != -1) {
                for (int j = 0; j < k; j++) {
                    if (bytes[j] == -128) {
                        b[j] = bytes[j];
                    } else {
                        b[j] = (byte) ~bytes[j];
                    }
                }
            }
            return k;
        }
    }*/

/*
    private static int time = 0;
*/

    private static int requestNumber = 0;

    @Override
    public NanoHTTPD.Response serve(String uri, Method method,
                                    Map<String, String> header, Map<String, String> parameters,
                                    Map<String, String> files) {
        //Toast.makeText(context,"got request = "+uri,Toast.LENGTH_LONG).show();
        InputStream fis = null;
        File file1 = null;
        try {
/*
            fis = new FileInputStream(new File(data + File.separator + uri));
*/
            file1 = new File("/var/www/html/bideo.mp4");
            fis = new FileInputStream(file1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        // video/mp2t
   /*     if (uri.contains("file")) {
            Response response = new NanoHTTPD.Response(Status.OK, "video/mp2t", fis);
            return response;
        } else {
            Response response = new NanoHTTPD.Response(Status.OK, "vnd.apple.mpegurl", fis);
            return response;
        }*/
      /*  if (true) {
            return new NanoHTTPD.Response(Status.OK, "video/mp4", fis);
        }*/
        System.out.print(requestNumber++);
        System.out.println(header);
        Response response;
        if (header.containsKey("range")) {
            String range = header.get("range").replace("bytes=", "");
            String[] strings = range.split("-");
            Long aLong = 0L;
            try {
                aLong = Long.parseLong(strings[0]);
                fis.skip(aLong);
            } catch (Exception e) {
                e.printStackTrace();
            }
            response = new NanoHTTPD.Response(Status.PARTIAL_CONTENT, "video/mp4", fis);
            response.addHeader("Accept-Ranges", "bytes");
            response.addHeader("Content-Length", String.valueOf((file1.length() - aLong)));
            response.addHeader("Keep-Alive", "timeout=5, max=100");
            response.addHeader("Connection", "Keep-Alive");
            response.addHeader("Content-Type", "video/mp4");
            response.addHeader("Content-Range", "bytes " + aLong + "-" + String.valueOf((file1.length() - 1)) + "/" + String.valueOf(file1.length()));
        } else {
            response = new NanoHTTPD.Response(Status.OK, "video/mp4", fis);
            response.addHeader("Accept-Ranges", "bytes");
            response.addHeader("Content-Length", String.valueOf(file1.length()));
            response.addHeader("Keep-Alive", "timeout=5, max=100");
            response.addHeader("Connection", "Keep-Alive");
            response.addHeader("Content-Type", "video/mp4");
            response.addHeader("Content-Range", "bytes " + 0 + "-" + String.valueOf((file1.length() - 1)) + "/" + String.valueOf(file1.length()));
        }
   /*     if (time == 0) {
            response = new NanoHTTPD.Response(Status.OK, "video/mp4", fis);
            response.addHeader("Accept-Ranges", "bytes");
            response.addHeader("Content-Length", "215966688");
            response.addHeader("Keep-Alive", "timeout=5, max=100");
            response.addHeader("Connection", "Keep-Alive");
            response.addHeader("Content-Type", "video/mp4");
        } else if (time == 1) {
            response = new NanoHTTPD.Response(Status.PARTIAL_CONTENT, "video/mp4", fis);
            response.addHeader("Accept-Ranges", "bytes");
            response.addHeader("Content-Length", "215966688");
            response.addHeader("Keep-Alive", "timeout=5, max=100");
            response.addHeader("Connection", "Keep-Alive");
            response.addHeader("Content-Type", "video/mp4");
            response.addHeader("Content-Range", "bytes 0-215966687/215966688");
        } else if (time == 2) {
            response = new NanoHTTPD.Response(Status.PARTIAL_CONTENT, "video/mp4", fis);
            response.addHeader("Accept-Ranges", "bytes");
            response.addHeader("Content-Length", "2784555");
            response.addHeader("Keep-Alive", "timeout=5, max=100");
            response.addHeader("Connection", "Keep-Alive");
            response.addHeader("Content-Type", "video/mp4");
            response.addHeader("Content-Range", "bytes 213182133-215966687/215966688");
        } else if (time == 3) {
            response = new NanoHTTPD.Response(Status.PARTIAL_CONTENT, "video/mp4", fis);
            response.addHeader("Accept-Ranges", "bytes");
            response.addHeader("Content-Length", "213182416");
            response.addHeader("Keep-Alive", "timeout=5, max=100");
            response.addHeader("Connection", "Keep-Alive");
            response.addHeader("Content-Type", "video/mp4");
            response.addHeader("Content-Range", "bytes 48-213182463/215966688");
        } else {
            response = null;
        }
        time++;*/
        return response;
      /*  InputStream fis = null;

        try {
            fis = new Mystream(fileToServe);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new NanoHTTPD.Response(Status.OK, "application/dash-xml", fis);*/
    }

    public static void startServer() {
        fileServer = new FileServer();
        while (true) {
            try {
                fileServer.start();
                break;
            } catch (Exception e) {
                e.printStackTrace();
                if (e instanceof BindException) {
                    port = port + 1;
                } else {
                    break;
                }
            }
        }
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void stopServer() {
        if (fileServer != null) {
            fileServer.stop();
        }
    }
}
