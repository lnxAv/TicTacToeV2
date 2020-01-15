package com.example.tictactoev2;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Arrays;

import static com.example.tictactoev2.GamePopUpType.*;

public class GameActivity extends Activity {
    final int _DEFAULTBOARD = -10;
    final int _STARTINGPLAYER = 0;
    final String _DEFAULTP1NAME = "Player 1";
    final String _DEFAULTP2NAME = "Player 2";
    Intent menuActivity;
    private int[] playerScore = new int[]{0,0};
    private String p1Name = "Player 1";
    private String p2Name = "Player 2";

    private TextView p1Score;
    private TextView p2Score;
    private ImageButton case1;
    private ImageButton case2;
    private ImageButton case3;
    private ImageButton case4;
    private ImageButton case5;
    private ImageButton case6;
    private ImageButton case7;
    private ImageButton case8;
    private ImageButton case9;

    private int turn = 0;
    private int player = _STARTINGPLAYER;
    private int board[] = new int[9];
    private AlertDialog.Builder game_popup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_main);
        menuActivity = new Intent(this, MenuActivity.class);
        game_popup = new AlertDialog.Builder(GameActivity.this);
        p1Name = getIntent().getStringExtra("p1Name");
        p2Name = getIntent().getStringExtra("p2Name");

        if(p1Name == null)
            p1Name = _DEFAULTP1NAME;
        if(p2Name == null)
            p2Name = _DEFAULTP2NAME;

        findViewById(R.id.b_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartGame();
            }
        });

        findViewById(R.id.b_exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to menu
            }
        });

        p1Score = findViewById(R.id.p1_score);
        p2Score = findViewById(R.id.p2_score);

        case1 = findViewById(R.id.b_00);
        case1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnPlayerClick((ImageButton)v);
            }
        });

        case2 = findViewById(R.id.b_01);
        case2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnPlayerClick((ImageButton)v);
            }
        });

        case3 = findViewById(R.id.b_02);
        case3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnPlayerClick((ImageButton)v);
            }
        });

        case4 =findViewById(R.id.b_03);
        case4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnPlayerClick((ImageButton)v);
            }
        });

        case5 = findViewById(R.id.b_04);
        case5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnPlayerClick((ImageButton)v);
            }
        });

        case6 = findViewById(R.id.b_05);
        case6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnPlayerClick((ImageButton)v);
            }
        });

        case7 = findViewById(R.id.b_06);
        case7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnPlayerClick((ImageButton)v);
            }
        });

        case8 = findViewById(R.id.b_07);
        case8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnPlayerClick((ImageButton)v);
            }
        });

        case9 = findViewById(R.id.b_08);
        case9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnPlayerClick((ImageButton)v);
            }
        });

        UpdateScene();
    }

    @Override
    protected void onStart() {
        super.onStart();
        StartGame();
    }

    private void OnPlayerClick(ImageButton b_){
        AddSelection(b_);
        CheckSelection();
        NextPlayer();
        UpdateScene();
    }

    private void StartGame(){
        Arrays.fill(board, _DEFAULTBOARD);

        case1.setImageResource(0);
        case1.setClickable(true);

        case2.setImageResource(0);
        case2.setClickable(true);

        case3.setImageResource(0);
        case3.setClickable(true);

        case4.setImageResource(0);
        case4.setClickable(true);

        case5.setImageResource(0);
        case5.setClickable(true);

        case6.setImageResource(0);
        case6.setClickable(true);

        case7.setImageResource(0);
        case7.setClickable(true);

        case8.setImageResource(0);
        case8.setClickable(true);

        case9.setImageResource(0);
        case9.setClickable(true);
    }
    private void CheckSelection(){
        int check = -1;
            for (int a = 0; a < 8; a++) {
                switch (a) {
                    case 0:
                        check = board[0] + board[1] + board[2];
                        break;
                    case 1:
                        check = board[3] + board[4] + board[5];
                        break;
                    case 2:
                        check = board[6] + board[7] + board[8];
                        break;
                    case 3:
                        check = board[0] + board[3] + board[6];
                        break;
                    case 4:
                        check = board[1] + board[4] + board[7];
                        break;
                    case 5:
                        check = board[2] + board[5] + board[8];
                        break;
                    case 6:
                        check = board[0] + board[4] + board[8];
                        break;
                    case 7:
                        check = board[2] + board[4] + board[6];
                        break;
                }

                if(check == 3 || check == 0){
                    BoardWin(player);
                    return;
                }
            }

        BoardDraw();
    }

    private void BoardWin(int player) {

        if(player == 0){
            ++playerScore[player];
            ScenePopUp(WinO);
        }
        else{
            ++playerScore[player];
            ScenePopUp(WinX);
        }

    }

    private boolean BoardDraw() {
        for (int i = 0; i <= board.length; i++){
            if(i == board.length){
                ScenePopUp(Draw);
                return true;
            }

            if(board[i] == _DEFAULTBOARD){
                return false;
            }
        }
        return  true;
    }


    private void AddSelection(ImageButton b_){
        try {
            board[getBoardPosition(b_)] = player;
            b_.setClickable(false);
            if(player == 0){
                b_.setImageResource(R.drawable.case_o);
            }
            else{
                b_.setImageResource(R.drawable.case_x);
            }
        }
        catch (Exception e){
        }
    }

    private int getBoardPosition(ImageButton b_){
        int b_id = b_.getId();
        switch (b_id){
            case R.id.b_00 :
                return 0;
            case R.id.b_01:
                return 1;
            case R.id.b_02 :
                return 2;
            case R.id.b_03:
                return 3;
            case R.id.b_04 :
                return 4;
            case R.id.b_05:
                return 5;
            case R.id.b_06 :
                return 6;
            case R.id.b_07:
                return 7;
            case R.id.b_08:
                return 8;
        }
        throw new NullPointerException("Position not found");
    }

    private void NextPlayer(){
        Toast.makeText(getApplicationContext(),"Next Player", Toast.LENGTH_SHORT).show();
        player = (player == 0)?1: 0;
        turn++;
    }

    private void UpdateScene(){
        if(player == 0){
            p1Score.setText(p1Name+": "+playerScore[player]);
            p1Score.setTextColor(getResources().getColor(R.color.colorAccent));
            p2Score.setTextColor(getResources().getColor(R.color.textDefault));
        }
        else{
            p2Score.setText(p2Name+"X: "+playerScore[player]);
            p2Score.setTextColor(getResources().getColor(R.color.colorAccent));
            p1Score.setTextColor(getResources().getColor(R.color.textDefault));
        }
    }

    private void ScenePopUp(GamePopUpType popUpType){
        View selectedPopUp = null;

        switch(popUpType){
            case Draw:
                selectedPopUp = getLayoutInflater().inflate(R.layout.draw_popup, null);
                break;
            case WinO:
                selectedPopUp = getLayoutInflater().inflate(R.layout.win_popup, null);
                ((TextView)selectedPopUp.findViewById(R.id.text_winner)).setText(p1Name+" !");
                break;
            case WinX:
                selectedPopUp = getLayoutInflater().inflate(R.layout.win_popup, null);
                ((TextView)selectedPopUp.findViewById(R.id.text_winner)).setText(p2Name+" !");
                break;
            default:
                break;
        }
        game_popup.setView(selectedPopUp);
        final AlertDialog dialog  = game_popup.create();

        ((Button)selectedPopUp.findViewById(R.id.b_continue)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartGame();
                dialog.dismiss();
            }
        });

        ((Button)selectedPopUp.findViewById(R.id.b_exit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(menuActivity);
                dialog.dismiss();
            }
        });

        dialog.show();

    }

}

enum GamePopUpType{
    Draw,
    WinO,
    WinX
}