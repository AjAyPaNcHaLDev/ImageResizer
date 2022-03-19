package ajay.developer.ImageResizer;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import ajay.developer.Params.parms;
import ajay.developer.backEnd.Permission;


public class MainActivity extends AppCompatActivity {


    Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parms.setFiles(findViewById(R.id.galleryBtn));
        parms.setCamera(findViewById(R.id.cameraBtn));


    }

    @Override
    protected void onResume() {
        super.onResume();

Log.e("TAGA","resume app");
    }

    public void cameraActionBtn(View view) {
        new Permission(context, MainActivity.this,"camera");
    }

    public void galleryActionBtn(View view) {
        new Permission(context, MainActivity.this,"storage");
    }
}