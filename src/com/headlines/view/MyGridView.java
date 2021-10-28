package com.headlines.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class MyGridView extends GridView {
	
	
	public MyGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyGridView(Context context) {
		super(context);
	}

//	@Override
//	public boolean dispatchTouchEvent(MotionEvent ev) {
//		if (ev.getAction() == MotionEvent.ACTION_MOVE) {
//			// 禁止GridView滑动
//			return true;
//		}
//		return super.dispatchTouchEvent(ev);
//	}
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);

	}

}
