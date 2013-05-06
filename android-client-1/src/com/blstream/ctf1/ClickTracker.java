package com.blstream.ctf1;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.Button;

public class ClickTracker {
	
	private static SharedPreferences mPrefs;
	private static Editor mEditor;
	private static boolean flag;
	public ClickTracker(Context context)
	{
		mPrefs = context.getSharedPreferences("CLICK", Context.MODE_PRIVATE);
		flag = true;
		
	}
	public static void saveClick(Context context, Button btn)
	{
		if(flag)
		{
			mPrefs = context.getSharedPreferences("CLICK", Context.MODE_PRIVATE);
			mEditor = mPrefs.edit();
			String log = mPrefs.getString("CLICKS", "defValue");
			log+="\n"+btn.getText().toString() + " pressed in " + context.getClass().getSimpleName();
			mEditor.putString("CLICKS", log);
			mEditor.commit();
		}
	}
	public static void init(Context context)
	{
		if(flag)
		{
			mEditor = mPrefs.edit();
			String log = "Application started\n";
			mEditor.putString("CLICKS", log);
			mEditor.commit();
		}
	}
	public static void setFlag(boolean arg) 
	{
		flag = arg;
	}
}