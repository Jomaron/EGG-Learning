package com.headlines.view;

import java.util.List;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.westsoft.headlines.R;

public class ViewPagerIndicator extends LinearLayout {

	private Paint mPaint;
	private Path mPath;
	private int mTriangleWidth;
	private int mTriangleHight;
	private static final float RADIO_TRIANGLE_WIDTH = 1 / 6F;
	
	/*
	 * �����εĵױ������
	 */
	private final int DIMENSION_TRIANGLE_WIDTH_MAX=(int) (getscreenWidth()/3*RADIO_TRIANGLE_WIDTH);
	
	private int minitTranslationX;
	private int mTranslationX;
	private int mTabVisibleCount;
	private static final int COUNT_DEFAULT_TAB = 4;
	private List<String> mTitles;
//	private static final int COLOR_TEXT_NORMAL = 0x77FFFFFF;
//	private static final int COLOR_TEXT_HIGHLIGHT = 0xFFFFFFFF;
	
	private static final int COLOR_TEXT_NORMAL = 0x7f050003;//  
	private static final int COLOR_TEXT_HIGHLIGHT =  0x7f050005;//��ɫ

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {

		super.onSizeChanged(w, h, oldw, oldh);

		mTriangleWidth = (int) (w / mTabVisibleCount * RADIO_TRIANGLE_WIDTH);
		mTriangleWidth=Math.min(mTriangleWidth, DIMENSION_TRIANGLE_WIDTH_MAX);
		minitTranslationX = w / mTabVisibleCount / 2 - mTriangleWidth / 2;

		initTriangle();
	}

	/*
	 * ��ʼ��������
	 */
	private void initTriangle() {

		mTriangleHight = mTriangleWidth / 2;
		mPath = new Path();
		mPath.moveTo(0, 0);
		mPath.lineTo(mTriangleWidth, 0);
		mPath.lineTo(mTriangleWidth / 2, -mTriangleHight);
		mPath.close();
	}

	public ViewPagerIndicator(Context context) {

		this(context, null);
	}

	public ViewPagerIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);

		// ��ȡ�ɼ�Tab������
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.ViewPagerIndicator);
		mTabVisibleCount = a.getInt(
				R.styleable.ViewPagerIndicator_visible_tab_count,
				COUNT_DEFAULT_TAB);

		if (mTabVisibleCount < 0) {
			mTabVisibleCount = COUNT_DEFAULT_TAB;
		}
		a.recycle();

		// ��ʼ������
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.parseColor("#157efb"));
		mPaint.setStyle(Style.FILL);
		// ���������νǵ�Բ��
		mPaint.setPathEffect(new CornerPathEffect(3));
	}

	/*
	 * ����������
	 */
	@Override
	protected void dispatchDraw(Canvas canvas) {

		canvas.save();
		canvas.translate(minitTranslationX + mTranslationX, getHeight() + 4);
		canvas.drawPath(mPath, mPaint);

		canvas.restore();
		super.dispatchDraw(canvas);

	}

	@Override
	protected void onFinishInflate() {

		super.onFinishInflate();
		int cCount = getChildCount();
		if (cCount == 0) {
			return;
		}
		for (int i = 0; i < cCount; i++) {
			View view = getChildAt(i);
			LinearLayout.LayoutParams lp = (LayoutParams) view
					.getLayoutParams();
			lp.weight = 0;
			lp.width = getscreenWidth() / mTabVisibleCount;
			view.setLayoutParams(lp);
		}
		setItemClickEvent();
	}

	/*
	 * ����ֻ���Ļ���
	 */
	private int getscreenWidth() {

		WindowManager wm = (WindowManager) getContext().getSystemService(
				Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);

		return outMetrics.widthPixels;
	}

	/*
	 * ָʾ����ViewPager����
	 */
	public void scroll(int position, float offset) {

		int tabWidth = getWidth() / mTabVisibleCount;
		mTranslationX = (int) (tabWidth * (offset + position));

		// �����ƶ�����Tab�ƶ������һ��ʱ
		if (position >= (mTabVisibleCount - 2) && offset > 0
				&& getChildCount() > mTabVisibleCount) {

			if (mTabVisibleCount != 1) {
				this.scrollTo((position - (mTabVisibleCount - 2)) * tabWidth
						+ (int) (tabWidth * offset), 0);
			} else {
				this.scrollTo(position * tabWidth + (int) (tabWidth * offset),
						0);
			}

		}

		invalidate();
	}

	public void setTabItemTitles(List<String> titles) {

		if (titles != null && titles.size() > 0) {
			this.removeAllViews();
			mTitles = titles;
			for (String title : mTitles) {
				addView(generateTextView(title));
			}
			
			setItemClickEvent();
		}
	}

	/*
	 * �ɼ���Tab����(�ɲ�д)
	 */
	public void setVisibleTabCount(int count) {
		mTabVisibleCount = count;
	}

	/*
	 * ����title����Tab
	 */
	private View generateTextView(String title) {

		TextView tv = new TextView(getContext());
		LinearLayout.LayoutParams lp = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		lp.width = getscreenWidth() / mTabVisibleCount;
		tv.setText(title);
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
		tv.setTextColor(COLOR_TEXT_NORMAL);//��ɫ
		tv.setLayoutParams(lp);

		return tv;
	}

	private ViewPager mViewPager;

	public interface PageOnchangeListener {

		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels);

		public void onPageSelected(int position);

		public void onPageScrollStateChanged(int state);
	}

	public PageOnchangeListener mListener;

	public void setOnPageChangeListener(PageOnchangeListener listener) {

		this.mListener = listener;
	}

	/*
	 * ���ù�����ViewPager
	 */
	public void setViewPager(ViewPager viewPager, int pos) {

		mViewPager = viewPager;
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			public void onPageSelected(int position) {

				if (mListener != null) {
					mListener.onPageSelected(position);
				}
				highLightTextView(position);
			}

			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {

				// tabWidth*positionOffset+position*tabWidth
				scroll(position, positionOffset);

				if (mListener != null) {
					mListener.onPageScrolled(position, positionOffset,
							positionOffsetPixels);
				}
			}

			public void onPageScrollStateChanged(int state) {

				if (mListener != null) {
					mListener.onPageScrollStateChanged(state);
				}
			}
		});

		mViewPager.setCurrentItem(pos);
		highLightTextView(pos);
	}

	/*
	 * ����Tab����ɫ
	 */
	private void resetTextColor() {

		for (int i = 0; i < getChildCount(); i++) {
			View view = getChildAt(i);
			if (view instanceof TextView) {

				((TextView) view).setTextColor(COLOR_TEXT_HIGHLIGHT);//��ɫ
			}
		}

	}

	/*
	 * ����Tab�ı�����
	 */
	private void highLightTextView(int pos) {

		resetTextColor(); 
		View view = getChildAt(pos);
		if (view instanceof TextView) {

			((TextView) view).setTextColor(COLOR_TEXT_HIGHLIGHT);
		}
	}


	/*
	 * ����Tab�ĵ���¼�
	 */
	private void setItemClickEvent() {

		int cCount=getChildCount();
		for(int i=0;i<cCount;i++){
			
			final int j=i;
			View view=getChildAt(i);
			view.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {

					mViewPager.setCurrentItem(j);
				}
			});
		}
	}
}
