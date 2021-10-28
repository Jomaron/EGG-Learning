package adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FoundFragmentPagerAdapter extends FragmentPagerAdapter {

	private List<Fragment> mListFragments;

	public FoundFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	//
	public FoundFragmentPagerAdapter(FragmentManager fm,
			List<Fragment> mListFragments) {
		super(fm);
		this.mListFragments = mListFragments;
	}

	@Override
	public Fragment getItem(int arg0) {
		return mListFragments.get(arg0);
	}

	@Override
	public int getCount() {
		return mListFragments.size();
	}

}
