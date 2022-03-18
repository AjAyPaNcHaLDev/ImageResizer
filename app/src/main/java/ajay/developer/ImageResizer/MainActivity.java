package ajay.developer.ImageResizer;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;


import ajay.developer.Params.parms;

import ajay.developer.backEnd.backEnd;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parms.setFiles(findViewById(R.id.files));
        parms.setCamera(findViewById(R.id.camera));

new backEnd(MainActivity.this,this);


    }

    @Override
    protected void onResume() {
        super.onResume();

Log.e("TAGA","resume app");
    }
}