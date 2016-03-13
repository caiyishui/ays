package com.tz.ays.fragment;

import com.tz.ays.activity.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class RegistFristFragment extends Fragment {
	private EditText etRegistMobile,etRegistName,etPwd;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.registe_fragment_one,null);
		etRegistMobile=(EditText) view.findViewById(R.id.et_regist_mobile);
		etRegistName=(EditText) view.findViewById(R.id.et_regist_name);
		etPwd=(EditText) view.findViewById(R.id.et_pwd);
		return view;
	}
	public EditText getEtRegistMobile() {
		return etRegistMobile;
	}
	public void setEtRegistMobile(EditText etRegistMobile) {
		this.etRegistMobile = etRegistMobile;
	}
	public EditText getEtRegistName() {
		return etRegistName;
	}
	public void setEtRegistName(EditText etRegistName) {
		this.etRegistName = etRegistName;
	}
	public EditText getEtPwd() {
		return etPwd;
	}
	public void setEtPwd(EditText etPwd) {
		this.etPwd = etPwd;
	}
}
