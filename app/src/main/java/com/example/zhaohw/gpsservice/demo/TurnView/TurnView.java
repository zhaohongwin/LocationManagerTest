package com.example.zhaohw.gpsservice.demo.TurnView;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 上下轮播显示view
 */
public class TurnView extends FrameLayout implements View.OnClickListener {
	
	private final long DEFAULT_ANIMATION_DURATION = 1000;
	private final long DEFAULT_NOTICE_SPACE = 3000;
	
	private long mViewInterval = DEFAULT_NOTICE_SPACE;
	private List<View> mAlarmList = new ArrayList<>();
	private int mCurrentView;
	private AnimationSet mInAnimationSet;
	private AnimationSet mOutAnimationSet;
	private Handler mHandler = new Handler(Looper.getMainLooper());
	private TurnPageRunnable mTurnPageRunnable;
	private boolean mIsRunning;
	private OnItemClickListener mListener;
	
	public TurnView(Context context) {
		this(context, null);
	}
	
	public TurnView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		// 初始化动画
		createOutAnimation();
		createInAnimation();
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		// 如果是未指定大小，那么设置宽为300px
		int exceptWidth = getMeasuredWidth();
		int exceptHeight = getMeasuredHeight();
		
		int width = resolveSize(exceptWidth, widthMeasureSpec);
		int height = resolveSize(exceptHeight, heightMeasureSpec);
//		setForegroundGravity(Gravity.CENTER_VERTICAL | Gravity.TOP);
		setMeasuredDimension(width, height);
	}
	
	/**
	 * 创建item翻入动画
	 */
	private void createInAnimation() {
		mInAnimationSet = new AnimationSet(false);
		TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, 0, TranslateAnimation.RELATIVE_TO_PARENT, 1f, TranslateAnimation.RELATIVE_TO_SELF, 0f);
		AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
		mInAnimationSet.addAnimation(translateAnimation);
		mInAnimationSet.addAnimation(alphaAnimation);
		mInAnimationSet.setDuration(DEFAULT_ANIMATION_DURATION);
	}
	
	/**
	 * 创建item翻出动画
	 */
	private void createOutAnimation() {
		mOutAnimationSet = new AnimationSet(false);
		TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, 0, TranslateAnimation.RELATIVE_TO_SELF, 0f, TranslateAnimation.RELATIVE_TO_PARENT, -1f);
		AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0f);
		mOutAnimationSet.addAnimation(translateAnimation);
		mOutAnimationSet.addAnimation(alphaAnimation);
		mOutAnimationSet.setDuration(DEFAULT_ANIMATION_DURATION);
	}
	
	/**
	 * 添加View列表
	 *
	 * @param list
	 */
	public void setViewList(List<View> list) {
		if (list == null || list.size() == 0) {
			return;
		}
		
		// 暂停轮播
		pause();
		
		removeAllViews();
		if (mAlarmList == null) {
			mAlarmList = new ArrayList<>();
		}
		mAlarmList.clear();
		
		// 创建View
		for (View view : list) {
			mAlarmList.add(view);
			view.setVisibility(INVISIBLE);
			addView(view);
		}
		
		// 显示第一条
		mCurrentView = 0;
		mAlarmList.get(mCurrentView).setVisibility(VISIBLE);
		// 启动轮播
		start();
	}
	
	/**
	 * 添加item点击监听
	 *
	 * @param listener
	 */
	public void addOnItemClickListener(OnItemClickListener listener) {
		setOnClickListener(this);
		mListener = listener;
	}
	
	/**
	 * 设置翻页时间间隔
	 *
	 * @param interval
	 */
	public void setTurnPageInterval(long interval) {
		if (interval > 0) {
			mViewInterval = interval;
		}
	}
	
	/**
	 * 设置动画持续时间
	 *
	 * @param duration
	 */
	public void setAnimationDuration(long duration) {
		if (duration > 0) {
			if (mInAnimationSet != null) {
				mInAnimationSet.setDuration(duration);
			}
			if (mOutAnimationSet != null) {
				mOutAnimationSet.setDuration(duration);
			}
		}
	}
	
	public void setInAnimation(AnimationSet animation) {
		mInAnimationSet = animation;
	}
	
	public void setOutAnimation(AnimationSet animation) {
		mOutAnimationSet = animation;
	}
	
	public void start() {
		// 如果轮播正在运行中，不重复执行
		if (mIsRunning || mAlarmList == null || mAlarmList.size() < 2) {
			return;
		}
		
		if (mTurnPageRunnable == null) {
			mTurnPageRunnable = new TurnPageRunnable();
		} else {
			mHandler.removeCallbacks(mTurnPageRunnable);
		}
		mHandler.postDelayed(mTurnPageRunnable, mViewInterval);
		mIsRunning = true;
	}
	
	public void pause() {
		// 如果轮播已经停止，不重复执行
		if (!mIsRunning) {
			return;
		}
		
		if (mTurnPageRunnable != null) {
			mHandler.removeCallbacks(mTurnPageRunnable);
		}
		
		mIsRunning = false;
	}
	
	public boolean isRunning() {
		return mIsRunning;
	}
	
	public boolean couldRun() {
		return mAlarmList != null && mAlarmList.size() > 0;
	}
	
	@Override
	public void onClick(View v) {
		if (mListener != null && mAlarmList != null && mAlarmList.size() > 0) {
			mListener.onItemClick(mAlarmList.get(mCurrentView), mCurrentView);
		}
	}
	
	class TurnPageRunnable implements Runnable {
		@Override
		public void run() {
			
			// 隐藏当前的view
			View currentView = mAlarmList.get(mCurrentView);
			currentView.setVisibility(INVISIBLE);
			if (mOutAnimationSet != null) {
				currentView.startAnimation(mOutAnimationSet);
			}
			mCurrentView++;
			if (mCurrentView >= mAlarmList.size()) {
				mCurrentView = 0;
			}
			
			// 显示下一个view
			View nextView = mAlarmList.get(mCurrentView);
			nextView.setVisibility(VISIBLE);
			if (mInAnimationSet != null) {
				nextView.startAnimation(mInAnimationSet);
			}
			mHandler.postDelayed(this, mViewInterval);
		}
	}
	
	public interface OnItemClickListener {
		void onItemClick(View view, int position);
	}
}
