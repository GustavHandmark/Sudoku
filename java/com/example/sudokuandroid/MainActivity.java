package com.example.sudokuandroid;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {
    private SudokuAdapter a;
    GridView gridview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SudokuModel sudokuModel = new SudokuModel();
        a = new SudokuAdapter(this, sudokuModel);
        gridview = findViewById(R.id.gridview);
        gridview.setVerticalScrollBarEnabled(false);
        gridview.setAdapter(a);
    }

    public void hideKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null){
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
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
                if(solved){
                    System.out.println("Fungerar att lösa matrisen");
                }
                else{
                    System.out.println("Gick ej att lösa");
                }
                a.notifyDataSetChanged();
                gridview.setAdapter(a);
                return solved;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
