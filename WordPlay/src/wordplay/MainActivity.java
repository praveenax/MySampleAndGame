package wordplay;

import prax.game.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	final Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Context context = this.getApplicationContext();

		TextView tx = (TextView) findViewById(R.id.textView1);
		Typeface custom_font = Typeface.createFromAsset(getAssets(),
				"font/Pacifico.ttf");
		tx.setTypeface(custom_font);

		Button playGame = (Button) findViewById(R.id.button1);
		Button instructions = (Button) findViewById(R.id.button2);
		Button exit = (Button) findViewById(R.id.button3);
		playGame.setOnClickListener(this);
		instructions.setOnClickListener(this);
		exit.setOnClickListener(this);

		playGame.setTypeface(custom_font);
		instructions.setTypeface(custom_font);
		exit.setTypeface(custom_font);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.button1:
			Intent intent = new Intent(this, PlayActivity.class);
			startActivity(intent);
			break;
		case R.id.button2:
			openAlert();
			break;

		case R.id.button3:

			Intent intent2 = new Intent(Intent.ACTION_MAIN);
			intent2.addCategory(Intent.CATEGORY_HOME);
			intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent2);
			break;
		}

	}

	private void openAlert() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

		// set title
		alertDialogBuilder.setTitle("How to Play!");

		// set dialog message
		alertDialogBuilder
				.setMessage(
						"Each Game consists of two four letter words, that you need to find. /n You will awarded 10 points for every correct word \n -5 for every reset pressed!")
				.setCancelable(false)
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// if this button is clicked, close
						// current activity
						dialog.cancel();
						// MainActivity.this.finish();
					}
				});
	

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();

	}

}
