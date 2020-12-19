package com.example.rockpaperscissor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button play ;
    Button rock ;
    Button paper ;
    Button scissor ;
    ImageView player ;
    ImageView computer ;
    TextView playerscore ;
    TextView computerscore ;
    TextView timer ;
    int playertag,player_score=0,computer_score=0,computertag;

    public void computerturn(){
        Random com = new Random();
        computertag = com.nextInt(3);
        if(computertag==0)
        {
            computer.setImageResource(R.drawable.rock);
        }
        else if(computertag==1)
        {
            computer.setImageResource(R.drawable.paper);
        }
        else if(computertag==2)
        {
            computer.setImageResource(R.drawable.scissor);
        }
    }


public void playerChoice(String s){
    if (s=="ROCK"){
        player.setImageResource(R.drawable.rock);
        playertag=0;
        computerturn();

    }
    else if (s=="PAPER"){
        player.setImageResource(R.drawable.paper);
        playertag=1;
        computerturn();

    }
   else if (s=="SCISSOR"){
        player.setImageResource(R.drawable.scissor);
        playertag=2;
        computerturn();

    }
}
    public void score(){

        if(computertag==playertag)
        {
            player_score=player_score;
            computer_score=computer_score;
        }
        switch(computertag){
            case 0:
                if(playertag==1)
                    player_score+=1;
                else if(playertag==2)
                    computer_score+=1;
                break;
            case 1:
                if(playertag==0)
                    computer_score+=1;
                else if(playertag==2)
                    player_score+=1;
                break;
            case 2:
                if(playertag==0)
                    player_score+=1;
                else if(playertag==1)
                    computer_score+=1;
                break;
        }

        playerscore.setText(Integer.toString(player_score));
        computerscore.setText(Integer.toString(computer_score));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = findViewById(R.id.play);
        rock = findViewById(R.id.rock);
        paper = findViewById(R.id.paper);
        scissor = findViewById(R.id.scissor);
        player = (ImageView) findViewById(R.id.player);
        computer = (ImageView) findViewById(R.id.computer);
        playerscore = (TextView) findViewById(R.id.playerscore);
        computerscore = (TextView) findViewById(R.id.computerscore);
        timer = findViewById(R.id.timer);
        playerscore.setText("");
        computerscore.setText("");
        timer.setVisibility(View.INVISIBLE);
        rock.setVisibility(View.INVISIBLE);
        paper.setVisibility(View.INVISIBLE);
        scissor.setVisibility(View.INVISIBLE);
        playerscore.setVisibility(View.INVISIBLE);
        computerscore.setVisibility(View.INVISIBLE);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerscore.setText("");
                computerscore.setText("");
                play.setVisibility(View.INVISIBLE);
                timer.setVisibility(View.VISIBLE);
                rock.setVisibility(View.VISIBLE);
                paper.setVisibility(View.VISIBLE);
                scissor.setVisibility(View.VISIBLE);
                playerscore.setVisibility(View.VISIBLE);
                computerscore.setVisibility(View.VISIBLE);

                new CountDownTimer(60001, 1000) {
                    @Override
                    public void onTick(long l) {
                        long r=l/1000;
                        if(r==60)
                        {
                            timer.setText("01:"+Long.toString(r));
                        }
                        else if(r<10)
                        {
                            timer.setText("00:0"+Long.toString(r));
                        }
                        else {
                            timer.setText("00:" + Long.toString(r));
                        }
                        rock.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                playerChoice("ROCK");
                                score();
                            }
                        });
                        paper.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                playerChoice("PAPER");
                                score();
                            }
                        });
                        scissor.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                playerChoice("SCISSOR");
                                score();
                            }
                        });

                    }

                    @Override
                    public void onFinish() {
                        play.setVisibility(View.VISIBLE);
                        play.setText("PLAY AGAIN !");
                        rock.setVisibility(View.INVISIBLE);
                        paper.setVisibility(View.INVISIBLE);
                        scissor.setVisibility(View.INVISIBLE);
                   if(player_score>computer_score)
                     {
                         Toast.makeText(getApplicationContext(),"PLAYER WINS", Toast.LENGTH_LONG).show();
                     }
                   else if(player_score<computer_score)
                        {
                            Toast.makeText(getApplicationContext(),"COMPUTER WINS", Toast.LENGTH_LONG).show();
                        }
                   else
                       Toast.makeText(getApplicationContext(),"IT'S A TIE", Toast.LENGTH_LONG).show();
                    }

                }.start();
                player_score=0;
                computer_score=0;





            }
        });














    }
}