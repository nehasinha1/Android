package com.example.neha.pig;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private FrameLayout die1, die2;
    private Button roll, hold;

    private int round_score=0;
    private int player1=0;
    private int player2=0;

    private TextView p1,p2,round;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        round=(TextView)findViewById(R.id.round);
        p1=(TextView)findViewById(R.id.p1);
        p2=(TextView)findViewById(R.id.p2);

        Intent intent = getIntent();
        if(intent.hasExtra("player1")){
        player1=intent.getExtras().getInt("player1");
        }

        if(intent.hasExtra("player2")){
            player2=intent.getExtras().getInt("player2");
        }

        roll = (Button) findViewById(R.id.button);
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
                endTurn();
            }
        });

        hold = (Button)findViewById(R.id.hold);
        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passTurn();
                endTurn();
                /*The commented out code here was the code we used
                in class to send an integer to the next activty.
                It was replaced by an alert dialog to be used to indicate
                a winner (for demonstration purposes).
                Use the alert dialog code in your program where appropriate
                Intent intent = new Intent(MainActivity.this,Player2.class);
                intent.putExtra("score", 99);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("You Won!");
                alertDialog.setMessage("Yipeeaieahhh!");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();*/
            }
        });

        round.setText("Round :"+round_score);
        p1.setText("p1:"+player1);
        p2.setText("p2:"+player2);
        die1 = (FrameLayout) findViewById(R.id.die1);
        die2 = (FrameLayout) findViewById(R.id.die2);

    }

    //get two random ints between 1 and 6 inclusive
    public void rollDice() {
        int val1 = 1 + (int) (6 * Math.random());
        int val2 = 1 + (int) (6 * Math.random());
        setDie(val1, die1);
        setDie(val2, die2);
        checkTurn(val1,val2);
    }

    public void checkTurn(int val1,int val2){
        if(val1==1 || val2==1){
            player1=player1+round_score;
            round.setText("Round :"+round_score);
            p1.setText("p1:"+player1);
            p2.setText("p2:"+player2);
            Toast.makeText(this, "Player 2 Turn ", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this,Player2.class);
            intent.putExtra("player1", player1);
            intent.putExtra("player2", player2);
            startActivity(intent);
        }
        else{
            round_score=round_score+val1+val2;
            round.setText("Round : "+round_score);
        }
    }

    public void passTurn()
    {
        player1=player1+round_score;
        Toast.makeText(this, "Player 2 Turn ", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MainActivity.this,Player2.class);
        intent.putExtra("player1", player1);
        intent.putExtra("player2", player2);
        startActivity(intent);
    }

    public void endTurn(){
        if(player1>=100){
            player1=0;
            player2=0;
            round_score=0;
            Toast.makeText(this, "Player 1 Wins ", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }
    //set the appropriate picture for each die per int
    public void setDie(int value, FrameLayout layout) {
        Drawable pic = null;

        switch (value) {
            case 1:
                pic = getResources().getDrawable(R.drawable.die_face_1);
                break;
            case 2:
                pic = getResources().getDrawable(R.drawable.die_face_2);
                break;
            case 3:
                pic = getResources().getDrawable(R.drawable.die_face_3);
                break;
            case 4:
                pic = getResources().getDrawable(R.drawable.die_face_4);
                break;
            case 5:
                pic = getResources().getDrawable(R.drawable.die_face_5);
                break;
            case 6:
                pic = getResources().getDrawable(R.drawable.die_face_6);
                break;
        }
        layout.setBackground(pic);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
/*
* AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
alertDialog.setTitle("Alert");
alertDialog.setMessage("Alert message to be shown");
alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
    new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    });
alertDialog.show();*/