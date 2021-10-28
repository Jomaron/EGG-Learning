package contents;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.westsoft.headlines.R;

public class FoundContentFragment extends Fragment {

	private ViewPager mFViewPager;
	private ImageView iv_found;
	private LinearLayout mFLinearTitle;
	private View rootView;
	private Button btn1, btn2;
	private FragmentManager fm;
	private FragmentTransaction traction;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.layout_found_points_fragment_view, container, false);
		initView();
		initEvent();

		imageResId = new int[] { R.drawable.myimage_01, R.drawable.myimage_02,
				R.drawable.myimag_03, R.drawable.myimage_06,
				R.drawable.myimage_05 };
		titles = new String[imageResId.length];
		titles[0] = "智慧之子，使父亲欢乐";
		titles[1] = "信，是守望之事的实底，是未见之事的确据";
		titles[2] = "那光是真光，照亮一切生在世上的人";
		titles[3] = "有耳可听的就应当听";
		titles[4] = "上帝为你关上一扇门，一定会为你开扇窗";

		imageViews = new ArrayList<ImageView>();
		for (int i = 0; i < imageResId.length; i++) {
			ImageView imageView = new ImageView(getContext());
			imageView.setImageResource(imageResId[i]);
			imageView.setScaleType(ScaleType.CENTER_CROP);
			imageViews.add(imageView);

		}

		dots = new ArrayList<View>();
		dots.add(rootView.findViewById(R.id.v_dot0));
		dots.add(rootView.findViewById(R.id.v_dot1));
		dots.add(rootView.findViewById(R.id.v_dot2));
		dots.add(rootView.findViewById(R.id.v_dot3));
		dots.add(rootView.findViewById(R.id.v_dot4));

		tv_title = (TextView) rootView.findViewById(R.id.tv_title);
		tv_title.setText(titles[0]);//

		viewPager = (ViewPager) rootView.findViewById(R.id.vp);
		viewPager.setAdapter(new MyAdapter());// 设置填充ViewPager页面的适配器
		// 设置一个监听器，当ViewPager中的页面改变时调用
		viewPager.setOnPageChangeListener(new MyPageChangeListener());

		return rootView;
	}

	private void initView() {

		btn1 = (Button) rootView.findViewById(R.id.found_points_mall_btn);
		btn2 = (Button) rootView.findViewById(R.id.found_special_events_btn);
		mFViewPager = (ViewPager) rootView.findViewById(R.id.vp);
		iv_found = (ImageView) rootView.findViewById(R.id.found_search_img);
	}

	private void initEvent() {

		// btn1.setOnClickListener(this);
		// btn2.setOnClickListener(this);
		// iv_found.setOnClickListener(this);
	}

	private ViewPager viewPager;
	private List<ImageView> imageViews;
	private String[] titles;
	private int[] imageResId;
	private List<View> dots;
	private TextView tv_title;
	private int currentItem = 0;

	private ScheduledExecutorService scheduledExecutorService;

	// 切换当前显示的图片
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			viewPager.setCurrentItem(currentItem);
		};
	};

	@Override
	public void onStart() {
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		// 当Activity显示出来后，每两秒钟切换一次图片显示
		scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2,
				TimeUnit.SECONDS);

		super.onStart();
	}

	@Override
	public void onStop() {

		scheduledExecutorService.shutdown();
		super.onStop();
	}

	/**
	 * 换行切换任务
	 * 
	 */
	private class ScrollTask implements Runnable {

		public void run() {
			synchronized (viewPager) {
				System.out.println("currentItem: " + currentItem);
				currentItem = (currentItem + 1) % imageViews.size();
				handler.obtainMessage().sendToTarget(); // 通过Handler切换图片
			}
		}

	}

	/**
	 * 当ViewPager中页面的状态发生改变时调用
	 * 
	 * @author Administrator
	 * 
	 */
	private class MyPageChangeListener implements OnPageChangeListener {
		private int oldPosition = 0;

		/**
		 * This method will be invoked when a new page becomes selected.
		 * position: Position index of the new selected page.
		 */
		public void onPageSelected(int position) {
			currentItem = position;
			tv_title.setText(titles[position]);
			dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
			dots.get(position).setBackgroundResource(R.drawable.dot_focused);
			oldPosition = position;
		}

		public void onPageScrollStateChanged(int arg0) {

		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}
	}

	/**
	 * 填充ViewPager页面的适配器
	 * 
	 * @author Administrator
	 * 
	 */
	private class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return imageResId.length;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {

			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(View container, int position) {
			((ViewPager) container).addView(imageViews.get(position));
			return imageViews.get(position);
		}

		@Override
		public void destroyItem(View container, int position, Object object) {

			((ViewPager) container).removeView((View) object);
		}

		@Override
		public void restoreState(Parcelable state, ClassLoader loader) {

		}

		@Override
		public void startUpdate(View arg0) {

		}

		@Override
		public void finishUpdate(View arg0) {

		}

		@Override
		public Parcelable saveState() {
			return null;
		}

	}

}