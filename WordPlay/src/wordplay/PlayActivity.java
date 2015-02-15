package wordplay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import prax.game.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.google.analytics.tracking.android.EasyTracker;

public class PlayActivity extends Activity implements OnClickListener {

	int Difficulty = 2;
	int remainingWords = 0;
	String allletters;
	TextView tView;
	List<Integer> buttonIdList;
	List<Integer> buttonsClicked;
	private PlayActivity context;
	List<String> inStr;
	Typeface custom_font;
	int score = 50;
	TextView scoreView;
	List<String> correctWords;
	List<String> startLetters;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_play);

		context = this;
		loadWords();

		scoreView = (TextView) findViewById(R.id.textView2);
		custom_font = Typeface
				.createFromAsset(getAssets(), "font/Pacifico.ttf");
		scoreView.setTypeface(custom_font);
		scoreView.setText("" + score);

		Button nextWord = (Button) findViewById(R.id.button1);
		nextWord.setOnClickListener(this);
		nextWord.setBackgroundColor(Color.WHITE);
		nextWord.setTextColor(Color.BLACK);

		Button resetButton = (Button) findViewById(R.id.button2);
		resetButton.setOnClickListener(this);
		resetButton.setBackgroundColor(Color.WHITE);
		resetButton.setTextColor(Color.BLACK);

		buttonsClicked = new ArrayList<Integer>();
		buttonIdList = new ArrayList<Integer>();
		addAllButtons();

		tView = (TextView) findViewById(R.id.textView3);

		injectGameLogic();

	}

	private void loadWords() {
		// TODO Auto-generated method stub
		inStr = new ArrayList<String>();
		try {

			InputStreamReader ins = new InputStreamReader(context.getAssets()
					.open("words4.txt"));
			BufferedReader br = new BufferedReader(ins);

			while (br.readLine() != null) {
				String tmp = br.readLine();
				if (tmp.length() == 4) {
					inStr.add(tmp.toUpperCase());
				}

			}

		} catch (Exception e) {

		}

	}

	private void injectGameLogic() {
		startLetters = new ArrayList<String>();
		remainingWords = Difficulty;
		String allLetters = getWords();

		StringBuilder output = initWordMap(allLetters);

		// System.out.println(allLetters);

		int index = 0;
		// Toast.makeText(this, output, Toast.LENGTH_LONG).show();
		Collections.shuffle(buttonIdList);

		index = mapToButtons(index);
	}

	private int mapToButtons(int index) {
		for (Integer v : buttonIdList) {
			Button b = (Button) findViewById(v);
			b.setBackgroundColor(Color.WHITE);
			b.setText("");
			// b.setTypeface(custom_font);
			b.setOnClickListener(this);
			if (allletters.length() > index) {

				char c = allletters.charAt(index);

				if (startLetters.contains("" + c)) {
					b.setText(("*" + c));
					startLetters.remove("" + c);
				} else {
					b.setText("" + c);
				}

				index++;
			} else {
				b.setBackgroundColor(Color.BLACK);

			}
		}
		return index;
	}

	private StringBuilder initWordMap(String allLetters) {
		allletters = allLetters;

		StringBuilder output = new StringBuilder(allLetters.length());

		List<Character> characters = new ArrayList<Character>();
		for (char c : allLetters.toCharArray()) {
			characters.add(c);
		}

		while (characters.size() != 0) {
			int randPicker = (int) (Math.random() * characters.size());
			output.append(characters.remove(randPicker));
		}
		return output;
	}

	private void addAllButtons() {
		buttonIdList.add(R.id.button11);
		buttonIdList.add(R.id.button12);
		buttonIdList.add(R.id.button13);
		buttonIdList.add(R.id.button14);

		buttonIdList.add(R.id.button21);
		buttonIdList.add(R.id.button22);
		buttonIdList.add(R.id.button23);
		buttonIdList.add(R.id.button24);

		buttonIdList.add(R.id.button31);
		buttonIdList.add(R.id.button32);
		buttonIdList.add(R.id.button33);
		buttonIdList.add(R.id.button34);

		buttonIdList.add(R.id.button41);
		buttonIdList.add(R.id.button42);
		buttonIdList.add(R.id.button43);
		buttonIdList.add(R.id.button44);
	}

	private String getWords() {
		Object words[] = { "word", "play", "blue" };

		// words = popWords();
		words = inStr.toArray();

		List<Object> wdList = Arrays.asList(words);

		Collections.shuffle(wdList);

		// System.out.println(wdList);

		String allLetters = "";
		/*
		 * for (int i = 0; i < words.length; i++) {
		 * 
		 * allLetters = allLetters + words[i]; }
		 */

		List subList = wdList.subList(0, Difficulty);
		// System.out.println(subList);
		correctWords = subList;
		// startLetters
		for (String letter : correctWords) {
			if (letter != null) {
				startLetters.add("" + letter.charAt(0));
			}

		}

		for (Object w : subList) {
			allLetters = allLetters + w.toString();
		}
		return allLetters;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.play, menu);
		return true;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.button11:
		case R.id.button12:
		case R.id.button13:
		case R.id.button14:

		case R.id.button21:
		case R.id.button22:
		case R.id.button23:
		case R.id.button24:

		case R.id.button31:
		case R.id.button32:
		case R.id.button33:
		case R.id.button34:

		case R.id.button41:
		case R.id.button42:
		case R.id.button43:
		case R.id.button44:
			onGameTablePress(v);

			break;

		case R.id.button2:
			onReset();

			break;

		case R.id.button1:
			onReset();
			injectGameLogic();
			break;

		}

	}

	private void onReset() {

		if (buttonsClicked.size() > 0) {

			clearBoard(false);

			tView.setText("");

			for (Integer val : buttonsClicked) {
				Button tmpBtn = (Button) findViewById(val);
				tmpBtn.setBackgroundColor(Color.WHITE);
			}

			buttonsClicked = new ArrayList<Integer>();

			score = score - 5;

			if (score <= 0) {
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						this);

				// set title
				alertDialogBuilder.setTitle("Game Over!");

				// set dialog message
				alertDialogBuilder

				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// if this button is clicked, close
						// current activity
						dialog.cancel();
						PlayActivity.this.finish();

					}
				});

				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				alertDialog.show();
			} else {
				scoreView.setText("" + score);
			}
		}

	}

	private void clearBoard(boolean b) {
		for (Integer val : buttonIdList) {
			Button tmpBtn = (Button) findViewById(val);
			if (b) {
				tmpBtn.setBackgroundColor(Color.BLACK);
			}

			tmpBtn.setEnabled(true);

		}
		// Toast.makeText(this, "ENABLED", Toast.LENGTH_LONG).show();
	}

	private void onWordFound() {
		// tView.setText("");
		remainingWords--;
		for (Integer val : buttonsClicked) {
			Button tmpBtn = (Button) findViewById(val);

			tmpBtn.setText("");

			tmpBtn.setBackgroundColor(Color.BLACK);

		}

		if (remainingWords == 0) {
			clearBoard(true);
			// onReset();
			injectGameLogic();
		}

		buttonsClicked = new ArrayList<Integer>();

	}

	private void onGameTablePress(View v) {
		Button b = (Button) findViewById(v.getId());
		if (b.getText() != "") {

			b.setBackgroundColor(Color.GRAY);

			b.setEnabled(false);

			// Toast.makeText(this, correctWords.toString(), Toast.LENGTH_SHORT)
			// .show();
			// Toast.makeText(this, b.getText(), Toast.LENGTH_SHORT).show();
			String temp = (String) tView.getText() + b.getText();
			// temp = temp;
			temp = temp.replace("*", "");

			tView.setText(temp);

			// for reset adding a list of clicked buttons

			buttonsClicked.add(v.getId());

			if (correctWords.contains(temp)) {
				score = score + 10;
				scoreView.setText("" + score);
				tView.setText("");
				onWordFound();

			}

		}

	}

	private static int getRandomFactor(int Min, int Max) {

		return (Min + (int) (Math.random() * ((Max - Min) + 1)));
	}

	@Override
	public void onStart() {
		super.onStart();
		// The rest of your onStart() code.
		EasyTracker.getInstance(this).activityStart(this); // Add this method.
	}

	@Override
	public void onStop() {
		super.onStop();
		// The rest of your onStop() code.
		EasyTracker.getInstance(this).activityStop(this); // Add this method.
	}

}
