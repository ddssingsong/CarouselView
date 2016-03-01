package com.dds.carousel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * ��������
 * 
 * @author dds
 *
 */
public class LeadActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lead);
	}

	public void btn_click(View view) {
		int id = view.getId();
		switch (id) {
		/**
		 * ��ȡurl
		 */
		case R.id.button1:
			startActivity(new Intent(this, UrlActivity.class));
			break;
		/**
		 * ��ȡ����ͼƬ
		 */
		case R.id.button2:
			startActivity(new Intent(this, ImageActivity.class));
			break;

		default:
			break;
		}

	}
}
