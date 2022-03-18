package ajay.developer.Params;


import android.app.Activity;
import android.content.Context;
import android.view.View;

public  class parms {
    public  static Activity tempActivity;
    public  static Context tempContext;
    public  static View Files;
public static View Camera;


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