package com.example.matala1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameBoard extends AppCompatActivity implements View.OnClickListener{
    private Button[][] buttons = new Button[3][3];
    private Boolean playerXTurn = true;
    private int roundCounter;
    private int playerXPoints;
    private int playerOPoints;
    private TextView txtTurn;
    private TextView txtPX;
    private TextView txtPO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_board);

        txtTurn = findViewById(R.id.txtTurn);
        txtPX = findViewById(R.id.txtP1);
        txtPO = findViewById(R.id.txtP2);
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
               String buttonID = "btn" + i + j;
               int resID = getResources().getIdentifier(buttonID,"id", getPackageName());
               buttons[i][j] = findViewById(resID);
               buttons[i][j].setOnClickListener(this);
            }
        }
        Button btnReset = findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetBoard();
                txtTurn.setText("X turn!!!");
                txtPX.setText("Player X: 0");
                txtPO.setText("Player O: 0");
            }
        });
    }

    public void homeBtnOnClick(View view){
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View view) {
        if (!((Button)view).getText().toString().equals(""))
            return;
        if (playerXTurn){
            txtTurn.setText("O turn!!!");
            ((Button)view).setText("X");
        } else{
            txtTurn.setText("X turn!!!");
            ((Button)view).setText("O");
        }
        roundCounter++;
        if (checkForWin()){
            if (playerXTurn){
                playerXWin();
            } else {
                playerOWin();
            }
        } else if (roundCounter == 9){
            draw();
        } else {
            playerXTurn = !playerXTurn;
        }
    }

    private boolean checkForWin(){
        String[][] field = new String[3][3];
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
               field[i][j] = buttons[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 3; i++){
          if (field[i][0].equals(field[i][1])
                  && field[i][0].equals(field[i][2])
                  && !field[i][0].equals("")){
              return true;
          }
        }
        for (int i = 0; i < 3; i++){
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")){
                return true;
            }
        }
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")){
            return true;
        }
        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")){
            return true;
        }
        return false;
    }

    private void playerXWin(){
        playerXPoints++;
        updatePointsText();
        txtTurn.setText("Player X Wins!");
        resetBoard();
    }

    private void playerOWin(){
        playerOPoints++;
        updatePointsText();
        txtTurn.setText("Player O Wins!");
        resetBoard();
    }

    private void draw(){
        txtTurn.setText("Draw!");
        resetBoard();
    }

    private void updatePointsText(){
        txtPX.setText("Player X : " + playerXPoints);
        txtPO.setText("Player O: " + playerOPoints);
    }

    private void resetBoard(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
               buttons[i][j].setText("");
            }
        }
        roundCounter = 0;
        playerXTurn = true;
    }
}