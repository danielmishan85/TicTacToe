package com.example.matala1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_board);

        txtTurn = findViewById(R.id.txtTurn);
        txtPX = findViewById(R.id.txtP1);
        txtPO = findViewById(R.id.txtP2);
        img = findViewById(R.id.winLine);
        img.setBackgroundResource(R.drawable.empty);
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
               String buttonID = "btn" + i + j;
               int resID = getResources().getIdentifier(buttonID,"id", getPackageName());
               buttons[i][j] = findViewById(resID);
               buttons[i][j].setOnClickListener(this);
               buttons[i][j].setTag("0");
            }
        }
        Button btnReset = findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetBoard();
                img.setBackgroundResource(R.drawable.empty);
                txtTurn.setText("X turn!!!");
            }
        });
    }

    public void homeBtnOnClick(View view){
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View view) {
        if (playerXTurn){
            txtTurn.setText("O turn!!!");
            ((Button)view).setBackgroundResource(R.drawable.x);
            ((Button)view).setTag(1);
        } else{
            txtTurn.setText("X turn!!!");
            ((Button)view).setBackgroundResource(R.drawable.o);
            ((Button)view).setTag(-1);

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

    private boolean checkForWin() {
        int[][] field = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = Integer.parseInt(buttons[i][j].getTag().toString());
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[i][0] == field[i][1]
                    && field[i][0] == field[i][2]
                    && field[i][0] != 0) {
                if (i == 0)
                    img.setBackgroundResource(R.drawable.mark6);
                if(i == 1)
                    img.setBackgroundResource(R.drawable.mark7);
                if(i == 2)
                    img.setBackgroundResource(R.drawable.mark8);
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[0][i] == field[1][i]
                    && field[0][i] == field[2][i]
                    && field[0][i] != 0) {
                if (i == 0)
                    img.setBackgroundResource(R.drawable.mark3);
                if(i == 1)
                    img.setBackgroundResource(R.drawable.mark4);
                if(i == 2)
                    img.setBackgroundResource(R.drawable.mark5);
                return true;
            }
        }
        if (field[0][0] == field[1][1]
                && field[0][0] == field[2][2]
                && field[0][0] != 0) {
            img.setBackgroundResource(R.drawable.mark1);
            return true;
        }
        if (field[0][2] == field[1][1]
                && field[0][2] == field[2][0]
                && field[0][2] != 0) {
            img.setBackgroundResource(R.drawable.mark2);
            return true;
        }
        return false;
    }

    private void playerXWin(){
        playerXPoints++;
        updatePointsText();
        txtTurn.setText("Player X Wins!");
    }

    private void playerOWin(){
        playerOPoints++;
        updatePointsText();
        txtTurn.setText("Player O Wins!");
    }

    private void draw(){
        txtTurn.setText("Draw!");
    }

    private void updatePointsText(){
        txtPX.setText("Player X : " + playerXPoints);
        txtPO.setText("Player O: " + playerOPoints);
    }

    private void resetBoard(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
               buttons[i][j].setTag("0");
                buttons[i][j].setBackgroundResource(0);
            }
        }
        roundCounter = 0;
        playerXTurn = true;
    }
}