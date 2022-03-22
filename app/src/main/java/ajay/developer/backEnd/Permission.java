package ajay.developer.backEnd;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import ajay.developer.ImageResizer.MainActivity;
import ajay.developer.Params.parms;

public class Permission  extends AppCompatActivity  {


    public  Permission(Context context, Activity activity,String type) {
                parms.setTempActivity(activity);
                parms.setTempContext(context);

   switch (type){
       case "storage":
           if(!Utails.isPermissionGrantedStorage(parms.getTempContext())){
           Log.e("TAGA","permission type is storage");

           takePermissionFile(activity);
             }else{
               Log.e("TAGA","permission type is storage ok");
        }
           break;
       case  "camera":
           Log.e("TAGA","permission type is camera");
           takePermissionCamera(context,activity);
           break;
       default:
           Log.e("TAGA","permission type not found or match");
            break;
   }



    }



    public void  takePermissionFile(Activity activity) {

    if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.R){
        Log.e("TAGA","Build.VERSION.SDK_INT>= Build.VERSION_CODES.R");
      try {
          Intent intent=new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
  intent.addCategory("getPackageName()");
          Uri uri= Uri.fromParts("package", getPackageName(),null);
          intent.setData(uri);
          startActivityForResult(intent,101);
      }catch (Exception e){
          e.printStackTrace();
          Intent in=new Intent();
          in.setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
          startActivityForResult(in,101);
      }



    }else{
        Log.e("TAGA"," ! Build.VERSION.SDK_INT>= Build.VERSION_CODES.R");
        ActivityCompat.requestPermissions(activity,new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.MANAGE_EXTERNAL_STORAGE
        },101);



    }

    }


    public  void takePermissionCamera(Context context, Activity activity){
        if (ContextCompat.checkSelfPermission( context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            Log.e("TAGA","takePermissionCamera " + false );
            ActivityCompat.requestPermissions(activity,
                    new String[]{
                            Manifest.permission.CAMERA
                    }, 110);

        }else {
            Log.e("TAGA", "takePermissionCamera " + true);

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("TAGA","permission request code"+requestCode);
        if (grantResults.length>0){
            boolean readExt=grantResults[0]== PackageManager.PERMISSION_GRANTED;
            switch (requestCode){
                case 101:

                 if(!readExt){

                        takePermissionFile(parms.getTempActivity());
                    }

                    break;
                case  110:
                    if(!readExt){

                        takePermissionCamera(parms.getTempContext(),parms.getTempActivity());
                    }

                    break;
                default:
                    Log.e("TAGA","there is no code match found for request permission");
                    break;


            }
        }
    }



}
