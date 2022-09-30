package ajay.developer.ImageResizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import ajay.developer.backEnd.adapter.GalleryAdapter;

public class Gallery extends AppCompatActivity {
GridView gridView;
public  static  int ScreenHeight;
public  static  int ScreenWidth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
      getSupportActionBar().setTitle("Choose Image");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
         ScreenHeight = displayMetrics.heightPixels;
         ScreenWidth = displayMetrics.widthPixels;

gridView=(GridView) findViewById(R.id.galleryView);
gridView.setAdapter(new GalleryAdapter(this));
gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
});


    }
}