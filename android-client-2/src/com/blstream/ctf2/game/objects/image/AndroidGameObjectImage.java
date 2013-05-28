package com.blstream.ctf2.game.objects.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.blstream.ctf2.Constants;
import com.blstream.ctf2.R;

/**
 * 
 * @author Karol Firmanty
 * 
 */
public class AndroidGameObjectImage implements GameObjectImage {
	private Bitmap mImage;
	private Context mCtx;

	public AndroidGameObjectImage(Constants.GAME_OBJECT_TYPE type, Constants.TEAM team, Context context) {
		mCtx = context;
		switch (type) {
		case GAMER:
			loadGamerImage(team);
			break;

		case FLAG:
			loadFlagImage(team);
			break;
		}
	}
	//TODO change commented lines when assets become available
	@Override
	public void loadGamerImage(Constants.TEAM team) {
		switch (team) {
		case TEAM_RED:
			//mImage = BitmapFactory.decodeResource(mCtx.getResources(), R.drawable.redGamer);
			break;
		case TEAM_BLUE:
			//mImage = BitmapFactory.decodeResource(mCtx.getResources(), R.drawable.blueGamer);
			break;
		}

	}

	@Override
	public void loadFlagImage(Constants.TEAM team) {
		switch (team) {
		case TEAM_RED:
			mImage = BitmapFactory.decodeResource(mCtx.getResources(), R.drawable.red);
			break;
		case TEAM_BLUE:
			mImage = BitmapFactory.decodeResource(mCtx.getResources(), R.drawable.blue);
			break;
		}
	}

	public Bitmap getImage() {
		return mImage;
	}

}
