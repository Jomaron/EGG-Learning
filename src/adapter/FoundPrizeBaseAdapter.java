package adapter;

import java.util.List;

import org.xutils.x;
import org.xutils.image.ImageOptions;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import bean.FoundPointsPrizeDataBean;

import com.westsoft.headlines.R;

public class FoundPrizeBaseAdapter extends BaseAdapter {

	private List<FoundPointsPrizeDataBean> mList;
	private Context mContext;

	public FoundPrizeBaseAdapter(List<FoundPointsPrizeDataBean> mList,
			Context mContext) {
		super();
		this.mList = mList;
		this.mContext = mContext;
	}

	public int getCount() {
		return mList.size();
	}

	public Object getItem(int position) {
		return mList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("NewApi")
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vHolder;
		if (convertView == null) {
			vHolder = new ViewHolder();

			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.layout_found_prize_gridview, null);
			//
			vHolder.mImgPrize = (ImageView) convertView
					.findViewById(R.id.prize_img);
			vHolder.mTvPrizeName = (TextView) convertView
					.findViewById(R.id.prize_name_tv);
			vHolder.mTvPrizeScores = (TextView) convertView
					.findViewById(R.id.prize_scores_tv);
			//
			convertView.setTag(vHolder);
		} else {
			vHolder = (ViewHolder) convertView.getTag();
		}
		//
		FoundPointsPrizeDataBean prizeData = mList.get(position);
		ImageOptions.Builder builder = new ImageOptions.Builder();
		builder.setUseMemCache(true);
		ImageOptions options = builder.build();
		// x.image().bind(vHolder.mImgPrize, prizeData.getImg_src(), options);
		// vHolder.mTvPrizeName.setText(prizeData.getName());
		// vHolder.mTvPrizeScores.setText(prizeData.getScores());

		vHolder.mImgPrize.setImageResource(prizeData.getImg_src());
		vHolder.mTvPrizeName.setText(prizeData.getName());
		// vHolder.mTvPrizeScores.setText(prizeData.getScores());
		return convertView;
	}

	class ViewHolder {
		private ImageView mImgPrize;
		private TextView mTvPrizeName, mTvPrizeScores;
	}
}
