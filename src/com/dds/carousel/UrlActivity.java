package com.dds.carousel;

import java.util.ArrayList;

import com.dds.carousel.carousel.CarouselView;
import com.dds.carousel.carousel.CarouselView.ImageCycleViewListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/*
 * ����ͼƬ�ֲ�Ч�� ����url
 */
public class UrlActivity extends Activity {

	private CarouselView mAdView;
	private ArrayList<String> mImageUrl = null;

	// �˴���urlһ����ȡ��
	private String imageUrl1 = "http://pic31.nipic.com/20130702/2926417_003653575119_2.jpg";
	private String imageUrl2 = "http://pic.58pic.com/58pic/11/22/39/458PICK58PICgAk.jpg";
	private String imageUrl3 = "http://pic2.ooopic.com/12/52/76/42bOOOPIC81_1024.jpg";

	public int stype = 0;// ����ǵײ�ָʾ����1Ϊ�����Σ�0ΪԲ��

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mImageUrl = new ArrayList<String>();
		mImageUrl.add(imageUrl1);
		mImageUrl.add(imageUrl2);
		mImageUrl.add(imageUrl3);

		mAdView = (CarouselView) findViewById(R.id.ad_view);
		mAdView.setImageUrlResources(mImageUrl, mAdCycleViewListener, stype);
	}

	private ImageCycleViewListener mAdCycleViewListener = new ImageCycleViewListener() {
		@Override
		public void onImageClick(int position, View imageView) {

			Toast.makeText(UrlActivity.this, mImageUrl.get(position) + position, Toast.LENGTH_SHORT).show();
		}
	};
}
