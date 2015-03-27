package in.excogitation.material_design_example;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {

	private ActionBarDrawerToggle mDrawerToggle;
	private DrawerLayout mDrawerLayout;

	private View containerView;

	private boolean mUserLearnedDrawer;
	private boolean mFromSavedInsatanceState;

	ListView lv;
	ArrayList<String> data;
	ArrayAdapter<String> adapter;

	public NavigationDrawerFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mUserLearnedDrawer = Boolean.getBoolean(readPref(getActivity(), "Key", "false"));
		if (savedInstanceState != null) {
			mFromSavedInsatanceState = true;
		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
		lv = (ListView) v.findViewById(R.id.listView_sidemenu);
		data = new ArrayList<>();
		data.add("Menu Item 1");
		data.add("Menu Item 2");
		data.add("Menu Item 3");
		data.add("Menu Item 4");
		data.add("Menu Item 5");

		adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data);
		lv.setAdapter(adapter);

		return v;


	}

	public void setUp(DrawerLayout drawerLayout, Toolbar toolbar) {
		containerView = getActivity().findViewById(R.id.fragment_navdrawer);
		mDrawerLayout = drawerLayout;
		mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawerOpen, R.string.drawerClosed) {

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				if (!mUserLearnedDrawer) {
					mUserLearnedDrawer = true;
					save2Pref(getActivity(), "Key", "true");
				}
				getActivity().invalidateOptionsMenu();
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				getActivity().invalidateOptionsMenu();
			}
		};

		if (!mUserLearnedDrawer && !mFromSavedInsatanceState) {
			mDrawerLayout.openDrawer(containerView);
		}

		mDrawerLayout.setDrawerListener(mDrawerToggle);
		mDrawerLayout.post(new Runnable() {
			@Override
			public void run() {
				mDrawerToggle.syncState();
			}
		});
	}

	public static void save2Pref(Context context, String key, String val) {
		SharedPreferences pref = context.getSharedPreferences("MY_PREF", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		editor.putString(key, val);
		editor.apply();

	}

	public static String readPref(Context context, String key, String default_val) {
		SharedPreferences pref = context.getSharedPreferences("MY_PREF", Context.MODE_PRIVATE);
		return pref.getString(key, default_val);
	}


}
