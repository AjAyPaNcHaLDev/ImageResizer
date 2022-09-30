package ajay.developer.backEnd.adapter;
import static ajay.developer.ImageResizer.Gallery.ScreenHeight;
import static ajay.developer.ImageResizer.Gallery.ScreenWidth;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GalleryAdapter extends BaseAdapter {
    Context context;
    int itemWidth=ScreenWidth;

    ArrayList<File> myImages = new ArrayList<File>();
 public GalleryAdapter(Context context) {
        this.context = context;
     String path = Environment.getExternalStorageDirectory()+"/ImageResizer";
     File p=new File(path);
File [] files=p.listFiles();

     for(File file :files){
         if(!file.isDirectory()){
             String extension = file.getName().substring(file.getName().lastIndexOf("."));
             if(extension.equals(".png")||extension.equals(".jpg")||extension.equals(".jpeg"))
             myImages.add(file);
         }
     }

     Collections.sort(myImages, new Comparator<File>() {

         @Override
         public int compare(File file1, File file2) {
             long k = file1.lastModified() - file2.lastModified();
             if(k < 0){
                 return 1;
             }else if(k == 0){
                 return 0;
             }else{
                 return -1;
             }
         }
     });
    }

    @Override
    public int getCount() {
        return myImages.size();
    }

    @Override
    public Object getItem(int i) {
        return myImages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView=new ImageView(context);
        imageView.setImageBitmap(BitmapFactory.decodeFile(String.valueOf(myImages.get(i))));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        int w,h;
        w=h=itemWidth/3;

        imageView.setLayoutParams(new GridView.LayoutParams(w,h));
        return imageView;
    }
}
