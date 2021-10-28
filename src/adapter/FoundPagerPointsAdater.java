package adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class FoundPagerPointsAdater extends PagerAdapter {

	private List<ImageView> mList;

	public FoundPagerPointsAdater(List<ImageView> mList) {
		super();
		this.mList = mList;
	}

	//
	@Override
	public int getCount() {
		return mList.size();
	}

	// 给ViewPager的item添加视图
	// ViewGroup container 加载视图的视图组对象 int position item的位置
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		ImageView imgView = mList.get(position);
		container.addView(imgView);
		return imgView;
	}

	// 移除ViewPager中的视图
	// ViewGroup container 加载视图的视图组对象
	// int position 需要移除视图的位置
	// Object object instantiateItem返回的对象
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// super.destroyItem(container, position, object);
		container.removeView(mList.get(position));
	}

	// 判断ViewPager的item显示的View arg0和instantiateItem返回的Object arg1是否相同
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

}
