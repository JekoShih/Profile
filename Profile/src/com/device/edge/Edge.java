package com.WH;

import android.app.Activity;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;

public class WH {
	public static int Width;
	public static int Height;

	// Width & Height
	public static void getDisplayMetrics(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		Width = dm.widthPixels;
		Height = dm.heightPixels;
		Log.e("CTC", Width + "" + Height);
	}
	
	public static void getSize(Activity activity)
	{
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		double x = Math.pow(dm.widthPixels/dm.xdpi,2);
		double y = Math.pow(dm.heightPixels/dm.ydpi,2);
		double screenInches = Math.sqrt(x+y);
		Log.e("screenInches","Screen inches : " + screenInches);
	}
	
	public static int getWeightPercent(double Per) {
		return (int) ((Per > 100.0) ? Width : ((Width * Per) / 100));
	}

	public static int getHeightPercent(double Per) {
		return (int) ((Per > 100.0) ? Height : ((Height * Per) / 100));
	}
}