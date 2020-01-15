package com.example.tictactoev2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class MenuActivity extends Activity {

    AlertDialog.Builder dialogueBuilder;
    EditText p1Name;
    EditText p2Name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialogueBuilder = new AlertDialog.Builder(MenuActivity.this);
        findViewById(R.id.b_menuStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadyGame();
            }
        });
    }

    private void ReadyGame(){
        View selectedPopUp = null;
        selectedPopUp = getLayoutInflater().inflate(R.layout.name_popup, null);
        p1Name = (EditText)selectedPopUp.findViewById(R.id.edit_p1Name);
        p2Name = (EditText)selectedPopUp.findViewById(R.id.edit_p2Name);
        dialogueBuilder.setView(selectedPopUp);
        final AlertDialog dialog  = dialogueBuilder.create();

        ((Button)selectedPopUp.findViewById(R.id.b_continue)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckNames()){
                    StartGameActivity();
                    dialog.dismiss();
                }

            }
        });

        ((Button)selectedPopUp.findViewById(R.id.b_exit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    dialog.dismiss();
            }
        });

        dialog.show();

    }

    private boolean CheckNames(){
        if(p1Name.getText().length() <= 0 || p2Name.getText().length() <= 0){
            return false;
        }
        else if(p1Name.getText() == p2Name.getText()){
            return false;
        }

        return true;
    }

    private void StartGameActivity(){
        Intent gameIntent = new Intent(this, GameActivity.class);
        gameIntent.putExtra("p1Name", p1Name.getText());
        gameIntent.putExtra("p2Name", p2Name.getText());
        startActivity(gameIntent);
    }
}
