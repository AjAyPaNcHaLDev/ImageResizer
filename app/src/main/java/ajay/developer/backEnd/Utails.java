package ajay.developer.backEnd;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import androidx.core.content.ContextCompat;

public class Utails {

    public  static  boolean isPermissionGrantedStorage(Context context){

     if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.R){
         Log.e("TAGA","isPermissionGrantedStorage " +Environment.isExternalStorageManager() );
         return Environment.isExternalStorageManager();
     }else{

         int readExtStorage=ContextCompat.checkSelfPermission(context,Manifest.permission.READ_EXTERNAL_STORAGE);
         Log.e("TAGA","isPermissionGrantedStorage " +readExtStorage );
         return readExtStorage== PackageManager.PERMISSION_GRANTED;

     }

    }
}
