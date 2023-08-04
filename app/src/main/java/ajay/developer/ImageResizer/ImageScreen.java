package ajay.developer.ImageResizer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class ImageScreen extends AppCompatActivity {
ImageView imageView;
    String imagePath;
    File file;
    Bitmap bitmapImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_screen);
        // Get the image path from the intent extras
          imagePath = getIntent().getStringExtra("imagePath");

        imageView = findViewById(R.id.largerImage);
        // Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        // Check if the imagePath is not null or empty
        if (imagePath != null && !imagePath.isEmpty()) {
              file = new File(imagePath);
            if (!file.exists()) {
                Toast.makeText(this, "This file does not exist", Toast.LENGTH_LONG).show();
                finish();
                return;
            }

            try {
                InputStream inputStream = new FileInputStream(file);
                // Step 1: Read the InputStream and convert it to a Bitmap
                  bitmapImage = BitmapFactory.decodeStream(inputStream);
                // Step 2: Set the Bitmap to the ImageView
                imageView.setImageBitmap(bitmapImage);
                // Close the InputStream after using it
                inputStream.close(); // Close the inputStream after use
            } catch (Exception e) {
                e.printStackTrace();
                // Handle any errors that may occur during file reading
            }


        }
    }

    public void deleteFileHandler(View view) {
        if (file.exists()) {
            String fileName=file.getName();
            Toast.makeText(this, fileName+ " Deleted Successfully", Toast.LENGTH_LONG).show();
            file.delete();
            startActivity(new Intent(this,Gallery.class));
            finish();
            return;
        }else{
            Toast.makeText(this, "This Image does not exist.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
    }

    public void shareFileAction(View view) {

// Create the sharing Intent using FileProvider
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/*");
        Uri fileUri = FileProvider.getUriForFile(this, "ajay.developer.ImageResizer.fileprovider", file);
        shareIntent.putExtra(Intent.EXTRA_STREAM, fileUri);

// Grant read permission to other apps
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

// Start the Intent to show the share chooser
        startActivity(Intent.createChooser(shareIntent, "Share Image"));
    }
}