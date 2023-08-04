package ajay.developer.ImageResizer;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import ajay.developer.Params.parms;

import ajay.developer.backEnd.MakeNewImageHandler;
import ajay.developer.backEnd.validImageSize;

public class MainActivity extends AppCompatActivity {
    String useFilename;
    Random rand = new Random();
    File   photoFile=null;
    File storageDir;
    Intent myFileIntent;
    String currentPhotoPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parms.setFileName(findViewById(R.id.fileName));
        parms.setSeekBarQualityLabel(findViewById(R.id.seekBarQualityLabel));
//        parms.setRoot(Environment.getExternalStorageDirectory().getPath());
        parms.setSaveFileActionBtn(findViewById(R.id.saveFileActionBtn));
        parms.setImagePreview(findViewById(R.id.imagePreview));
        parms.getSaveFileActionBtn().setEnabled(false);
        parms.setSeekBarQuality(findViewById(R.id.seekBarQuality));
        parms.getSeekBarQuality().setProgress(50);
        parms.getSeekBarQuality().setEnabled(false);
        getSupportActionBar().hide();
        parms.getSeekBarQuality().setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                parms.seekBarQuality.setProgress(progress);
                new validImageSize(parms.getOriginalBitmap(),'t');
                parms.getSeekBarQualityLabel().setText("Drag to change Size.\n"+parms.getTempFileSize()+"KB / "+parms.getFilesSize()+"KB "+parms.getSeekBarQuality( ).getProgress()+"%");
                parms.getSeekBarQualityLabel()
                .setTextColor(Color.argb(70,80,90,90));
                parms.getSaveFileActionBtn().setEnabled(true);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                parms.getSeekBarQualityLabel().setTextColor(Color.argb(255, 255,0,0));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                parms.getSeekBarQualityLabel().setTextColor(Color.argb(255, 7,70, 7));
            }
        });
    }
    public void cameraActionBtn(View view ) {
          if(checkPermission(Manifest.permission.CAMERA)){
              openCameraTask();
          }
     }
    public void galleryActionBtn(View view) {
        if(checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE)){
            parms.getImagePreview().setImageURI(null);
            myFileIntent = new Intent(Intent.ACTION_GET_CONTENT);
            myFileIntent.setType("image/*");
            startActivityForResult(myFileIntent,10);

        }else{
            Toast.makeText(MainActivity.this,"Please allow permission",Toast.LENGTH_SHORT).show();
        }

    }
    public void saveFileActionBtn(View view) {
        parms.getSaveFileActionBtn().setEnabled(false);
        MakeNewImageHandler makeNewImageHandler=new MakeNewImageHandler();

        File file=makeNewImageHandler.makeFile(basicSetup(),MainActivity.this);
        if(file.exists()){
            Intent i=new Intent(MainActivity.this,ImageScreen.class);
            i.putExtra("imagePath",file.getAbsolutePath());
            startActivity(i);
        }

    }
    public void galleryActionListBtn(View view) {
        if(checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE)){
            parms.getImagePreview().setImageURI(null);
            startActivity(new Intent(MainActivity.this,Gallery.class));
        }else{
            Toast.makeText(MainActivity.this,"please allow permission",Toast.LENGTH_SHORT).show();
        }
    }
    public void clearActionBtn(View view) {
        parms.getSaveFileActionBtn().setEnabled(false);
    }
    private boolean checkPermission( String type) {
        final boolean[] permission = {false};
        Dexter.withContext(this)
                .withPermission(type)
                .withListener(new PermissionListener() {
                    @Override public void onPermissionGranted(PermissionGrantedResponse response) {
                        permission[0] =true;
                    }
                    @Override public void onPermissionDenied(PermissionDeniedResponse response) {
                        permission[0] =false;
                        Toast.makeText(MainActivity.this,"please allow permission form setting",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Settings.ACTION_SETTINGS));
                    }
                    @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        Toast.makeText(MainActivity.this,"1",Toast.LENGTH_SHORT).show();
                        token.continuePermissionRequest();
                    }
                }).check();
        return permission[0];
    }
    private void openCameraTask() {
        Log.e("TAGA", "dispatchTakePictureIntent: " );
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            Log.e("TAGA", "dispatchTakePictureIntent: inside resoleActivity," );
            // Create the File where the photo should go

            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Toast.makeText(MainActivity.this, ex.toString(), Toast.LENGTH_SHORT).show();
                Log.e("TAGA", "dispatchTakePictureIntent: inside resoleActivity," +ex);
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "ajay.developer.ImageResizer.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    // Start the image capture intent to take photo
                    startActivityForResult(takePictureIntent, 123);
                }
            }
        }
        Log.e("TAGA", "dispatchTakePictureIntent: end" );
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
   return useFilename;

    }
    private File createImageFile() throws IOException {
        storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image= File.createTempFile(
                basicSetup(),  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        Log.e("LOGA",currentPhotoPath);
        return image;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 || requestCode==123) {
            if (resultCode == RESULT_OK) {
                parms.getSeekBarQuality().setEnabled(true);
                parms.getSaveFileActionBtn().setEnabled(false);
                parms.setTempActivity(MainActivity.this);
                parms.setTempContext(this);
                InputStream inputStream = null;
                if(requestCode==10){
                    parms.setOriginalBitmapIntent(data);
                    try {
                        inputStream = getContentResolver().openInputStream(parms.getOriginalBitmapIntent().getData());

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    parms.setOriginalBitmap(BitmapFactory.decodeStream(inputStream));
                    parms.getImagePreview().setImageBitmap(parms.getOriginalBitmap());

                    parms.getSeekBarQualityLabel().setText("Image Selected now Drag Seekbar to Resize then click Save File.\n File Size is"+parms.getFilesSize()+"KB");
                }else {

                    Bitmap takenImage = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                    parms.getImagePreview().setImageBitmap(takenImage);
                    parms.setOriginalBitmap(takenImage);

                    File del = new File (currentPhotoPath);
                    del.delete();
                }
                new validImageSize(parms.getOriginalBitmap(),'o');
                parms.getSeekBarQualityLabel().setText("Image Selected now Drag Seekbar to Resize then click Save File.\n"+parms.getFilesSize()+"KB");

            }
        }
    }



}