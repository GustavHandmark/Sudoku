package com.example.sudokuandroid;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.text.InputType;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
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
                box.setBackgroundResource(R.drawable.rectangle);
                box.setInputType(InputType.TYPE_CLASS_NUMBER);
                box.setGravity(Gravity.CENTER);
                String s = String.valueOf(sm.getMatrix()[i][j]);
                box.setText(String.valueOf(sm.getMatrix()[i][j]));
                if (s.equalsIgnoreCase("0")) {
                    box.setText("");
                } else {
                    box.setText(String.valueOf(sm.getMatrix()[i][j]));
                }
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


    public SudokuModel getSudoku() {
        return sm;
    }

    public boolean updateSudoku() {
        try {
            for (int i = 0; i < sudokuMatrix.length; i++) {
                for (int j = 0; j < sudokuMatrix[i].length; j++) {
                    String s = sudokuMatrix[i][j].getText().toString();
                    try {
                        sm.setValue(Integer.parseInt(s), i, j);
                    } catch (IllegalArgumentException t) {
                        sm.clearValue(i, j);
                    }

                }
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean solveSudoku() {
        updateSudoku();
        Boolean check = sm.solveSudoku();
        updateEditTextMatrix();
        return check;
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

    public Object getItem(int position) {
        int row = position / 9;
        int col = position % 9;
        if (position <= 80) {
            return sudokuMatrix[row][col];
        }
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        EditText et;
        if (convertView == null) {
            et = new EditText(mContext);

        } else {
            et = (EditText) convertView;
        }
        ;

        int row;
        int col;

        if (position <= 80) {
            row = position / 9;
            col = position % 9;
            et = sudokuMatrix[row][col];
            if (position < 3) {
                et.setBackgroundResource(R.drawable.rectangle_dark);
            }

            if (position > 8 && position < 12) {
                et.setBackgroundResource(R.drawable.rectangle_dark);
            }
            if (position > 17 && position < 21) {
                et.setBackgroundResource(R.drawable.rectangle_dark);
            }

        }

        return et;
    }
}
