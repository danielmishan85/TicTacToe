package com.example.matala1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class XPlayerScreen extends AppCompatActivity implements View.OnClickListener{
    private Button[][] buttons = new Button[3][3];
    private Boolean playerXTurn = true;
    private int roundCounter;
    private int playerXPoints;
    private int playerOPoints;
    private TextView txtPX;
    private TextView txtPO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xplayer_screen);

        txtPX = findViewById(R.id.txtP1);
        txtPO = findViewById(R.id.txtP2);
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
               String buttonID = "imgBtn" + i + j;
               int resID = getResources().getIdentifier(buttonID,"id", getPackageName());
               buttons[i][j] = findViewById(resID);
               buttons[i][j].setOnClickListener(this);
            }
        }
        Button btnReset = findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
            ((Button)view).setText("X");
        } else{
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
//        String[][] field = new String[3][3];
//        for (int i = 0; i < 3; i++){
//            for (int j = 0; j < 3; j++){
//               field[i][j] = buttons[i][j].getText().toString();
//            }
//        }
        for (int i = 0; i < 3; i++){
          if (buttons[i][0].equals(buttons[i][1])
                  && buttons[i][0].equals(buttons[i][2])
                  && !buttons[i][0].equals("")){
              return true;
          }
        }
        for (int i = 0; i < 3; i++){
            if (buttons[0][i].equals(buttons[1][i])
                    && buttons[0][i].equals(buttons[2][i])
                    && !buttons[0][i].equals("")){
                return true;
            }
        }
        if (buttons[0][0].equals(buttons[1][1])
                && buttons[0][0].equals(buttons[2][2])
                && !buttons[0][0].equals("")){
            return true;
        }
        if (buttons[0][2].equals(buttons[1][1])
                && buttons[0][2].equals(buttons[2][0])
                && !buttons[0][2].equals("")){
            return true;
        }
        return false;
    }

    private void playerXWin(){
        playerXPoints++;
        Toast.makeText(this,"Player X Wins!", Toast.LENGTH_SHORT);
        updatePointsText();
        resetBoard();
    }

    private void playerOWin(){
        playerOPoints++;
        Toast.makeText(this,"Player O Wins!", Toast.LENGTH_SHORT);
        updatePointsText();
        resetBoard();
    }

    private void draw(){
        Toast.makeText(this,"Player O Wins!", Toast.LENGTH_SHORT);
        resetBoard();
    }
    private void updatePointsText(){}
    private void resetBoard(){}
}