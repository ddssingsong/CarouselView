package com.dds.carousel.carousel;

import java.util.ArrayList;

import com.dds.carousel.R;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

/**
 * 图片轮滑控件
 * 
 * @author dds
 *
 */
public class CarouselView extends LinearLayout {

	private Context mContext;

	private ViewPager mViewPager = null;

	private ImageCycleAdapter mAdapter; // url
	private ImageCycleAdapter1 mAdapter1; // image

	private ViewGroup mGroup; // 底部指示器

	private ImageView mImageView = null; // 单个图片

	private ImageView[] mImageViews = null; // 图片列表

	private boolean isStop;

	public int stype = 1; // 指示器样式，1为长条形，0为圆形

	public CarouselView(Context context) {
		super(context);
	}

	public CarouselView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		LayoutInflater.from(context).inflate(R.layout.activity_image, this);
		mViewPager = (ViewPager) findViewById(R.id.adv_pager);
		mViewPager.setOnPageChangeListener(new GuidePageChangeListener());
		mGroup = (ViewGroup) findViewById(R.id.viewGroup);

	}

	/**
	 * 解决手指按下停止问题
	 */

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			startImageTimerTask();
		} else {
			stopImageTimerTask();
		}
		return super.dispatchTouchEvent(event);
	}

	/**
	 * 装填图片数据url列表
	 * 
	 * @param imageUrlList
	 *            url列表
	 * @param imageCycleViewListener
	 *            点击监听
	 * @param stype
	 *            指示器的样式
	 */
	public void setImageUrlResources(ArrayList<String> imageUrlList, ImageCycleViewListener imageCycleViewListener,
			int stype) {
		this.stype = stype;
		mGroup.removeAllViews();
		// 图片广告数量
		final int imageCount = imageUrlList.size();
		mImageViews = new ImageView[imageCount];

		for (int i = 0; i < imageCount; i++) {
			mImageView = new ImageView(mContext);
			LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			params.leftMargin = 30;
			mImageView.setScaleType(ScaleType.CENTER_CROP);
			mImageView.setLayoutParams(params);

			mImageViews[i] = mImageView;

			if (i == 0) {
				if (this.stype == 1)
					mImageViews[i].setBackgroundResource(R.drawable.banner_dian_focus);
				else
					mImageViews[i].setBackgroundResource(R.drawable.cicle_banner_dian_focus);
			} else {
				if (this.stype == 1)
					mImageViews[i].setBackgroundResource(R.drawable.banner_dian_blur);
				else
					mImageViews[i].setBackgroundResource(R.drawable.cicle_banner_dian_blur);
			}
			mGroup.addView(mImageViews[i]);

		}

		mAdapter = new ImageCycleAdapter(mContext, imageUrlList, imageCycleViewListener);
		mViewPager.setAdapter(mAdapter);
		mViewPager.setCurrentItem(Integer.MAX_VALUE / 2);
		startImageTimerTask();
	}

	/**
	 * 装填图片数据资源id列表
	 * 
	 * @param imageList
	 *            资源id列表
	 * @param imageCycleViewListener
	 *            点击监听
	 * @param stype
	 *            指示器样式
	 */
	public void setImageResources(ArrayList<Integer> imageList, ImageCycleViewListener imageCycleViewListener,
			int stype) {
		this.stype = stype;
		mGroup.removeAllViews();
		// 图片广告数量
		final int imageCount = imageList.size();
		mImageViews = new ImageView[imageCount];

		for (int i = 0; i < imageCount; i++) {
			mImageView = new ImageView(mContext);
			LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			params.leftMargin = 30;
			mImageView.setScaleType(ScaleType.CENTER_CROP);
			mImageView.setLayoutParams(params);

			mImageViews[i] = mImageView;

			if (i == 0) {
				if (this.stype == 1)
					mImageViews[i].setBackgroundResource(R.drawable.banner_dian_focus);
				else
					mImageViews[i].setBackgroundResource(R.drawable.cicle_banner_dian_focus);
			} else {
				if (this.stype == 1)
					mImageViews[i].setBackgroundResource(R.drawable.banner_dian_blur);
				else
					mImageViews[i].setBackgroundResource(R.drawable.cicle_banner_dian_blur);
			}
			mGroup.addView(mImageViews[i]);

		}

		mAdapter1 = new ImageCycleAdapter1(mContext, imageList, imageCycleViewListener);
		mViewPager.setAdapter(mAdapter1);
		mViewPager.setCurrentItem(Integer.MAX_VALUE / 2);
		startImageTimerTask();
	}

	/**
	 * 图片轮播(手动控制自动轮播与否，便于资源控制），可在生命周期中使用
	 */
	public void startImageCycle() {
		startImageTimerTask();
	}

	/**
	 * 暂停轮播—用于节省资源，可在生命周期中使用
	 */
	public void pushImageCycle() {
		stopImageTimerTask();
	}

	/**
	 * 图片滚动任务
	 */
	private void startImageTimerTask() {
		stopImageTimerTask();
		// 图片滚动
		if (mImageTimerTask == null) {
			mHandler.postDelayed(mImageTimerTask, 3000);
		}

	}

	/**
	 * 停止图片滚动任务
	 */
	private void stopImageTimerTask() {
		isStop = true;
		if (mImageTimerTask != null) {
			mHandler.removeCallbacks(mImageTimerTask);
		}

	}

	private Handler mHandler = new Handler();

	/**
	 * 图片自动轮播Task
	 */
	private Runnable mImageTimerTask = new Runnable() {
		@Override
		public void run() {
			if (mImageViews != null) {
				mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
				if (!isStop) {
					mHandler.postDelayed(mImageTimerTask, 3000);
				}

			}
		}
	};

	/**
	 * 轮播图片监听
	 * 
	 * @author minking
	 */
	private final class GuidePageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageScrollStateChanged(int state) {
			if (state == ViewPager.SCROLL_STATE_IDLE)
				startImageTimerTask();
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int index) {
			index = index % mImageViews.length;
			// 设置图片滚动指示器
			if (stype == 1)
				mImageViews[index].setBackgroundResource(R.drawable.banner_dian_focus);
			else
				mImageViews[index].setBackgroundResource(R.drawable.cicle_banner_dian_focus);
			for (int i = 0; i < mImageViews.length; i++) {
				if (index != i) {
					if (stype == 1)
						mImageViews[i].setBackgroundResource(R.drawable.banner_dian_blur);
					else
						mImageViews[i].setBackgroundResource(R.drawable.cicle_banner_dian_blur);
				}

			}
		}
	}

	private class ImageCycleAdapter extends PagerAdapter {

		/**
		 * 图片视图缓存列表
		 */
		private ArrayList<SmartImageView> mImageViewCacheList;

		/**
		 * 图片资源列表
		 */
		private ArrayList<String> mAdList = new ArrayList<String>();
		/**
		 * 广告图片点击监听
		 */
		private ImageCycleViewListener mImageCycleViewListener;

		private Context mContext;

		public ImageCycleAdapter(Context context, ArrayList<String> adList,
				ImageCycleViewListener imageCycleViewListener) {
			this.mContext = context;
			this.mAdList = adList;
			mImageCycleViewListener = imageCycleViewListener;
			mImageViewCacheList = new ArrayList<SmartImageView>();
		}

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			return view == obj;
		}

		@Override
		public Object instantiateItem(ViewGroup container, final int position) {
			String imageUrl = mAdList.get(position % mAdList.size());

			SmartImageView imageView = null;
			if (mImageViewCacheList.isEmpty()) {
				imageView = new SmartImageView(mContext);
				imageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

			} else {
				imageView = mImageViewCacheList.remove(0);
			}

			// 设置图片点击监听
			imageView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					mImageCycleViewListener.onImageClick(position % mAdList.size(), v);
				}
			});
			imageView.setTag(imageUrl);
			container.addView(imageView);
			imageView.setImageUrl(imageUrl, R.drawable.ic_error, R.drawable.ic_empty);
			return imageView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			SmartImageView view = (SmartImageView) object;
			mViewPager.removeView(view);
			mImageViewCacheList.add(view);

		}

	}

	private class ImageCycleAdapter1 extends PagerAdapter {

		/**
		 * 图片资源列表
		 */
		private ArrayList<Integer> mAdList = new ArrayList<Integer>();
		/**
		 * 广告图片点击监听
		 */
		private ImageCycleViewListener mImageCycleViewListener;

		private Context mContext;

		public ImageCycleAdapter1(Context context, ArrayList<Integer> imagelist,
				ImageCycleViewListener imageCycleViewListener) {
			this.mContext = context;
			this.mAdList = imagelist;
			mImageCycleViewListener = imageCycleViewListener;
		}

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			return view == obj;
		}

		@Override
		public Object instantiateItem(ViewGroup container, final int position) {
			Integer imageResourse = mAdList.get(position % mAdList.size());

			ImageView imageView = new ImageView(mContext);
			imageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

			// 设置图片点击监听
			imageView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					mImageCycleViewListener.onImageClick(position % mAdList.size(), v);
				}
			});
			imageView.setTag(imageResourse);
			container.addView(imageView);
			imageView.setImageResource(imageResourse);
			return imageView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			ImageView view = (ImageView) object;
			mViewPager.removeView(view);

		}

	}

	/**
	 * 轮播控件的监听
	 * 
	 * @author minking
	 */
	public static interface ImageCycleViewListener {

		/**
		 * 单击图片事件
		 * 
		 * @param position
		 * @param imageView
		 */
		public void onImageClick(int position, View imageView);
	}

}
