package ajay.developer.backEnd;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import ajay.developer.ImageResizer.MainActivity;
import ajay.developer.ImageResizer.R;
import ajay.developer.Params.parms;

public class makeNewImageHandler extends AppCompatActivity {

    public makeNewImageHandler(String useFilename){



Toast.makeText(parms.getTempContext(),useFilename,Toast.LENGTH_SHORT).show();

        File myDir = new File(parms.getRoot() + "/ImageResizer/Pictures/");
        File file = new File (myDir, useFilename+".jpeg");
        if (!myDir.exists()){
            try {
                myDir.mkdirs();
            }catch (Exception e){
                Log.e("TAGA",e+"  fnot found ");
            }

            Log.e("TAGA","path  found" ) ;
        }else{
            Log.e("TAGA","path not found" ) ;
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            parms.getOriginalBitmap().compress(Bitmap.CompressFormat.JPEG,parms.getSeekBarQuality().getProgress() , out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e("TAGA",e+"  f not found ");
            Toast.makeText(parms.getTempContext(),e.toString(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {

            e.printStackTrace();
            Log.e("TAGA","  i o exception from file "+e);

            Toast.makeText(parms.getTempContext(),e.toString(), Toast.LENGTH_LONG).show();
        }



    }


}
