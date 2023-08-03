package ajay.developer.backEnd;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import ajay.developer.ImageResizer.MainActivity;
import ajay.developer.Params.parms;

public class makeNewImageHandler extends AppCompatActivity {
    File file ,myDir;
    void  makeFile(String useFilename ){
        if (!myDir.exists()){
            try {
                myDir.mkdirs();
            }catch (Exception e){
                Log.e("TAGA",e+"  fiLe not found ");
                return;
            }
        }
        if (myDir.exists()){

            try {
                FileOutputStream out = new FileOutputStream(file);
                parms.getOriginalBitmap().compress(Bitmap.CompressFormat.JPEG,parms.getSeekBarQuality().getProgress() , out);
                out.flush();
                Log.e("TAGA","created file" ) ;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Log.e("TAGA",e+"  f not found ");
                Toast.makeText(parms.getTempContext(),e.toString(), Toast.LENGTH_LONG).show();
                return;
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("TAGA","  i o exception from file "+e);

                Toast.makeText(parms.getTempContext(),e.toString(), Toast.LENGTH_LONG).show();
                return;
            }
            Toast.makeText(parms.getTempContext(),useFilename+ " Saved.", Toast.LENGTH_LONG).show();
        }else Log.e("TAGA","path  found" ) ;
    }
    public makeNewImageHandler(String useFilename, Context c){
        myDir= new File(c.getExternalFilesDir(null) + "/ImageResizer/");
        file = new File (myDir, useFilename+".jpeg");
        makeFile(useFilename);
     }


}
