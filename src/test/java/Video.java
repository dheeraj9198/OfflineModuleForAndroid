import java.io.Serializable;

/**
 * Created by dheeraj on 17/3/15.
 */
public class Video implements Serializable {
    private String dashUrl;
    private String videoBitrate;
    private String audioBitrate;
    private int videoWidth;
    private int videoHeight;
    private Long fileSize;

    public Video(String dashUrl, String videoBitrate, String audioBitrate, int videoWidth, int videoHeight, Long fileSize) {
        this.dashUrl = dashUrl;
        this.videoBitrate = videoBitrate;
        this.audioBitrate = audioBitrate;
        this.videoWidth = videoWidth;
        this.videoHeight = videoHeight;
        this.fileSize = fileSize;
    }

    public Video() {
    }


    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public int getAudioBitrateInKbps() {
        try {
            return Integer.parseInt(audioBitrate.replaceAll("\\D+", ""));
        } catch (Exception e) {
            return 0;
        }
    }

    public int getVideoBitrateInKbps() {
        try {
            return Integer.parseInt(videoBitrate.replaceAll("\\D+", ""));
        } catch (Exception e) {
            return 0;
        }
    }

    public int getTotalBitrateInKbps() {
        return getAudioBitrateInKbps() + getVideoBitrateInKbps();
    }


    public String getDashUrl() {
        return dashUrl;
    }

    public void setDashUrl(String dashUrl) {
        this.dashUrl = dashUrl;
    }

    public String getVideoBitrate() {
        return videoBitrate;
    }

    public void setVideoBitrate(String videoBitrate) {
        this.videoBitrate = videoBitrate;
    }

    public String getAudioBitrate() {
        return audioBitrate;
    }

    public void setAudioBitrate(String audioBitrate) {
        this.audioBitrate = audioBitrate;
    }

    public int getVideoWidth() {
        return videoWidth;
    }

    public void setVideoWidth(int videoWidth) {
        this.videoWidth = videoWidth;
    }

    public int getVideoHeight() {
        return videoHeight;
    }

    public void setVideoHeight(int videoHeight) {
        this.videoHeight = videoHeight;
    }

    @Override
    public String toString() {
        return "Video{" +
                "dashUrl='" + dashUrl + '\'' +
                ", videoBitrate='" + videoBitrate + '\'' +
                ", audioBitrate='" + audioBitrate + '\'' +
                ", videoWidth=" + videoWidth +
                ", videoHeight=" + videoHeight +
                '}';
    }
}
