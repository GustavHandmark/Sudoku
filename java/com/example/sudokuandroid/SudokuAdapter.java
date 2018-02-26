package com.example.sudokuandroid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.text.InputType;
import android.text.Layout;
import android.text.Spanned;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.text.InputFilter;

import java.lang.reflect.Field;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by gusta on 2/22/2018.
 * chokladio helped
 */

public class SudokuAdapter extends BaseAdapter {
    private Context mContext;
    private SudokuModel sm;
    private EditText[][] sudokuMatrix = new EditText[9][9];
    private boolean discoStarted = false;
    private Timer timer;

    public SudokuAdapter(Context c, SudokuModel sm) {
        mContext = c;
        this.sm = sm;
        updateEditTextMatrix();

    }

    public EditText[][] getSudokuMatrix(){
        return sudokuMatrix;
    }

    public void updateEditTextMatrix() {
        for (int i = 0; i < sm.getMatrix().length; i++) {
            for (int j = 0; j < sm.getMatrix()[i].length; j++) {
                EditText box = new EditText(mContext);
                box.setInputType(InputType.TYPE_CLASS_NUMBER);
                box.setGravity(Gravity.CENTER);
                box.setFilters(new InputFilter[]{new InputFilterMinMax(1, 9)});
                box.setCursorVisible(false);

                String s = String.valueOf(sm.getMatrix()[i][j]);
                box.setText(String.valueOf(sm.getMatrix()[i][j]));
                if (s.equalsIgnoreCase("0")) {
                    box.setText("");
                } else {
                    box.setText(String.valueOf(sm.getMatrix()[i][j]));
                }

                int reg = ((i / 3) + (j / 3));
                if (reg == 0 || reg == 2 || reg == 4 || reg == 6 || reg == 8) {
                    box.setBackgroundResource(R.drawable.rectangle_dark);
                } else {
                    box.setBackgroundResource(R.drawable.rectangle);
                }
                sudokuMatrix[i][j] = box;
            }
        }
    }


    /**
     * Implements a "filter" that only allows values between 1 and 9. Called every time a EditText-box is updated
     */
    public class InputFilterMinMax implements InputFilter {
        private int min;
        private int max;

        public InputFilterMinMax(int min, int max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

            //noinspection EmptyCatchBlock
            try {
                int input = Integer.parseInt(dest.subSequence(0, dstart).toString() + source + dest.subSequence(dend, dest.length()));
                if (isInRange(min, max, input))
                    return null;
            } catch (NumberFormatException nfe) {
            }
            return "";
        }

        private boolean isInRange(int a, int b, int c) {
            return b > a ? c >= a && c <= b : c >= b && c <= a;
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


        int row;
        int col;

        if (position <= 80) {
            row = position / 9;
            col = position % 9;
            et = sudokuMatrix[row][col];
            et.setSelection(et.getText().length());
        }

        return et;
    }
}
