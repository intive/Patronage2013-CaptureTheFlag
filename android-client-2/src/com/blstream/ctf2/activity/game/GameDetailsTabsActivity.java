package com.blstream.ctf2.activity.game;

import java.util.ArrayList;

import com.blstream.ctf2.Constants;
import com.blstream.ctf2.R;
import com.blstream.ctf2.domain.GameDetails;
import com.blstream.ctf2.services.GameServices;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author Rafal Tatol
 *
 */
public class GameDetailsTabsActivity extends FragmentActivity {

	final static String TAB_DETAILS = "Details tab";
	final static String TAB_PLAYERS = "Players tab";
	final static String TAB_MAP = "Map tab";

	private String mGameId;
	private Context mContext;
	private TabHost mTabHost;
	private GameDetailsFragment mGameDetailsFragment;
	private GameDetailsPlayersFragment mGameDetailsPlayersFragment;
	private GameDetailsMapFragment mGameDetailsMapFragment;
	private GameDetails mGameDetails;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_game_details_tabs);

		mContext = this;
		mGameId = getIntent().getExtras().getString(Constants.ID);

		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setOnTabChangedListener(listener);
		mTabHost.setup();
	}

	@Override
	protected void onResume() {
		super.onResume();
		GameServices mGameServices = new GameServices(this);
		mGameServices.getGameDetails(GameDetailsTabsActivity.this, mGameId);
	}

	public void onClickButton(View v) {
		switch (v.getId()) {
		case R.id.joinButton:
			//GameServices mGameServices = new GameServices(this);
			//mGameServices.joinTheGame(GameDetailsTabsActivity.this, mGameId);
			break;
		case R.id.editButton:
			break;
		//case R.id.playersButton:
			//Intent intent = new Intent("com.blstream.ctf2.GAMEPLAYERSACTIVITY");
			//intent.putExtra(Constants.ID, mGameId);
			//startActivity(intent);
			//break;
		case R.id.startButton:
			Intent game_intent = new Intent("com.blstream.ctf2.GAMEACTIVITY");
			startActivity(game_intent);
			break;			
		}
	}

	public void setGameDetails(GameDetails gameDetails) {
		mGameDetails = gameDetails;
		mTabHost.clearAllTabs();
		if (mGameDetails != null) {
			initializeTabs();
		} else {
			 Toast.makeText(this, R.string.server_error, Toast.LENGTH_SHORT).show();
			 this.finish();
		}
	}

	TabHost.OnTabChangeListener listener = new TabHost.OnTabChangeListener() {
		public void onTabChanged(String tabId) {
			if (tabId.equals(TAB_DETAILS)) {
				mGameDetailsFragment = new GameDetailsFragment();
				Bundle args = new Bundle();
				args.putSerializable(Constants.KEY_GAME_DETAILS, mGameDetails);
				mGameDetailsFragment.setArguments(args);
				pushFragments(mGameDetailsFragment);
			} else if (tabId.equals(TAB_PLAYERS)) {
				GameServices mGameServices = new GameServices(mContext);
				mGameServices.getGamePlayers(GameDetailsTabsActivity.this, mGameId);
			} else if (tabId.equals(TAB_MAP)) {
				mGameDetailsMapFragment = new GameDetailsMapFragment();
				Bundle args = new Bundle();
				args.putSerializable(Constants.KEY_GAME_AREA, mGameDetails.getLocalization());
				args.putSerializable(Constants.KEY_TEAM_RED, mGameDetails.getTeamRed());
				args.putSerializable(Constants.KEY_TEAM_BLUE, mGameDetails.getTeamBlue());
				mGameDetailsMapFragment.setArguments(args);
				pushFragments(mGameDetailsMapFragment);
			}
		}
	};

	public void showPlayersFragment(ArrayList<String> playersList) {
		mGameDetailsPlayersFragment = new GameDetailsPlayersFragment();
		Bundle args = new Bundle();
		args.putStringArrayList("key", playersList);
		mGameDetailsPlayersFragment.setArguments(args);
		pushFragments(mGameDetailsPlayersFragment);
	}

	public void pushFragments(Fragment fragment) {
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction ft = manager.beginTransaction();
		ft.replace(android.R.id.tabcontent, fragment);
		ft.commit();
	}

	public void initializeTabs() {
		TabHost.TabSpec spec = null;
		mTabHost.setCurrentTab(0);

		spec = mTabHost.newTabSpec(TAB_DETAILS);
		spec.setContent(new TabHost.TabContentFactory() {
			public View createTabContent(String tag) {
				return findViewById(android.R.id.tabcontent);
			}
		});
		spec.setIndicator(createTabView(R.string.game_details));
		mTabHost.addTab(spec);

		spec = mTabHost.newTabSpec(TAB_PLAYERS);
		spec.setContent(new TabHost.TabContentFactory() {
			public View createTabContent(String tag) {
				return findViewById(android.R.id.tabcontent);
			}
		});
		spec.setIndicator(createTabView(R.string.game_players));
		mTabHost.addTab(spec);

		spec = mTabHost.newTabSpec(TAB_MAP);
		spec.setContent(new TabHost.TabContentFactory() {
			public View createTabContent(String tag) {
				return findViewById(android.R.id.tabcontent);
			}
		});
		spec.setIndicator(createTabView(R.string.game_map));
		mTabHost.addTab(spec);
	}

	private View createTabView(final int id) {
		View view = LayoutInflater.from(this).inflate(R.layout.game_details_tabs, null);
		((TextView) view.findViewById(R.id.tab_text)).setText(id);
		return view;
	}

}
