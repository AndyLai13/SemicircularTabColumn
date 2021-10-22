package com.andylai.semicirculartabcolumn;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		View[] views = new View[]{
				LayoutInflater.from(this).inflate(R.layout.content_cell, null, false),
				LayoutInflater.from(this).inflate(R.layout.content_cell, null, false)
		};

		SemicircularTabColumn semicircularTabColumn = findViewById(R.id.semicircularTabColumn);
		semicircularTabColumn.setDecorationColor(getResources().getColor(R.color.teal_700));
		semicircularTabColumn.setBackgroundColor(Color.WHITE);
		semicircularTabColumn.setCornerRadius(15);
		semicircularTabColumn.setIconViews(views);
		semicircularTabColumn.setCallback(index -> Log.d("Andy", "index = " + index));
	}
}