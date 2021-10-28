package asynctask;

import bean.FoundPointsRedDataBean;

import com.google.gson.Gson;


/*
 * 发现---积分商城  红包来袭  数据解析
 * */
public class FoundRedGsonData {

//	private static List<FoundRedGsonData> mListRedData = new ArrayList<FoundRedGsonData>();
	
//	private static byte[] datas;

	public static FoundPointsRedDataBean jsonParse(String jsonStr) {
//		try {
//			// 解析JSON数据
//			JSONObject jsonObject = new JSONObject(jsonStr);
//			//
//			boolean status = jsonObject.getBoolean("status");
//			int count = jsonObject.getInt("count");
//			// data对象
//			JSONObject data = jsonObject.getJSONObject("data");
//			int maxnum = data.getInt("maxnum");
//			int today_state = data.getInt("today_state");
//			// banner_img数组
//			JSONArray banner_img = data.getJSONArray("banner_img");
//
//			for (int i = 0; i < banner_img.length(); i++) {
//				JSONObject jArrayObject = banner_img.getJSONObject(i);
//				String img_src = jArrayObject.getString("img_src");
//				// action对象
//				JSONObject action = jArrayObject.getJSONObject("action");
//				String target = action.getString("target");
//				String type = action.getString("type");
//				String value = action.getString("value");
//				//
//			}
//
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
		Gson gson = new Gson();
		FoundPointsRedDataBean redDataBean = gson.fromJson(jsonStr, FoundPointsRedDataBean.class);
		
		return redDataBean;
	}
}
