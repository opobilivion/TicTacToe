package com.example.op.gameconnect;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int activePlayer=0;
    int [] getState={ 2,2,2,2,2,2,2,2,2};
    int counter=0;

    //2-empty
    int [][] WinningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive=true;
    public void dropin(View view)
    {                 //0-black 1-red
        //Toast.makeText(this,"tapped",Toast.LENGTH_LONG).show();
        ImageView i=(ImageView) view;
        int tapCounter=Integer.parseInt((i.getTag().toString()));
        if(getState[tapCounter]==2 && gameActive ) { counter++;
            getState[tapCounter] = activePlayer;
            i.setTranslationY(-1500);
            if (activePlayer == 0) {
                i.setImageResource(R.drawable.download);
                activePlayer = 1;
            } else {
                i.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            i.animate().translationYBy(1500).setDuration(1000);
            for (int[] WinningPositions : WinningPositions) {
                if (getState[WinningPositions[0]] == getState[WinningPositions[1]] && getState[WinningPositions[1]] == getState[WinningPositions[2]] && getState[WinningPositions[0]] != 2) {
                    String winner = "";
                    if (activePlayer == 1)
                        winner = "Black";
                    else
                        winner = "Red";
                    Toast.makeText(this, winner + "won", Toast.LENGTH_LONG).show();
                    gameActive=false;
                    Button again=(Button)findViewById(R.id.button2);
                    TextView t=(TextView)findViewById(R.id.textView);

                    t.setText(winner+" has won");
                    again.setVisibility(View.VISIBLE);
                    t.setVisibility(View.VISIBLE);
                }
            }
        }
        if(counter==9) {
            Button again=(Button)findViewById(R.id.button2);
            TextView t=(TextView)findViewById(R.id.textView);

            t.setText("Match Draw");
            again.setVisibility(View.VISIBLE);
            t.setVisibility(View.VISIBLE);
            again.setVisibility(View.VISIBLE);
            t.setVisibility(View.VISIBLE);
        }
        }

    public void playAgain(View view)
    {

        Button again=(Button)findViewById(R.id.button2);
        TextView t=(TextView)findViewById(R.id.textView);
        again.setVisibility(View.INVISIBLE);
        t.setVisibility(View.INVISIBLE);
        GridLayout gd=(GridLayout)findViewById(R.id.gridLayout);
        for(int i=0;i<gd.getChildCount();i++)
        {
            ImageView img=(ImageView)gd.getChildAt(i);
            img.setImageDrawable(null);
        }
        activePlayer=0;
        for(int x=0;x<getState.length;x++)
            getState[x]=2;
         gameActive=true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,"Started",Toast.LENGTH_LONG).show();
    }
}
