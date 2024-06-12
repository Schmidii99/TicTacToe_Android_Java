package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Boolean xCurrent = true;
    private String[][] gameState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.gameState = new String[3][3];
    }

    public void reset(View v) {
        this.xCurrent = true;
        ((TextView)findViewById(R.id.currentPlayer)).setText("Current Player: " + (xCurrent ? "X" : "O"));
        ((Button)findViewById(R.id.btn_1)).setText("");
        ((Button)findViewById(R.id.btn_2)).setText("");
        ((Button)findViewById(R.id.btn_3)).setText("");
        ((Button)findViewById(R.id.btn_4)).setText("");
        ((Button)findViewById(R.id.btn_5)).setText("");
        ((Button)findViewById(R.id.btn_6)).setText("");
        ((Button)findViewById(R.id.btn_7)).setText("");
        ((Button)findViewById(R.id.btn_8)).setText("");
        ((Button)findViewById(R.id.btn_9)).setText("");
        this.gameState = new String[3][3];
    }

    public void buttonClick(View v) {
        Button b = (Button) v;
        if (b.getText() != "") {
            return;
        }
        b.setText(xCurrent ? "X" : "O");

        String buttonId = getResources().getResourceEntryName(v.getId());
        Integer num = Integer.valueOf(buttonId.split("_")[1]);
        num = num - 1;
        this.gameState[(Integer)(num / 3)][num % 3] = xCurrent ? "X" : "O";
        this.xCurrent = !this.xCurrent;
        ((TextView)findViewById(R.id.currentPlayer)).setText("Current Player: " + (xCurrent ? "X" : "O"));

        String winner = checkForWin();
        if (winner == null) {
            return;
        }
        if (!Objects.equals(winner, "")) {
            Toast.makeText(this, "Player " + winner + " won!", Toast.LENGTH_LONG).show();
            this.reset(v);
        }
    }

    public String checkForWin() {
        for (int i = 0; i < 3; i++) {
            // Columns
            if (Objects.equals(this.gameState[0][i], this.gameState[1][i]) && Objects.equals(this.gameState[1][i], this.gameState[2][i]) && this.gameState[1][i] != null) {
                return this.gameState[0][i];
            }
            // Rows
            if (Objects.equals(this.gameState[i][0], this.gameState[i][1]) && Objects.equals(this.gameState[i][1], this.gameState[i][2]) && this.gameState[i][1] != null) {
                return this.gameState[i][0];
            }
        }
        // Diagonals
        if (Objects.equals(this.gameState[0][0], this.gameState[1][1]) && Objects.equals(this.gameState[1][1], this.gameState[2][2]) && this.gameState[1][1] != null) {
            return this.gameState[1][1];
        }
        if (Objects.equals(this.gameState[2][0], this.gameState[1][1]) && Objects.equals(this.gameState[1][1], this.gameState[0][2]) && this.gameState[1][1] != null) {
            return this.gameState[1][1];
        }

        return "";
    }
}