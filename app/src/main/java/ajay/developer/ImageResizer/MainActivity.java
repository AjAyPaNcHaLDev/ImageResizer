package ajay.developer.ImageResizer;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import ajay.developer.Params.parms;
import ajay.developer.backEnd.Permission;
import ajay.developer.backEnd.Utails;
import ajay.developer.backEnd.makeNewImageHandler;
//import ajay.developer.backEnd.makeNewImageHandler;


public class MainActivity extends AppCompatActivity {
    Context context=this;
    Intent myFileIntent;
    String useFilename;
    Random rand = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parms.setFileName(findViewById(R.id.fileName));
        parms.setSeekBarQualityLabel(findViewById(R.id.seekBarQualityLabel));
        parms.setRoot(Environment.getExternalStorageDirectory().getPath());
        parms.setSaveFileActionBtn(findViewById(R.id.saveFileActionBtn));
        parms.setImagePreview(findViewById(R.id.imagePreview));
        parms.getSaveFileActionBtn().setEnabled(false);
        parms.setSeekBarQuality(findViewById(R.id.seekBarQuality));
        parms.getSeekBarQuality().setProgress(50);
        parms.getSeekBarQuality().setVisibility(View.GONE);
        parms.getSeekBarQuality().setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        parms.seekBarQuality.setProgress(progress);
        parms.getSeekBarQualityLabel().setText("Image Size set to "+parms.getSeekBarQuality().getProgress()+"% Drag to change.");
        parms.getSeekBarQualityLabel().setTextColor(Color.argb(rand.nextInt(255), rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)));

        parms.getSaveFileActionBtn().setEnabled(true); }

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

     if (Utails.isPermissionGrantedCamera(context)){

     } else {
         new Permission(context, MainActivity.this,"camera");}

    }

    public void galleryActionBtn(View view) {
parms.getImagePreview().setImageURI(null);
        if (Utails.isPermissionGrantedStorage(context)){
            myFileIntent  =new Intent(Intent.ACTION_GET_CONTENT);
            myFileIntent.setType("image/*");
            startActivityForResult(myFileIntent,10);
}else{
            new Permission(context, MainActivity.this,"storage");
        }
    }


    public void saveFileActionBtn(View view) {
        parms.getSaveFileActionBtn().setEnabled(false);
        ;
new makeNewImageHandler(basicSetup());

    }

    public void clearActionBtn(View view) {
        parms.getSaveFileActionBtn().setEnabled(false);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
switch (requestCode){
    case 10:

        if(resultCode==RESULT_OK){


       Toast.makeText(MainActivity.this,"ok",Toast.LENGTH_SHORT).show();
       parms.getSeekBarQuality().setVisibility(View.VISIBLE);
       parms.getSaveFileActionBtn().setEnabled(false);
                parms.setOriginalBitmapIntent(data);
                parms.setTempActivity(MainActivity.this);
                parms.setTempContext(this);
                Log.e("TAGA",useFilename+" 11");
                InputStream inputStream= null;
                try {
                    inputStream = getContentResolver().openInputStream(parms.getOriginalBitmapIntent().getData());

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Log.e("TAGA",useFilename+" 1");
                parms.setOriginalBitmap(BitmapFactory.decodeStream(inputStream));
                Log.e("TAGA",useFilename+" 2");

                Log.e("TAGA",useFilename+" 3");
                parms.getImagePreview().setImageBitmap(parms.getOriginalBitmap());


        }
        break;
    default:
        break;
}

    }

    private String basicSetup() {

        if(!parms.getFileName().getText().toString().trim().isEmpty()){
            useFilename=parms.getFileName().getText().toString().trim();
 }else{
            String yyyy = new SimpleDateFormat("yyyy",
                    Locale.getDefault()).format(new Date());
            String mm = new SimpleDateFormat("MM",
                    Locale.getDefault()).format(new Date());
            String dd = new SimpleDateFormat("dd",
                    Locale.getDefault()).format(new Date());

            useFilename="ImageResizer_"+rand.nextInt(999)+yyyy+mm+dd;
        }
        Log.e("TAGA","this is the file name: "+parms.getFileName().getText().toString().trim());
   return useFilename;

    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.e("TAGA","resume app");
    }
}