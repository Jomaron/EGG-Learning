package asynctask;


import com.westsoft.headlines.R;

import utils.Httputils;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class LoadImageAsync extends AsyncTask<String, Void, Bitmap> {
	private Context context;
	private ImageView imageView;

	public LoadImageAsync(Context context, ImageView imageView) {
		super();
		this.context = context;
		this.imageView = imageView;
	}

	@Override
	protected Bitmap doInBackground(String... params) {
		byte[] loadData = Httputils.LoadData(params[0], "GET");
		Bitmap bitmap = null;
		if (loadData != null && loadData.length > 0) {
			bitmap = BitmapFactory
					.decodeByteArray(loadData, 0, loadData.length);

		}
		return bitmap;
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		super.onPostExecute(result);
		if (result != null) {
			imageView.setImageBitmap(result);
		} else {
			imageView.setImageResource(R.drawable.ic_launcher);
		}
	}

}
