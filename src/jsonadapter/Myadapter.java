package jsonadapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import asynctask.LoadImageAsync;
import bean.UserLogin;

import com.westsoft.headlines.R;

public class Myadapter extends BaseAdapter {
	private List<UserLogin> lists;
	private Context context;

	public Myadapter(List<UserLogin> lists, Context context) {
		super();
		this.lists = lists;
		this.context = context;
	}

	public int getCount() {
		return lists.size();
	}

	public Object getItem(int position) {
		return lists.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.listview_item, null);
			holder.photo_img = (ImageView) convertView.findViewById(R.id.image);
			holder.tv_id = (TextView) convertView.findViewById(R.id.tv_id);
			holder.tv_nicname = (TextView) convertView
					.findViewById(R.id.tv_nickname);
			holder.tv_reg = (TextView) convertView
					.findViewById(R.id.tv_reg_type);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		UserLogin user = lists.get(position);
		// 加载图片
		LoadImageAsync imageAsync = new LoadImageAsync(context,
				holder.photo_img);
		imageAsync.execute(user.getAvatar_url());
		holder.tv_id.setText("id:" + user.getId());
		holder.tv_nicname.setText("别名：" + user.getNickname());
		holder.tv_reg.setText("来源:" + user.getReg_type());
		return convertView;
	}

	class ViewHolder {
		ImageView photo_img;
		TextView tv_id;
		TextView tv_nicname;

		TextView tv_reg;

	}

}
