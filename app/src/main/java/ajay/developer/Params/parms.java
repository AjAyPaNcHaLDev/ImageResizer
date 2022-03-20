package ajay.developer.Params;


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import org.json.JSONObject;

public  class parms {
public  static Activity tempActivity;
public  static Context tempContext;
public  static View Files;
public  static View Camera;
public  static ImageView imagePreview;
public  static EditText fileName;
public  static SeekBar seekBarQuality;


    public  static TextView seekBarQualityLabel;

    public static ImageView getImagePreview() {
        return imagePreview;
    }

    public static void setImagePreview(ImageView imagePreview) {
        parms.imagePreview = imagePreview;
    }

    public static EditText getFileName() {
        return fileName;
    }

    public static void setFileName(EditText fileName) {
        parms.fileName = fileName;
    }

    public static SeekBar getSeekBarQuality() {
        return seekBarQuality;
    }

    public static void setSeekBarQuality(SeekBar seekBarQuality) {
        parms.seekBarQuality = seekBarQuality;
    }

    public static TextView getSeekBarQualityLabel() {
        return seekBarQualityLabel;
    }

    public static void setSeekBarQualityLabel(TextView seekBarQualityLabel) {
        parms.seekBarQualityLabel = seekBarQualityLabel;
    }



    public static Activity getTempActivity() {
        return tempActivity;
    }

    public static void setTempActivity(Activity tempActivity) {
        parms.tempActivity = tempActivity;
    }



    public static Context getTempContext() {
        return tempContext;
    }

    public static void setTempContext(Context tempContext) {
        parms.tempContext = tempContext;
    }

    public static View getFiles() {
        return Files;
    }

    public static void setFiles(View files) {
        Files = files;
    }

    public static View getCamera() {
        return Camera;
    }

    public static void setCamera(View camera) {
        Camera = camera;
    }
}