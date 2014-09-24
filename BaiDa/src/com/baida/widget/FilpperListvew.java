package com.baida.widget;


import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

public class FilpperListvew extends ListView {
	private float myLastX = -1;
	private float myLastY = -1;
	private boolean delete = false;
	//自定义的滑动删除监听
	private FilpperDeleteListener filpperDeleterListener;

	public FilpperListvew(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public FilpperListvew(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// 获得第一个点的x坐标
			myLastX = ev.getX(0);
			myLastY = ev.getY(0);
			break;

		case MotionEvent.ACTION_MOVE:
			// 得到最后一个点的坐标
			float deltaX = ev.getX(ev.getPointerCount() - 1) - myLastX;
			float deltaY = Math.abs(ev.getY(ev.getPointerCount() - 1) - myLastY);
			// 可以滑动删除的条件：横向滑动大于100，竖直差小于50
			if (deltaX > 100.0 && deltaY < 50) {
				delete = true;
			}
			break;

		case MotionEvent.ACTION_UP:
			if (delete && filpperDeleterListener != null) {
				filpperDeleterListener.filpperDelete(myLastX,myLastY);
			}
			reset();
			break;
		}
		return super.onTouchEvent(ev);
	}

	public void reset(){
		delete = false ;
		myLastX = -1 ;
		myLastY = -1 ;
	}
	
	public void setFilpperDeleteListener(FilpperDeleteListener f) {
		filpperDeleterListener = f;
	}

	//自定义的接口
	public interface FilpperDeleteListener {
		public void filpperDelete(float xPosition,float yPosition);
	}
	
}
