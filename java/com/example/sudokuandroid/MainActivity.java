package com.example.sudokuandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SudokuModel sudokuModel = new SudokuModel();
        sudokuModel.setValue(2,0,0);
        SudokuAdapter a = new SudokuAdapter(this,sudokuModel);
        GridView gridview = findViewById(R.id.gridview);
        gridview.setAdapter(a);
        a.printmatrix();


//        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//            }
//        });


        // Button addbtn = (Button) findViewById(R.id.name)
        // addbtn_setOnClickListener(new OnClickListener(){
        // @Override
        // public void onClick(Vew view) {
        //  EditText reference to be changed = (EditText) findbyview...
        //
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_clear:
                return true;

            case R.id.action_solve:
                //add code calling solve.
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
