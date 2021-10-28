package adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/*
 * »¶Ó­Ò³Ãæ
 */
public class WelcomeViewAdapter extends PagerAdapter{

	private List<ImageView> list;
	
	public WelcomeViewAdapter(List<ImageView> list) {

		super();
		this.list=list;
	}
	
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		
		return arg0==arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {

		container.addView(list.get(position));
		return list.get(position);
	}


	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {

//		super.destroyItem(container, position, object);
		container.removeView(list.get(position));
	}

	

	

}
