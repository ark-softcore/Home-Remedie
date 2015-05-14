package com.ark.homeremedies.apputils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SectionIndexer;

import com.ark.homeremedies.R;
import com.ark.homeremedies.common.StickyListHeadersListView;

/**
 * Class : Sidebar.java extends : View class Responsible for the side bar in the
 * existing store page
 */
public class Sidebar extends View {
	public static final Character[] letters = { '#', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	private SectionIndexer mSectionIndexter = null;
	private StickyListHeadersListView mList;

	private Paint paint;

	private int blockSize;

	private boolean dirty = true;

	private int mGravity;

	// Defining the constructors
	public Sidebar(Context context) {
		super(context);
		init();
	}

	public Sidebar(Context context, AttributeSet attrs) {
		super(context, attrs);

		initAttrs(context, attrs);
		init();
	}

	public Sidebar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		initAttrs(context, attrs);
		init();
	}

	private void initAttrs(Context context, AttributeSet attrs) {
		TypedArray a = null;

		try {
			a = context.obtainStyledAttributes(attrs, R.styleable.SideBar, 0, 0);

			int index = a.getInt(R.styleable.SideBar_android_gravity, -1);
			if (index >= 0) {
				setGravity(index);
			}
		} catch (Exception e) {
		} finally {
			if (a != null) {
				a.recycle();
			}
		}

	}

	public void setGravity(int gravity) {
		if (mGravity != gravity) {
			mGravity = gravity;
		}
	}

	// Function to initialise the side bar elements.
	private void init() {
		setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (dirty) {
					return true;
				}

				if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
					int touchY = (int) event.getY();
					int idx = touchY / blockSize;

					if (idx >= letters.length) {
						idx = letters.length - 1;
					} else if (idx < 0) {
						idx = 0;
					}

					if (idx == 0) {
						mList.setSelection(0);
						return true;
					}

					if (mSectionIndexter == null) {
						mSectionIndexter = (SectionIndexer) mList.getAdapter();
					}
					// Getting the position of the element touched
					int position = mSectionIndexter.getPositionForSection(idx);

					if (position == -1) {
						return true;
					}

					// setting the position of the list to the touched alphabet.
					mList.setSelection(position);
				}

				return true;
			}
		});
	}

	private void initPaint() {
		blockSize = getHeight() / letters.length;

		paint = new Paint();
		paint.setTextSize((blockSize * 2) / 3);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setColor(Color.BLACK);

		dirty = false;
	}

	// Function to set the listview
	public void setListView(StickyListHeadersListView _list) {
		mList = _list;
		mSectionIndexter = (SectionIndexer) _list.getAdapter();
	}

	// Defining the style for the sidebar
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		if (dirty || paint == null) {
			initPaint();
		}

		float width = getMeasuredWidth() / 2;
		if (mGravity == Gravity.RIGHT) {
			width = getMeasuredWidth() - paint.measureText(String.valueOf(letters[0]));
		} else if (mGravity == Gravity.LEFT) {
			width = 0;
		}
		float heightCenter = getMeasuredHeight() / letters.length;
		for (int i = 0; i < letters.length; i++) {
			canvas.drawText(String.valueOf(letters[i]), width, heightCenter + (i * heightCenter), paint);
		}
	}

	public static int getCharPosition(char c) {
		for (int i = 1; i < letters.length; i++) {
			if (letters[i] == c) {
				return i;
			}
		}

		return 0;
	}

}