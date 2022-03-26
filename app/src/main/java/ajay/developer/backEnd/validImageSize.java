package ajay.developer.backEnd;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

import ajay.developer.Params.parms;

public class  validImageSize {


    public    validImageSize(Bitmap bitmapPhoto,char why  ){

         try {
             if (bitmapPhoto!=null){
                 ByteArrayOutputStream stream = new ByteArrayOutputStream();



                 if(why=='o'){

                     bitmapPhoto.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                 }else if(why=='t'){

                     bitmapPhoto.compress(Bitmap.CompressFormat.JPEG, parms.getSeekBarQuality().getProgress(), stream);
                 }byte[] imageInByte = stream.toByteArray();
                 // Get length of file in bytes
                 float imageSizeInBytes = imageInByte.length;
                 // Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
                 float imageSizeInKB = imageSizeInBytes / 1024;
                 // Convert the KB to MegaBytes (1 MB = 1024 KBytes)
                 float imageSizeInMB = imageSizeInKB / 1024;

               if(why=='o'){
                   parms.setFilesSize(imageSizeInKB);
               }else if(why=='t') {
                   parms.setTempFileSize(imageSizeInKB);
               }

             }
         }catch (Exception e){
             e.printStackTrace();


         }


    }
}
