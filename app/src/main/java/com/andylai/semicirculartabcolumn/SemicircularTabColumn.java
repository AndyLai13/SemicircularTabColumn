package com.andylai.semicirculartabcolumn;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SemicircularTabColumn extends LinearLayout {

	private final List<View> selectableViews = new ArrayList<>();

	private int number = -1;
	private int decorationColor = Color.BLUE;
	private int backgroundColor = Color.RED;
	private float cornerRadius = 20;
	private Callback callback;

	public interface Callback {
		void onSelected(int index);
	}

	public SemicircularTabColumn(Context context) {
		this(context, null);
	}

	public SemicircularTabColumn(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SemicircularTabColumn(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		setOrientation(VERTICAL);
	}

	public void setCallback(Callback callback) {
		this.callback = callback;
	}

	public void setDecorationColor(int color) {
		decorationColor = color;
	}

	@Override
	public void setBackgroundColor(int backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public void setDecorationHeight() {

	}

	public void setCornerRadius(float radius) {
		cornerRadius = radius;
	}

	public void setIconViews(@NonNull View[] views) {
		number = views.length;
		for (int i = 0; i < number; i++) {
			SelectableView selectableView = createSelectableView();
			selectableView.addView(views[i]);
			selectableViews.add(selectableView);
		}
		for (int i = 0; i < number; i++) {
			DecorationView decorationView = createDecorationView();
			addView(decorationView);
			addView(selectableViews.get(i));
		}
		// add last DecorationView
		DecorationView decorationView = createDecorationView();
		addView(decorationView);

		for (int i = 0; i < selectableViews.size(); i++) {
			int finalI = i;
			selectableViews.get(i).setOnClickListener(v -> {
				setSelectedIndex(finalI);
				if (callback != null) callback.onSelected(finalI);
			});
		}
		// set initial state as 0
		setSelectedIndex(0);
	}

	private DecorationView createDecorationView() {
		DecorationView decorationView = new DecorationView(getContext());
		decorationView.setDecorationColor(decorationColor);
		decorationView.setBackgroundColor(backgroundColor);
		decorationView.setCornerRadius(cornerRadius);
		return decorationView;
	}

	private SelectableView createSelectableView() {
		SelectableView selectableView = new SelectableView(getContext());
		selectableView.setDecorationColor(decorationColor);
		selectableView.setBackgroundColor(backgroundColor);
		selectableView.setCornerRadius(cornerRadius);
		return selectableView;
	}

	public void setSelectedIndex(int index) {
		if (index < number && index > -1) {
			int viewIndex = 2 * index + 1;
			for (int i = 0; i < getChildCount(); i++) {
				View view = getChildAt(i);
				if (view instanceof DecorationView) {
					if (i == viewIndex - 1) {
						((DecorationView) view).setTopDecoration();
					} else if (i == viewIndex + 1) {
						((DecorationView) view).setBottomDecoration();
					} else {
						((DecorationView) view).setNoneDecoration();
					}
				} else if (view instanceof SelectableView) {
					view.setSelected(i == viewIndex);

				} else {
					throw new IllegalStateException("No way");
				}
			}
		}
	}
}