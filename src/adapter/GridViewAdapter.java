package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.westsoft.headlines.R;

public class GridViewAdapter extends BaseAdapter{
	
	private Context context;
	
	 public GridViewAdapter(Context context) {
		 this.context=context;
	}
	 public Context getContext() {
		return context;
	}
	 
	 private GridViewData data[]=new GridViewData[]{
				new GridViewData("���±�", R.drawable.myimage_01),
				new GridViewData("��ͼ����", R.drawable.myimage_10),
				new GridViewData("����", R.drawable.myimage_11),
				new GridViewData("ʫ��", R.drawable.myimage_05),
				new GridViewData("����", R.drawable.myimage_06),
				new GridViewData("����", R.drawable.myimage_07),
				new GridViewData("����", R.drawable.myimage_05),
				new GridViewData("�", R.drawable.myimage_06)
		};

	@Override
	public int getCount() {
		return data.length;
	}

	@Override
	public Object getItem(int position) {
		return data[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout ll;
		if(convertView!=null){//�жϣ����������л��յģ��������ڿգ�ֱ�ӵ����������ݡ�
			ll=(LinearLayout) convertView;
		}else{
			ll=(LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.grid_view_item, null);
		}
		
		GridViewData data=(GridViewData) getItem(position);
		ImageView icon=(ImageView) ll.findViewById(R.id.prize_img);
		TextView name=(TextView) ll.findViewById(R.id.prize_name_tv);
		
		icon.setImageResource(data.getIconId());
//		icon.setScaleType(ScaleType.FIT_XY);
		name.setText(data.getName());
		return ll;
	}
	
//	@Override
//	public void onItemClick(AdapterView<?> parent, View view, int position,
//			long id) {
//		GridViewData data=(GridViewData) getItem(position);
//		Toast.makeText(getContext(),String.format("����:%s", data.getName()), Toast.LENGTH_LONG).show();
//		
//	}

}
