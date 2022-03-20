package ajay.developer.ImageResizer;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;


import java.util.Random;

import ajay.developer.Params.parms;
import ajay.developer.backEnd.Permission;
import ajay.developer.backEnd.Utails;


public class MainActivity extends AppCompatActivity {
    Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parms.setSeekBarQualityLabel(findViewById(R.id.seekBarQualityLabel));
parms.setSeekBarQuality(findViewById(R.id.seekBarQuality));
parms.getSeekBarQuality().setProgress(50);
parms.getSeekBarQuality().setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        parms.seekBarQuality.setProgress(progress);
parms.getSeekBarQualityLabel().setText("Image Size set to "+parms.getSeekBarQuality().getProgress()+"% Drag to change.");
        Random rand = new Random();
  parms.getSeekBarQualityLabel().setTextColor(Color.argb(rand.nextInt(255), rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        parms.getSeekBarQualityLabel().setTextColor(Color.argb(255, 255,0,0));
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        parms.getSeekBarQualityLabel().setTextColor(Color.argb(255, 07,70,07));
    }
});
    }


    public void cameraActionBtn(View view) {

     if (Utails.ispermissionStatusCamera(context)){

     } else {new Permission(context, MainActivity.this,"camera");}

    }

    public void galleryActionBtn(View view) {

        if (Utails.isPermissionGrantedStorage(context)){


        }else{
            new Permission(context, MainActivity.this,"storage");
        }
    }


    public void saveFileActionBtn(View view) {


    }

    public void clearActionBtn(View view) {
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.e("TAGA","resume app");
    }

}