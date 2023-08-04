package ajay.developer.backEnd;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import ajay.developer.Params.parms;

  public class MakeNewImageHandler  {
    File file ,myDir;
    Context context;
     public  File   makeFile(String useFilename,Context c ){
        this.context=c;
        myDir= new File(c.getExternalFilesDir(null) + "/ImageResizer/");
        file = new File (myDir, useFilename+".jpeg");


        if (!myDir.exists()){
            try {
                myDir.mkdirs();
            }catch (Exception e){
                Log.e("TAGA",e+"  fiLe not found ");
                return null;
            }
        }

        if (!myDir.exists()){
        Log.e("TAGA","File Path Not Found." ) ;
        return  null;
        }


        try {
            FileOutputStream out = new FileOutputStream(file);
            parms.getOriginalBitmap().compress(Bitmap.CompressFormat.JPEG,parms.getSeekBarQuality().getProgress() , out);
            out.flush();
            Log.e("TAGA","created file" ) ;
            return  file;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e("TAGA",e+"  file not found ");
            Toast.makeText(parms.getTempContext(),e.toString(), Toast.LENGTH_LONG).show();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("TAGA","  Input Output Exception  "+e);
            Toast.makeText(parms.getTempContext(),e.toString(), Toast.LENGTH_LONG).show();
            return null;
        }
    }


}
