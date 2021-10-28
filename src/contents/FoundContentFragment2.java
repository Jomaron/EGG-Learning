package contents;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.westsoft.headlines.R;

public class FoundContentFragment2 extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View rootView = inflater.inflate(
				R.layout.layout_found_special_fragment_view, container,false);
		return rootView;
	}
}