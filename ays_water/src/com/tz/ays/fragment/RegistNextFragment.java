package com.tz.ays.fragment;

import java.io.FileNotFoundException;
import java.io.InputStream;

import com.tz.ays.activity.R;
import com.tz.ays.utils.BitmapUtils;
import com.tz.ays.utils.MyConstants;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class RegistNextFragment extends Fragment implements OnClickListener {
	private TextView tvCardFront,tvCardRear,tvCard;
	private EditText etAge,etName;
	ImageView frontPhoto,backPhoto,certPhoto;
	private Uri frontPhotoUri,backPhotoUri,certPhotoUri;//利用地址
	private String frontPhotoPath,backPhotoPath,certPhotoPath;
	
	
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	
	View v=inflater.inflate(R.layout.registe_fragment_two, null);	  	
	tvCardFront=(TextView) v.findViewById(R.id.tv_card_front);
	tvCardRear=(TextView) v.findViewById(R.id.tv_card_rear);
	tvCard=(TextView) v.findViewById(R.id.tv_card);
	etAge=(EditText) v.findViewById(R.id.et_age);
	etName=(EditText) v.findViewById(R.id.et_name);
	frontPhoto=(ImageView) v.findViewById(R.id.front_photo);
	backPhoto=(ImageView) v.findViewById(R.id.back_photo);
	certPhoto=(ImageView) v.findViewById(R.id.cert_photo);
	//解决图片缓存问题
	if(frontPhotoUri!=null){
		try {
			frontPhoto.setImageBitmap(BitmapUtils.getScaleBitmapByUri(frontPhotoUri,getActivity()));
			tvCardFront.setText("正面身份证已选择");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	if(backPhotoUri!=null){
		try {
			backPhoto.setImageBitmap(BitmapUtils.getScaleBitmapByUri(backPhotoUri,getActivity()));
			tvCardRear.setText("背面身份证已选择");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	if(certPhotoUri!=null){
		try {
			certPhoto.setImageBitmap(BitmapUtils.getScaleBitmapByUri(certPhotoUri,getActivity()));
			tvCard.setText("其他证照片已选择");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	tvCardFront.setOnClickListener(this);
	tvCardRear.setOnClickListener(this);
	tvCard.setOnClickListener(this);
	return v;
}
public void onClick(View v) {
	switch (v.getId()) {
	case R.id.tv_card_front:
		//取身份证正面
		doPickPhotoFromGallery(MyConstants.FRONT_CARD);
		break;
	case R.id.tv_card_rear:
		//获取身份证背面
		doPickPhotoFromGallery(MyConstants.REAL_CARD);
		break;
	case R.id.tv_card:
		doPickPhotoFromGallery(MyConstants.CERT_CARD);
		break;

	default:
		break;
	}
	
}

	public void doPickPhotoFromGallery(int type){
		//开启相机或者图库
		//首先判断sd卡是否存在
		String state=Environment.getExternalStorageState();
		//如果sd卡挂载不正常
		if(!state.equals(Environment.MEDIA_MOUNTED)){
			return;
		}else{
			//挂载正常
			Intent intent=new Intent(Intent.ACTION_PICK);
			intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
			startActivityForResult(intent, type);
		}
		
	}
	//调用图册返回标识码
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch (requestCode) {
		case MyConstants.FRONT_CARD:
			//已经选取正面图片了,缩小后设置图片
			try {
				frontPhotoUri=data.getData();
				frontPhotoPath=BitmapUtils.getPicPathByUri(frontPhotoUri, getActivity());
				frontPhoto.setImageBitmap(BitmapUtils.getScaleBitmapByUri(data.getData(),getActivity()));
				tvCardFront.setText("正面身份证已选择");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		case MyConstants.REAL_CARD:
			try {
				backPhotoUri=data.getData();
				backPhotoPath=BitmapUtils.getPicPathByUri(backPhotoUri, getActivity());
				backPhoto.setImageBitmap(BitmapUtils.getScaleBitmapByUri(data.getData(),getActivity()));
				tvCardRear.setText("背面身份证已选择");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case MyConstants.CERT_CARD:
			try {
				certPhotoUri=data.getData();
				certPhotoPath=BitmapUtils.getPicPathByUri(certPhotoUri, getActivity());
				certPhoto.setImageBitmap(BitmapUtils.getScaleBitmapByUri(data.getData(),getActivity()));
				tvCard.setText("其他证照片已选择");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	public String getFrontPhotoPath() {
		return frontPhotoPath;
	}
	public void setFrontPhotoPath(String frontPhotoPath) {
		this.frontPhotoPath = frontPhotoPath;
	}
	public String getBackPhotoPath() {
		return backPhotoPath;
	}
	public void setBackPhotoPath(String backPhotoPath) {
		this.backPhotoPath = backPhotoPath;
	}
	public String getCertPhotoPath() {
		return certPhotoPath;
	}
	public void setCertPhotoPath(String certPhotoPath) {
		this.certPhotoPath = certPhotoPath;
	}
	/**
	 * 根据图片uri进行缩放图片,利用options的预加载功能
	 * @param uri
	 * @return
	 * @throws FileNotFoundException 
	 */
	
	public TextView getTvCardFront() {
		return tvCardFront;
	}
	public void setTvCardFront(TextView tvCardFront) {
		this.tvCardFront = tvCardFront;
	}
	public TextView getTvCardRear() {
		return tvCardRear;
	}
	public void setTvCardRear(TextView tvCardRear) {
		this.tvCardRear = tvCardRear;
	}
	public TextView getTvCard() {
		return tvCard;
	}
	public void setTvCard(TextView tvCard) {
		this.tvCard = tvCard;
	}
	public EditText getEtAge() {
		return etAge;
	}
	public void setEtAge(EditText etAge) {
		this.etAge = etAge;
	}
	public EditText getEtName() {
		return etName;
	}
	public void setEtName(EditText etName) {
		this.etName = etName;
	}
	public ImageView getFrontPhoto() {
		return frontPhoto;
	}
	public void setFrontPhoto(ImageView frontPhoto) {
		this.frontPhoto = frontPhoto;
	}
	public ImageView getBackPhoto() {
		return backPhoto;
	}
	public void setBackPhoto(ImageView backPhoto) {
		this.backPhoto = backPhoto;
	}
	public ImageView getCertPhoto() {
		return certPhoto;
	}
	public void setCertPhoto(ImageView certPhoto) {
		this.certPhoto = certPhoto;
	}
}
