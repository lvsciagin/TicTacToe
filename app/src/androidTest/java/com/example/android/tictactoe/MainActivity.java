package com.example.android.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean player1Turn = true;

    private Button[][] buttons = new Button[3][3];

    private int rounds;

    private TextView textViewPlayer2;
    private TextView textViewPlayer1;

    private int player2Points;
    private int player1Points;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPlayer2 = findViewById(R.id.player_2);
        textViewPlayer1 = findViewById(R.id.player_1);


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resourceID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button buttonReset = findViewById(R.id.reset_btn);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (player1Turn) {
            ((Button) v).setText("X");

        } else {
            ((Button) v).setText("O");
        }

        rounds++;

        if (checkWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (rounds == 9) {
            draw();
        } else {
            player1Turn = !player1Turn; //switch turns
        }

    }

    private boolean checkWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private void player2Wins() {
        player2Points++;
        Toast.makeText(this, "Winner: Player 2!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void player1Wins() {
        player1Points++;
        Toast.makeText(this, "Winner: Player 1!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void updatePointsText() {
        textViewPlayer2.setText("Player 2: " + player2Points);
        textViewPlayer1.setText("Player 1: " + player1Points);

    }


    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }


    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(""); //reset all buttons to initial empty state
            }
        }

// restart the game
        rounds = 0;
        player1Turn = true;
    }
}