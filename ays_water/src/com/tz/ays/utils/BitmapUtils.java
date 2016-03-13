package com.tz.ays.utils;

import java.io.FileNotFoundException;
import java.io.InputStream;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
/**
 * 使用uri的数据流读取文件流的时候，读取一次以后文件流就没有了，解决方法，再重新输入流或者使用path加载
 * @author Administrator
 *
 */
public class BitmapUtils {
	public static Bitmap getScaleBitmapByUri(Uri uri,Context context) throws FileNotFoundException{
		Bitmap bitmap;
		int w;
		//通过uri加载输入流
		InputStream is;
	
			is = context.getContentResolver().openInputStream(uri);

		BitmapFactory.Options options=new BitmapFactory.Options();
		options.inJustDecodeBounds=true;
		bitmap=BitmapFactory.decodeStream(is,null,options);
		w=options.outWidth;
		if(w>MyConstants.BITMAP_STANDED_WIDTH){
			options.inSampleSize=w/MyConstants.BITMAP_STANDED_WIDTH;
			options.inJustDecodeBounds=false;//这才开始真正的加载
			
			bitmap=BitmapFactory.decodeFile(getPicPathByUri(uri, context),options);
//			is = context.getContentResolver().openInputStream(uri);
//			bitmap=BitmapFactory.decodeStream(is,null,options);
		}else{
			bitmap=BitmapFactory.decodeStream(is);
		}
		return bitmap;
		
	}
	public static String getPicPathByUri(Uri uri,Context context){
		String path="";
		Uri imageUri=uri;
		Cursor cursor=context.getContentResolver().query(imageUri, null, null, null, null);
		if(cursor.moveToNext()){
			path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
			
		}
		return path;
	}
}
