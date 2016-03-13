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
 * ʹ��uri����������ȡ�ļ�����ʱ�򣬶�ȡһ���Ժ��ļ�����û���ˣ��������������������������ʹ��path����
 * @author Administrator
 *
 */
public class BitmapUtils {
	public static Bitmap getScaleBitmapByUri(Uri uri,Context context) throws FileNotFoundException{
		Bitmap bitmap;
		int w;
		//ͨ��uri����������
		InputStream is;
	
			is = context.getContentResolver().openInputStream(uri);

		BitmapFactory.Options options=new BitmapFactory.Options();
		options.inJustDecodeBounds=true;
		bitmap=BitmapFactory.decodeStream(is,null,options);
		w=options.outWidth;
		if(w>MyConstants.BITMAP_STANDED_WIDTH){
			options.inSampleSize=w/MyConstants.BITMAP_STANDED_WIDTH;
			options.inJustDecodeBounds=false;//��ſ�ʼ�����ļ���
			
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
