# CarouselView
图片轮播，可异步获取图片，可获取本地图片

使用：
   在这里实时项目的代码和资源文件，只要把这些拷到你的项目中，
   你的项目就已经完成来了两个功能，一个是引导界面，一个是图片轮播，是不是很开心
   
 carousel_view.xml：
  <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/ad_rl"
    android:layout_width="match_parent"
    android:layout_height="match_parent" >
    <android.support.v4.view.ViewPager
        android:id="@+id/adv_pager"
        android:layout_width="match_parent"
        android:layout_height="match_parent" >
    </android.support.v4.view.ViewPager>
    <LinearLayout
        android:id="@+id/viewGroup"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:layout_marginBottom="8dp"
        android:gravity="center"
        android:orientation="horizontal" >
    </LinearLayout>
   </RelativeLayout>

装填图片数据url列表

	  @param imageUrlList
	             url列表
	 @param imageCycleViewListener
	            点击监听
	 @param stype
	            指示器的样式
	 
mAdView.setImageUrlResources(mImageUrl, mAdCycleViewListener, stype);
