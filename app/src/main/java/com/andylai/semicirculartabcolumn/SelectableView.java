package com.andylai.semicirculartabcolumn;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SelectableView extends FrameLayout {
	private Drawable selectedDrawable;
	private Drawable unselectedDrawable;
	private int decorationColor = Color.RED;
	private int backgroundColor = Color.WHITE;
	private float cornerRadius = 20;

	public SelectableView(@NonNull Context context) {
		this(context, null);
	}

	public SelectableView(@NonNull Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SelectableView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		refreshDrawables();
		setSelected(false);
	}

	@Override
	public void setSelected(boolean selected) {
		super.setSelected(selected);
		setBackground(selected ? selectedDrawable : unselectedDrawable);
	}

	public void setDecorationColor(int color) {
		decorationColor = color;
		refreshDrawables();
	}

	@Override
	public void setBackgroundColor(int color) {
		super.setBackgroundColor(color);
		backgroundColor = color;
		refreshDrawables();
	}

	public void setCornerRadius(float radius) {
		cornerRadius = radius;
		refreshDrawables();
	}

	private void refreshDrawables() {
		selectedDrawable = getSelectedDrawable();
		unselectedDrawable = getUnselectedDrawable();
	}

	private Drawable getSelectedDrawable() {
		Drawable back = getDrawableWithColor(decorationColor);
		ShapeDrawable front = new ShapeDrawable(
				new RoundRectShape(new float[]{
						cornerRadius, cornerRadius,
						0, 0,
						0, 0,
						cornerRadius, cornerRadius
				}, null, null));
		front.setIntrinsicHeight(50);
		front.getPaint().setColor(backgroundColor);
		front.getPaint().setStyle(Paint.Style.FILL);
		Drawable[] drawables = new Drawable[]{back, front};
		return new LayerDrawable(drawables);
	}

	private Drawable getUnselectedDrawable() {
		return getDrawableWithColor(decorationColor);
	}

	private ShapeDrawable getDrawableWithColor(int color) {
		ShapeDrawable rect = new ShapeDrawable(new RectShape());
		rect.setIntrinsicHeight(50);
		rect.getPaint().setColor(color);
		rect.getPaint().setStyle(Paint.Style.FILL);
		return rect;
	}
}
