package com.prax.lights;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.analytics.tracking.android.EasyTracker;

public class MainActivity extends Activity implements OnClickListener {

	static int DimensionX = 5;
	static int DimensionY = 5;
	Map<String, Boolean> storeMap;
	int score = 0;
	final Context context = this;
	static int difficulty = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Util.showHelp("Objective: To turn off all the Lights with the least number of moves \n" +
        		"\n" +
        		"Each switch is inter-connected with other lights in a perpendicular fashion",context);
		// DimensionX = 3;
		// DimensionY = 3;

		difficulty = getRandomFactor(5, 8);
		TextView optiView = (TextView) findViewById(R.id.Optimum);
		optiView.setText("" + difficulty);
		List<Integer> buttonList = new ArrayList<Integer>();
		buttonList.add(R.id.button11);
		buttonList.add(R.id.button12);
		buttonList.add(R.id.button13);
		buttonList.add(R.id.button14);
		buttonList.add(R.id.button15);

		buttonList.add(R.id.button21);
		buttonList.add(R.id.button22);
		buttonList.add(R.id.button23);
		buttonList.add(R.id.button24);
		buttonList.add(R.id.button25);

		buttonList.add(R.id.button31);
		buttonList.add(R.id.button32);
		buttonList.add(R.id.button33);
		buttonList.add(R.id.button34);
		buttonList.add(R.id.button35);

		buttonList.add(R.id.button41);
		buttonList.add(R.id.button42);
		buttonList.add(R.id.button43);
		buttonList.add(R.id.button44);
		buttonList.add(R.id.button45);

		buttonList.add(R.id.button51);
		buttonList.add(R.id.button52);
		buttonList.add(R.id.button53);
		buttonList.add(R.id.button54);
		buttonList.add(R.id.button55);

		Iterator itButton = buttonList.iterator();
		while (itButton.hasNext()) {
			Button buttonSetup = (Button) findViewById((Integer) itButton
					.next());
			buttonSetup.setOnClickListener(this);
		}

		Button resetButton = (Button) findViewById(R.id.reset);
		resetButton.setOnClickListener(this);

