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
	private Uri frontPhotoUri,backPhotoUri,certPhotoUri;//���õ�ַ
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
	//���ͼƬ��������
	if(frontPhotoUri!=null){
		try {
			frontPhoto.setImageBitmap(BitmapUtils.getScaleBitmapByUri(frontPhotoUri,getActivity()));
			tvCardFront.setText("�������֤��ѡ��");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	if(backPhotoUri!=null){
		try {
			backPhoto.setImageBitmap(BitmapUtils.getScaleBitmapByUri(backPhotoUri,getActivity()));
			tvCardRear.setText("�������֤��ѡ��");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	if(certPhotoUri!=null){
		try {
			certPhoto.setImageBitmap(BitmapUtils.getScaleBitmapByUri(certPhotoUri,getActivity()));
			tvCard.setText("����֤��Ƭ��ѡ��");
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
		//ȡ���֤����
		doPickPhotoFromGallery(MyConstants.FRONT_CARD);
		break;
	case R.id.tv_card_rear:
		//��ȡ���֤����
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
		//�����������ͼ��
		//�����ж�sd���Ƿ����
		String state=Environment.getExternalStorageState();
		//���sd�����ز�����
		if(!state.equals(Environment.MEDIA_MOUNTED)){
			return;
		}else{
			//��������
			Intent intent=new Intent(Intent.ACTION_PICK);
			intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
			startActivityForResult(intent, type);
		}
		
	}
	//����ͼ�᷵�ر�ʶ��
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch (requestCode) {
		case MyConstants.FRONT_CARD:
			//�Ѿ�ѡȡ����ͼƬ��,��С������ͼƬ
			try {
				frontPhotoUri=data.getData();
				frontPhotoPath=BitmapUtils.getPicPathByUri(frontPhotoUri, getActivity());
				frontPhoto.setImageBitmap(BitmapUtils.getScaleBitmapByUri(data.getData(),getActivity()));
				tvCardFront.setText("�������֤��ѡ��");
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
				tvCardRear.setText("�������֤��ѡ��");
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
				tvCard.setText("����֤��Ƭ��ѡ��");
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
	 * ����ͼƬuri��������ͼƬ,����options��Ԥ���ع���
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
