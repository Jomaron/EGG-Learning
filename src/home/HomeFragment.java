package home;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.headlines.fragment.ReMenFragment;
import com.headlines.view.ViewPagerIndicator;
import com.westsoft.headlines.R;

/*
 * 首页的子Fragment
 */
public class HomeFragment extends Fragment {

	private ViewPager vp;
	private ViewPagerIndicator indactor;
	private List<ReMenFragment> mContents = new ArrayList<ReMenFragment>();
	private FragmentPagerAdapter adpater;
	private View rootView;

	private List<String> mTitles = Arrays.asList("动态", "经文", "诗歌", "书籍", "故事",
			"互动", "运动", "生活"); 

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.activity_main, container, false);

		initView();
		initDatas();
		
		// 动态生成Tab
		indactor.setVisibleTabCount(4);
		indactor.setTabItemTitles(mTitles);
		
		vp.setAdapter(adpater);
		// 调用ViewPager页面监听方法
		indactor.setViewPager(vp, 0);

		return rootView;

	}
	
	private void initView() {

		vp=(ViewPager) rootView.findViewById(R.id.id_homeviewPager);
		indactor=(ViewPagerIndicator) rootView.findViewById(R.id.id_indicator);
	}

	// 添加数据
	private void initDatas() {

		// 循环添加Fragment对象
		for (int i = 0; i < 8; i++) {
			ReMenFragment fragment = new ReMenFragment();
			mContents.add(fragment);
		}

		adpater = new FragmentPagerAdapter(getFragmentManager()) {

			@Override
			public int getCount() {
				return mContents.size();
			}

			@Override
			public Fragment getItem(int arg0) {
				return mContents.get(arg0);
			}
		};
	}

}
