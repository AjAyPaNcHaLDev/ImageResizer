package ajay.developer.backEnd;


import android.app.Activity;
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


    public void backEnd(Activity k){



        parms.getFiles().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(k,"file",Toast.LENGTH_SHORT).show();

            }
        });

        parms.getCamera().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(k,"camera",Toast.LENGTH_SHORT).show();
            }
        });

    }


}
