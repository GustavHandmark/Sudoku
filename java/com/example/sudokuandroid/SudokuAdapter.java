package com.example.sudokuandroid;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;


/**
 * Created by gusta on 2/22/2018.
 */

public class SudokuAdapter extends BaseAdapter {
    private Context mContext;
    private SudokuModel sm;
    private EditText[][] sudokuMatrix = new EditText[9][9];

    public SudokuAdapter(Context c, SudokuModel sm) {
        mContext = c;
        this.sm = sm;
        updateEditTextMatrix();

    }

    public void updateEditTextMatrix() {
        for (int i = 0; i < sm.getMatrix().length; i++) {
            for (int j = 0; j < sm.getMatrix()[i].length; j++) {
                EditText box = new EditText(mContext);
                box.setText(String.valueOf(sm.getMatrix()[i][j]));
                sudokuMatrix[i][j] = box;
            }
        }

    }

    /**
     * Updates the sudokuMatrix, returns true if successful,
     * returns false if any of the numbers inserted is not an integer between 1 to 9
     *
     * @return
     */
    public void printmatrix() {
        for (int i = 0; i < sudokuMatrix.length; i++) {
            for (int j = 0; j < sudokuMatrix[i].length; j++) {
                System.out.println(sudokuMatrix[i][j].getText().toString());
            }
        }
    }
    public boolean updateSudoku() {
        try {
            for (int i = 0; i < sudokuMatrix.length; i++) {
                for (int j = 0; j < sudokuMatrix[i].length; i++) {
                    String s = sudokuMatrix[i][j].getText().toString();
                    try {
                        sm.setValue(Integer.parseInt(s), i, j);
                    } catch (IllegalArgumentException t) {
                        return false;
                    }

                }
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void solveSudoku() {
        updateSudoku();
        sm.solveSudoku();
        updateEditTextMatrix();
    }

    public void clear() {
        for (int i = 0; i < sudokuMatrix.length; i++) {
            for (int j = 0; j < sudokuMatrix[i].length; j++) {
                sudokuMatrix[i][j].setText("");
            }
        }
        sm.clearMatrix();
    }

    public int getCount() {
        int c = 0;
        for (int i = 0; i < sudokuMatrix.length; i++) {
            for (int j = 0; j < sudokuMatrix[i].length; j++) {
                c++;
            }

        }
        return c;
    }

    public Object getItem(int row) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        EditText et;
        if(convertView == null){
            et = new EditText(mContext);
            et.setText(sudokuMatrix[0][0].getText());



        } else {
            et = (EditText) convertView;
        };
        return et;


    }


}
