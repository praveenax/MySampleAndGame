package com.prax.lights;

import android.app.AlertDialog;
import android.content.Context;

public class Util {

	public static void showAlert(String message,Context context) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

		// set title
		alertDialogBuilder.setTitle("Result");

		// set dialog message
		alertDialogBuilder.setMessage(message);

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();
	}
	
	public static void showHelp(String message,Context context) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

		// set title
		alertDialogBuilder.setTitle("How to Play!");

		// set dialog message
		alertDialogBuilder.setMessage(message);

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();
	}
}
