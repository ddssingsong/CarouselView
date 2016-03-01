package com.dds.carousel;

import java.util.ArrayList;

import com.dds.carousel.carousel.CarouselView;
import com.dds.carousel.carousel.CarouselView.ImageCycleViewListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ImageActivity extends Activity {

	private CarouselView mAdView;
	private ArrayList<Integer> mImages = null;

	private Integer imageUrl1 = R.drawable.test1;
	private Integer imageUrl2 = R.drawable.test2;
	private Integer imageUrl3 = R.drawable.test3;

	public int stype = 1;// 这个是底部指示器，1为长条形，0为圆形

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mImages = new ArrayList<Integer>();
		mImages.add(imageUrl1);
		mImages.add(imageUrl2);
		mImages.add(imageUrl3);

		mAdView = (CarouselView) findViewById(R.id.ad_view);
		mAdView.setImageResources(mImages, mAdCycleViewListener, stype);
	}

	private ImageCycleViewListener mAdCycleViewListener = new ImageCycleViewListener() {
		@Override
		public void onImageClick(int position, View imageView) {

			Toast.makeText(ImageActivity.this, "我是"+position, Toast.LENGTH_SHORT).show();
		}
	};
}