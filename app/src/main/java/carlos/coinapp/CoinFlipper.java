package carlos.coinapp;

import android.graphics.drawable.AnimationDrawable;
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

import java.util.Random;

public class CoinFlipper extends AppCompatActivity implements Button.OnClickListener {

    private Button btnFlip;
    private EditText etHead;
    private EditText etTail;
    private ImageView ivCoin;
    private AnimationDrawable coinflipAnimation;
    private LinearLayout formLayout;
    private static final Random random = new Random();
    private int res;

    Animation fadeout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_flipper);
        /*this.getActionBar().setDisplayHomeAsUpEnabled(true);*/

        // Get view elements
        btnFlip = (Button) findViewById(R.id.btnFlip);
        etHead = (EditText) findViewById(R.id.etHead);
        etTail = (EditText) findViewById(R.id.etTail);
        ivCoin = (ImageView) findViewById(R.id.ivCoin);
        formLayout = (LinearLayout) findViewById(R.id.formLayout);

        // Get animations
        fadeout = AnimationUtils.loadAnimation(this, R.anim.fadeout);

        // Add OnClickListener
        btnFlip.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == btnFlip) {
            res=0;
            ivCoin.setBackgroundResource(R.drawable.coinflip_animation);
            coinflipAnimation = (AnimationDrawable) ivCoin.getBackground();

            TranslateAnimation anim = new TranslateAnimation(
                    TranslateAnimation.RELATIVE_TO_SELF, 0f,
                    TranslateAnimation.RELATIVE_TO_SELF, 0f,
                    TranslateAnimation.RELATIVE_TO_SELF, 0f,
                    TranslateAnimation.RELATIVE_TO_SELF, -1.8f); // this is distance of top and bottom form current position

            anim.setDuration(2000);
            anim.setRepeatCount(1);
            anim.setRepeatMode(Animation.REVERSE);
            anim.setAnimationListener(new Animation.AnimationListener(){
                @Override
                public void onAnimationStart(Animation arg0) {
                    coinflipAnimation.start();
                    // TODO ADD SOUND
                    res = random.nextBoolean() ? 1 : 2;
                }
                @Override
                public void onAnimationRepeat(Animation arg0) {
                }
                @Override
                public void onAnimationEnd(Animation arg0) {
                    coinflipAnimation.stop();

                    //TODO SAVE TO DATABASE
                }
            });

            ivCoin.startAnimation(anim);

        }
    }
}
