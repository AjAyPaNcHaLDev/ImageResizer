package ajay.developer.ImageResizer;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import ajay.developer.Params.parms;
import ajay.developer.backEnd.backEnd;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parms.setFiles(findViewById(R.id.files));
        parms.setCamera(findViewById(R.id.camera));
backEnd n=new backEnd();
n.backEnd(MainActivity.this);


    }
}