package ajay.developer.backEnd;
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
    public makeNewImageHandler(String useFilename){

       myDir= new File(parms.getRoot() + "/ImageResizer/");
      file = new File (myDir, useFilename+".jpeg");

        Log.e("TAGA",useFilename+"  chack file name please  and path "+ myDir);



        if (Utails.isPermissionGrantedStorage(parms.getTempContext())){

            makeFile(useFilename);

        }else{
            new Permission(parms.getTempContext(), parms.getTempActivity(),"storage");
            Toast.makeText(parms.getTempContext(),"please allow file permission to save file.", Toast.LENGTH_LONG).show();

        }





    }

    void  makeFile(String useFilename ){
        if (!myDir.exists()){
            try {
                myDir.mkdirs();
            }catch (Exception e){
                Log.e("TAGA",e+"  fnot found ");
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


}
