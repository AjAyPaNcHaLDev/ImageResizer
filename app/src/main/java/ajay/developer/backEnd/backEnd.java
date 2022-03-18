package ajay.developer.backEnd;


import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ajay.developer.ImageResizer.MainActivity;
import ajay.developer.ImageResizer.R;
import ajay.developer.Params.parms;

public  class backEnd  extends  AppCompatActivity  {


    public  backEnd(Activity activity, Context context){



        parms.getFiles().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                new Permission(context ,activity,"storage");

            }
        });

        parms.getCamera().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Permission(context ,activity,"camera");

                new Permission(context ,activity,"storage");


            }
        });

    }


}
