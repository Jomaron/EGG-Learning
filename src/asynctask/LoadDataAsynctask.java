package asynctask;

import java.io.UnsupportedEncodingException;
import java.util.List;

import jsonadapter.Myadapter;
import utils.FormerUtils;
import utils.Httputils;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import bean.UserLogin;

public class LoadDataAsynctask extends AsyncTask<String, Void, String> {
	private Context context;
	private ListView listView;

	public LoadDataAsynctask(Context context, ListView listView) {
		super();
		this.context = context;
		this.listView = listView;
	}

	@Override
	protected String doInBackground(String... params) {
		byte[] loadData = Httputils.LoadData(params[0], "GET");
		try {
			String dataStr = new String(loadData, 0, loadData.length, "utf-8");
			// Log.e("==", "==data=="+dataStr);
			return dataStr;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		// Log.e("==", "==result=="+result);
		List<UserLogin> parse = FormerUtils.getParse(result);
		Log.e("==", "==parse==" + parse.size());
		Myadapter adapter = new Myadapter(parse, context);
		listView.setAdapter(adapter);
	}

}
