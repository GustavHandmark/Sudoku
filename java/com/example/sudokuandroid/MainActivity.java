package com.example.sudokuandroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.GridView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    private SudokuAdapter a;
    private SudokuModel sudokuModel;
    Handler hand = new Handler();
    private boolean discoStarted = false;
    GridView gridview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sudokuModel = new SudokuModel();
        a = new SudokuAdapter(this, sudokuModel);
        gridview = findViewById(R.id.gridview);
        gridview.setAdapter(a);
        gridview.requestFocus();

    }

    /**
     * helper-method for hiding the user keyboard.
     */
    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     *Initializes the menu buttons in the activitybar.
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    /**
     * Sets the actions to be executed when a button in the activitybar is pressed.
     * Returns true if the action was executed.
     * @param item
     * @return boolean
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_clear:
                a.clear();
                a.notifyDataSetChanged();
                gridview.setAdapter(a);
                return true;

            case R.id.action_solve:
                hideKeyboard();
                Boolean solved = a.solveSudoku();
                if (!solved) {
                    AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                    alertDialog.setTitle("Sudoku cannot be solved");
                    alertDialog.setMessage("The sudoku cannot be solved, no solution exists." + "\n" + "\n" + "Check the input values and try again");
                    alertDialog.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    alertDialog.show();
                }

                a.notifyDataSetChanged();
                gridview.setAdapter(a);
                return true;

            case R.id.action_disco:
                if (!discoStarted) {
                    hand.postDelayed(runnable,20);
                    discoStarted = true;

                } else {
                    hand.removeCallbacks(runnable);
                    hand.removeCallbacks(runnable2);
                    discoStarted = false;
                    a.updateSudoku();
                    a.updateEditTextMatrix();
                }
                a.notifyDataSetChanged();
                gridview.setAdapter(a);
                return discoStarted;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private Runnable runnable = new Runnable(){
        @Override
        /**
         * Changes the colors of whole regions randomly.
         */
        public void run(){
            Random rand = new Random();
            int row = rand.nextInt(9);
            int col = rand.nextInt(9);
            int regionRow = (row / 3) * 3;
            int regionCol = (col / 3) * 3;
            int color = Color.rgb(rand.nextInt(156)+100, rand.nextInt(156)+100, rand.nextInt(156)+100);
            for (int i = regionRow; i <= regionRow + 2; i++) {
                for (int j = regionCol; j <= regionCol + 2; j++) {
                    EditText etemp = a.getSudokuMatrix()[i][j];
                    etemp.setBackgroundColor(color);
                }
            }
            hand.postDelayed(this,30);
            hand.postDelayed(runnable2,50);
        }
    };

    private Runnable runnable2 = new Runnable(){
        @Override
        /**
         * Changes the colors of individual cells randomly.
         */
        public void run(){
            Random rand = new Random();
            int row = rand.nextInt(9);
            int col = rand.nextInt(9);
            for (int i = 0; i <= 9; i++) {
                    EditText etemp = a.getSudokuMatrix()[row][col];
                    etemp.setBackgroundColor(Color.argb(255, rand.nextInt(156)+100, rand.nextInt(156)+100, rand.nextInt(156))+100);
                }
            }
        };
    }

