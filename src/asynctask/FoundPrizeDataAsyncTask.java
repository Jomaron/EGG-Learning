package asynctask;

import java.util.List;

import jsonadapter.FoundPrizeJsonData;
import utils.HttpUtils2;
import adapter.FoundPrizeBaseAdapter;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;
import bean.FoundPointsPrizeDataBean;

public class FoundPrizeDataAsyncTask extends AsyncTask<String, Void, String> {
	 private GridView mGvPrize;
//	private MyGridView mGvPrize;
	private Context mContext;

	public FoundPrizeDataAsyncTask(GridView mGvPrize, Context mContext) {
		super();
		this.mGvPrize = mGvPrize;
		this.mContext = mContext;
	}

//	public FoundPrizeDataAsyncTask(MyGridView mGvPrize, Context mContext) {
//		super();
//		this.mGvPrize = mGvPrize;
//		this.mContext = mContext;
//	}

	@Override
	protected String doInBackground(String... params) {
		//
		String strData = null;
		//
		if (HttpUtils2.isNetworkConn(mContext)) {
			//
			byte[] downloadData = HttpUtils2.getData(params[0]);
			//
			try {
				strData = new String(downloadData, 0, downloadData.length,
						"utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return strData;
		} else {
			Log.e("============", "´íÎó");
			return null;
		}
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		//
		List<FoundPointsPrizeDataBean> mListPrizeData = FoundPrizeJsonData
				.jsonParse(result);
		//
		FoundPrizeBaseAdapter prizeAdapter = new FoundPrizeBaseAdapter(
				mListPrizeData, mContext);
		//
		prizeAdapter.notifyDataSetChanged();
		//
		mGvPrize.setAdapter(prizeAdapter);

	}

}