		resetMap();

	}
	
	@Override
	  public void onStart() {
	    super.onStart();
	    // The rest of your onStart() code.
	    EasyTracker.getInstance(this).activityStart(this);  // Add this method.
	  }

	  @Override
	  public void onStop() {
	    super.onStop();
	    // The rest of your onStop() code.
	    EasyTracker.getInstance(this).activityStop(this);  // Add this method.
	  }

	public void resetMap() {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map = initMap();
		paintButtons(map);
		storeMap = map;
	}

	private void paintButtons(Map<String, Boolean> map) {
		for (String temp : map.keySet()) {

			int buttonId = getButtonId(temp);
			Button tempButton = (Button) findViewById(buttonId);
			if (map.get(temp) == true) {
				int colorId = Color.WHITE;
				tempButton.setBackgroundColor(colorId);
			} else if (map.get(temp) == false) {
				int colorId = Color.BLACK;
				tempButton.setBackgroundColor(colorId);
			}
		}
	}

	private int getButtonId(String temp) {

		int returnValue = 0;
		if (temp.equals("r1c1")) {
			returnValue = R.id.button11;
		} else if (temp.equals("r1c2")) {
			returnValue = R.id.button12;
		} else if (temp.equals("r1c3")) {
			returnValue = R.id.button13;
		} else if (temp.equals("r1c4")) {
			returnValue = R.id.button14;
		} else if (temp.equals("r1c5")) {
			returnValue = R.id.button15;
		} else if (temp.equals("r2c1")) {
			returnValue = R.id.button21;
		} else if (temp.equals("r2c2")) {
			returnValue = R.id.button22;
		} else if (temp.equals("r2c3")) {
			returnValue = R.id.button23;
		} else if (temp.equals("r2c4")) {
			returnValue = R.id.button24;
		} else if (temp.equals("r2c5")) {
			returnValue = R.id.button25;
		} else if (temp.equals("r3c1")) {
			returnValue = R.id.button31;
		} else if (temp.equals("r3c2")) {
			returnValue = R.id.button32;
		} else if (temp.equals("r3c3")) {
			returnValue = R.id.button33;
		} else if (temp.equals("r3c4")) {
			returnValue = R.id.button34;
		} else if (temp.equals("r3c5")) {
			returnValue = R.id.button35;
		} else if (temp.equals("r4c1")) {
			returnValue = R.id.button41;
		} else if (temp.equals("r4c2")) {
			returnValue = R.id.button42;
		} else if (temp.equals("r4c3")) {
			returnValue = R.id.button43;
		} else if (temp.equals("r4c4")) {
			returnValue = R.id.button44;
		} else if (temp.equals("r4c5")) {
			returnValue = R.id.button45;
		} else if (temp.equals("r5c1")) {
			returnValue = R.id.button51;
		} else if (temp.equals("r5c2")) {
			returnValue = R.id.button52;
		} else if (temp.equals("r5c3")) {
			returnValue = R.id.button53;
		} else if (temp.equals("r5c4")) {
			returnValue = R.id.button54;
		} else if (temp.equals("r5c5")) {
			returnValue = R.id.button55;
		}

		return returnValue;

	}

	private static Map<String, Boolean> initMap() {

		Map<String, Boolean> initMap = new HashMap<String, Boolean>();

		for (int i = 1; i < (DimensionX + 1); i++) {
			for (int j = 1; j < (DimensionY + 1); j++) {
				initMap.put("r" + i + "c" + j, false);
				// System.out.println("initMap"+initMap);
			}
		}

		// difficulty = 3;

		initMap = randomMapGen(difficulty, initMap);

		return initMap;
	}

	private static Map<String, Boolean> randomMapGen(int difficulty,
			Map<String, Boolean> initMap) {
		List<String> moveList = new ArrayList<String>();
		for (int k = 0; k < difficulty; k++) {
			int x = getRandomFactor(1, DimensionX);
			int y = getRandomFactor(1, DimensionY);

			
			// System.out.println("x:" + x + "\ny:" + y);
			String inputStr = "r" + x + "c" + y;
			boolean foundUnique=false;
			while(!foundUnique){
				inputStr = "r" + x + "c" + y;
			if (!moveList.contains(inputStr)) {
				moveList.add(inputStr);
				foundUnique=true;
			}
			else{
				x = getRandomFactor(1, DimensionX);
				y = getRandomFactor(1, DimensionY);
			}
			}

			initMap = repaintMap(initMap, x, y);
			// System.out.println(initMap);
		}
		return initMap;
	}

	private static int getRandomFactor(int Min, int Max) {

		return (Min + (int) (Math.random() * ((Max - Min) + 1)));
	}

	private static Map<String, Boolean> repaintMap(Map<String, Boolean> map,
			int x, int y) {

		String inputStr = "r" + x + "c" + y;

		String north = "";
		String south = "";
		String east = "";
		String west = "";

		List<String> strList = new ArrayList<String>();
		strList.add(inputStr);
		// find if the neighbours exist
		if (y - 1 != 0) {
			west = "r" + x + "c" + (y - 1);
			strList.add(west);
		}
		if (y + 1 != (DimensionY + 1)) {
			east = "r" + x + "c" + (y + 1);
			strList.add(east);
		}
		if (x - 1 != 0) {
			north = "r" + (x - 1) + "c" + y;
			strList.add(north);
		}
		if (x + 1 != (DimensionX + 1)) {
			south = "r" + (x + 1) + "c" + y;
			strList.add(south);
		}

		Iterator<String> itr = strList.iterator();
		while (itr.hasNext()) {
			String input = itr.next();
			// System.out.println(map);
			boolean value;
			if (map.containsKey(input)) {
				value = map.get(input);
				map.put(input, !value);
			}

		}

		return map;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {

		int viewId = v.getId();
		TextView tView = (TextView) findViewById(R.id.score);
		if (viewId == R.id.reset) {
			score = 0;
			tView.setText("" + score);
			difficulty = getRandomFactor(3, 9);
			TextView optiView = (TextView) findViewById(R.id.Optimum);
			optiView.setText("" + difficulty);
			resetMap();
		} else {
			score = score + 1;

			tView.setText("" + score);

			int x = 0;
			int y = 0;

			switch (viewId) {
			case R.id.button11:
				x = 1;
				y = 1;
				break;

			case R.id.button12:
				x = 1;
				y = 2;
				break;

			case R.id.button13:
				x = 1;
				y = 3;
				break;

			case R.id.button14:
				x = 1;
				y = 4;
				break;

			case R.id.button15:
				x = 1;
				y = 5;
				break;

			case R.id.button21:
				x = 2;
				y = 1;
				break;

			case R.id.button22:
				x = 2;
				y = 2;
				break;

			case R.id.button23:
				x = 2;
				y = 3;
				break;

			case R.id.button24:
				x = 2;
				y = 4;
				break;

			case R.id.button25:
				x = 2;
				y = 5;
				break;

			case R.id.button31:
				x = 3;
				y = 1;
				break;

			case R.id.button32:
				x = 3;
				y = 2;
				break;

			case R.id.button33:
				x = 3;
				y = 3;
				break;

			case R.id.button34:
				x = 3;
				y = 4;
				break;

			case R.id.button35:
				x = 3;
				y = 5;
				break;

			case R.id.button41:
				x = 4;
				y = 1;
				break;

			case R.id.button42:
				x = 4;
				y = 2;
				break;

			case R.id.button43:
				x = 4;
				y = 3;
				break;

			case R.id.button44:
				x = 4;
				y = 4;
				break;

			case R.id.button45:
				x = 4;
				y = 5;
				break;

			case R.id.button51:
				x = 5;
				y = 1;
				break;

			case R.id.button52:
				x = 5;
				y = 2;
				break;

			case R.id.button53:
				x = 5;
				y = 3;
				break;

			case R.id.button54:
				x = 5;
				y = 4;
				break;

			case R.id.button55:
				x = 5;
				y = 5;
				break;
			}

			storeMap = repaintMap(storeMap, x, y);
			paintButtons(storeMap);

			checkVictory(storeMap);
		}

	}

	private void checkVictory(Map<String, Boolean> vicMap) {

		if (!vicMap.containsValue(true)) {
			Util.showAlert("You are Victorious! You took " + score + " moves.",
					context);
			score = 0;
			TextView tView = (TextView) findViewById(R.id.score);
			tView.setText("" + score);
			resetMap();
		}

	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		
		switch (item.getItemId())
        {
        case R.id.action_howto:
            
            Util.showHelp("Objective: To turn off all the Lights with the least number of moves \n" +
            		"\n" +
            		"Each switch is inter-connected with other lights in a perpendicular fashion",context);
            return true;
            
        default:
            return super.onOptionsItemSelected(item);
        }
		
		
	}

}
