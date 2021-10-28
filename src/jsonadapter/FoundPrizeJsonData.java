package jsonadapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.widget.ImageView;
import bean.FoundPointsPrizeDataBean;

import com.westsoft.headlines.R;

/*
 * 发现---积分商城  奖品兑换  数据解析
 * */
public class FoundPrizeJsonData {

	private static List<FoundPointsPrizeDataBean> mListPrizeData = new ArrayList<FoundPointsPrizeDataBean>();

	// 解析
	// public static List<FoundPointsPrizeDataBean> jsonParse(String jsonStr) {
	// try {
	// // 解析JSON数据
	// JSONObject jsonObject = new JSONObject(jsonStr);
	// // Log.e("======", "====PointsPrizeJsonData=====jsonStr"+
	// // jsonObject);
	// JSONArray jsonArray = jsonObject.getJSONArray("data");
	// for (int i = 0; i < jsonArray.length(); i++) {
	// // 获取JSON对象中JSON数组的对�?
	// JSONObject jArrayObject = jsonArray.getJSONObject(i);
	// String id = jArrayObject.getString("Id");
	// String scores = jArrayObject.getString("scores");
	// String img_src = jArrayObject.getString("img_src");
	// String name = jArrayObject.getString("name");
	// // 将数据添加到集合�?
	// FoundPointsPrizeDataBean prizeData = new FoundPointsPrizeDataBean(
	// id, scores, img_src, name);
	// //
	// mListPrizeData.add(prizeData);
	// }
	// } catch (JSONException e) {
	// e.printStackTrace();
	// }
	// return mListPrizeData;
	// }

	public static List<FoundPointsPrizeDataBean> jsonParse(String jsonStr) {

		List<String> mTitles = Arrays.asList("我的笔记","地图导航","我的课表","精选美文","诗歌收藏","故事会");
		final int imgViews[] = { R.drawable.myimage_10, R.drawable.myimage_07,
				R.drawable.myimage_11,R.drawable.myimage_05,R.drawable.myimage_02,R.drawable.myimage_01 };

		FoundPointsPrizeDataBean prizeData = null;
		for (int i = 0; i < imgViews.length; i++) {
			for (int j = i; j < mTitles.size(); j++) {
				// 将数据添加到集合
				prizeData = new FoundPointsPrizeDataBean(imgViews[i],
						mTitles.get(j));
				mListPrizeData.add(prizeData);
				break;
			}
		}

		return mListPrizeData;
	}
}
