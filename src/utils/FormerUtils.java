package utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bean.UserLogin;

public class FormerUtils {
	private static List<UserLogin> lists = new ArrayList<UserLogin>();
	private static String avatar_url;
	private static String id;
	private static String nickname;
	private static String reg_type;

	public static List<UserLogin> getParse(String parseStr) {
		try {
			JSONObject root = new JSONObject(parseStr);
			
			JSONObject jsonObject = root.getJSONObject("data");
			
			JSONArray jsonArray = jsonObject.getJSONArray("topics");
			
			for (int i = 0; i < jsonArray.length(); i++) {
				
				JSONObject jsonObject2 = jsonArray.getJSONObject(i);
				
				JSONArray jsonArray2 = jsonObject2.getJSONArray("topics");
				
				for (int j = 0; j < jsonArray2.length(); j++) {
					
					JSONObject jsonObject3 = jsonArray2.getJSONObject(j);
					
					JSONObject jsonObject4 = jsonObject3.getJSONObject("user");
					
					avatar_url = jsonObject4.getString("avatar_url");
					
					id = jsonObject4.getString("id");
					
					nickname = jsonObject4.getString("nickname");
					
					reg_type = jsonObject4.getString("reg_type");
					
					UserLogin user = new UserLogin();
					user.setId(id);
					user.setNickname(nickname);
					user.setReg_type(reg_type);
					user.setAvatar_url(avatar_url);
					lists.add(user);
//					Log.e("==", "==id==" + id + "==nickname==" + nickname
//							+ "==reg_type==" + reg_type + "==avatar_url=="
//							+ avatar_url);
				}
			}
			return lists;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
}
