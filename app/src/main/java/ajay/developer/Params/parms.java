package ajay.developer.Params;


import android.view.View;

public  class parms {

public  static View Files;
public static View Camera;

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