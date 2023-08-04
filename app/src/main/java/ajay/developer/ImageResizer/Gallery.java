package ajay.developer.ImageResizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
      getSupportActionBar().setTitle("Saved Images");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
         ScreenHeight = displayMetrics.heightPixels;
         ScreenWidth = displayMetrics.widthPixels;
        gridView=(GridView) findViewById(R.id.galleryView);
        gridView.setAdapter(new GalleryAdapter(this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        gridView.setAdapter(new GalleryAdapter(this));
    }

    // Method to navigate back to the previous instance of the activity
    private void navigateBackToPreviousInstance() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        navigateBackToPreviousInstance();
    }
}