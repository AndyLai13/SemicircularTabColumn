package com.andylai.semicirculartabcolumn;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

public class DecorationView extends androidx.appcompat.widget.AppCompatImageView {
	private final Drawable drawableTop;
	private final Drawable drawableBottom;
	private final Drawable drawableNone;

	public DecorationView(@NonNull Context context) {
		this(context, null);
	}

	public DecorationView(@NonNull Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DecorationView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		drawableTop = ResourcesCompat.getDrawable(getResources(), R.drawable.box_color_top, null);
		drawableBottom = ResourcesCompat.getDrawable(getResources(), R.drawable.box_color_bottom, null);
		drawableNone = ResourcesCompat.getDrawable(getResources(), R.drawable.box_color_none, null);
		setTopDecoration();
	}

	@Override
	public void setBackgroundColor(int color) {
		super.setBackgroundColor(color);
	}

	public void setDecorationColor(int color) {
		drawableTop.setTint(color);
		drawableBottom.setTint(color);
		drawableNone.setTint(color);
	}

	public void setCornerRadius(float radius) {
		((GradientDrawable) drawableTop).setCornerRadii(new float[]{
				0, 0,
				0, 0,
				radius, radius,
				0, 0,
		});
		((GradientDrawable) drawableBottom).setCornerRadii(new float[]{
				0, 0,
				radius, radius,
				0, 0,
				0, 0,
		});
	}


	public void setTopDecoration() {
		setImageDrawable(drawableTop);
	}

	public void setBottomDecoration() {
		setImageDrawable(drawableBottom);
	}

	public void setNoneDecoration() {
		setImageDrawable(drawableNone);
	}
}
