package carlos.coinapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Logger;

public class CoinFlipper extends AppCompatActivity implements Button.OnClickListener {

    private static final Logger logger = Logger.getLogger(CoinFlipper.class.getName());

    private Button btnFlip;
    private Button btnFlipAgain;
    private EditText etHead;
    private EditText etTail;
    private TextView tvResult;
    private ImageView ivCoin;
    private AnimationDrawable coinflipAnimation;
    private LinearLayout formLayout;
    private LinearLayout resultLayout;
    private static final Random random = new Random();
    private int res;
    private String result;
    private SQLiteDatabase db;
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    private Animation fadeout;
    private Animation fadein;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_flipper);
        /*this.getActionBar().setDisplayHomeAsUpEnabled(true);*/

        // Get view elements
        btnFlip = (Button) findViewById(R.id.btnFlip);
        btnFlipAgain = (Button) findViewById(R.id.btnFlipAgain);
        etHead = (EditText) findViewById(R.id.etHead);
        etTail = (EditText) findViewById(R.id.etTail);
        tvResult = (TextView) findViewById(R.id.tvResult);
        ivCoin = (ImageView) findViewById(R.id.ivCoin);
        formLayout = (LinearLayout) findViewById(R.id.formLayout);
        resultLayout = (LinearLayout) findViewById(R.id.resultLayout);

        // Get animations
        mediaPlayer = MediaPlayer.create(this, R.raw.coin);
        fadeout = AnimationUtils.loadAnimation(this, R.anim.fadeout);
        fadein = AnimationUtils.loadAnimation(this, R.anim.fadein);

        // Add OnClickListener
        btnFlip.setOnClickListener(this);
        btnFlipAgain.setOnClickListener(this);


        ivCoin.setBackgroundResource(R.drawable.coin01);
    }

    @Override
    public void onClick(View view) {
        if (view == btnFlip) {
            res = 0;
            ivCoin.setBackgroundResource(R.drawable.coinflip_animation);
            coinflipAnimation = (AnimationDrawable) ivCoin.getBackground();

            TranslateAnimation anim = new TranslateAnimation(
                    TranslateAnimation.RELATIVE_TO_SELF, 0f,
                    TranslateAnimation.RELATIVE_TO_SELF, 0f,
                    TranslateAnimation.RELATIVE_TO_SELF, 0f,
                    TranslateAnimation.RELATIVE_TO_SELF, -1.4f); // this is distance of top and bottom form current position

            anim.setDuration(2000);
            anim.setRepeatCount(1);
            anim.setRepeatMode(Animation.REVERSE);

            formLayout.setAnimation(fadeout);
            formLayout.setVisibility(View.INVISIBLE);

            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation arg0) {
                    coinflipAnimation.start();
                    res = random.nextBoolean() ? 1 : 2;
                    mediaPlayer.start();
                }

                @Override
                public void onAnimationRepeat(Animation arg0) {
                }

                @Override
                public void onAnimationEnd(Animation arg0) {


                    coinflipAnimation.stop();
                    if (res == 1) {
                        result = etHead.getText().toString();
                        ivCoin.setBackgroundResource(R.drawable.coinheads);
                        if(!etHead.getText().equals("")){
                            saveResult(res);
                        }
                    } else if (res == 2) {
                        result = etTail.getText().toString();
                        ivCoin.setBackgroundResource(R.drawable.cointails);
                        if(!etTail.getText().equals("")){
                            saveResult(res);
                        }
                    }
                    tvResult.setText(result);

                    formLayout.setVisibility(View.GONE);
                    resultLayout.setAnimation(fadein);
                    resultLayout.setVisibility(View.VISIBLE);
                    //TODO SAVE TO DATABASE

                }
            });

            ivCoin.startAnimation(anim);

        } else if (view == btnFlipAgain) {
            resultLayout.setAnimation(fadeout);
            resultLayout.setVisibility(View.INVISIBLE);
            formLayout.setAnimation(fadein);
            formLayout.setVisibility(View.VISIBLE);
        }
    }

    public void saveResult(int res) {
        logger.info("Saving result<" + res + ">");
        db = openOrCreateDatabase("coinflipdb", Context.MODE_PRIVATE, null);

        try {
            db.execSQL("INSERT INTO toss (res, resmsg, date) VALUES ('" + res + "','" +
                    result + "','" +
                    formatter.format(new Date())+ "')");

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("New task insert error.");
        }



    }
}
