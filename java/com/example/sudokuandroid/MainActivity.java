package com.example.sudokuandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
        gridview.setAdapter(a);
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
                a.solveSudoku();
                a.notifyDataSetChanged();
                gridview.setAdapter(a);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
