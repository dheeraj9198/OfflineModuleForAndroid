import MPDModels.AdaptationSetBase;
import MPDModels.MPDParser;
import MPDModels.SBase;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.*;
import java.net.URL;

public class Main {


    public static int PRETTY_PRINT_INDENT_FACTOR = 4;
    public static String folder = "/home/dheeraj/Desktop/offlineContent/";

    public static void main(String[] args) throws IOException {

        FileUtils.copyURLToFile(new URL("http://54.86.202.143:1935/vod_android/mp4:sample.mp4/manifest.mpd"), new File(folder + "manifest.mpd"));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(folder + "manifest.mpd")));
        StringBuilder stringBuilder = new StringBuilder();
        String test;
        while ((test = bufferedReader.readLine()) != null) {
            stringBuilder.append(test);
        }
        test = stringBuilder.toString();
        //System.out.println(test);
        try {
            JSONObject xmlJSONObj = XML.toJSONObject(test);
            String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
            //System.out.println(jsonPrettyPrintString);
            MPDParser mpdParser = JsonHandler.parse(jsonPrettyPrintString, MPDParser.class);
            //System.out.println(JsonHandler.stringify(mpdParser));
            String location = mpdParser.getMPD().getLocation();
            location = location.substring(0, location.lastIndexOf("/")) + "/";
            //System.out.println(location);

            for (AdaptationSetBase adaptationSetBase : mpdParser.getMPD().getPeriod().getAdaptationSet()) {
                String repId = adaptationSetBase.getRepresentation().getId();
                String init = adaptationSetBase.getSegmentTemplate().getInitialization();
                //"initialization": "chunk_ctvideo_cfm4s_rid$RepresentationID$_cinit_w664894557_mpd.m4s",
                init = init.replace("$RepresentationID$", repId);
                FileUtils.copyURLToFile(new URL(location+init), new File(folder + init));

                
                //"media": "chunk_ctvideo_cfm4s_rid$RepresentationID$_cs$Time$_w664894557_mpd.m4s",
                String media = adaptationSetBase.getSegmentTemplate().getMedia();
                media = media.replace("$RepresentationID$",repId);
                
                long time = 0;
                boolean firstGone = false;
                for(SBase s :adaptationSetBase.getSegmentTemplate().getSegmentTimeline().getS()){
                    if(!firstGone){
                        firstGone = true;
                        String mediaFinal  = media.replace("$$Time",time+"");
                        FileUtils.copyURLToFile(new URL(location+mediaFinal), new File(folder + mediaFinal));
                    }
                    time = time + Long.parseLong(s.getD());
                    String mediaFinal  = media.replace("$$Time",time+"");
                    FileUtils.copyURLToFile(new URL(location+mediaFinal), new File(folder + mediaFinal));
                }
            }

        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}