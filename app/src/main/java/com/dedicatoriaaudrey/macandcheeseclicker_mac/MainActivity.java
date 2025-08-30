package com.dedicatoriaaudrey.macandcheeseclicker_mac;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageButton cheese;
    TextView counter;
    int count;
    FrameLayout frame;
    Button reset;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton cheese = findViewById(R.id.button);
        TextView counter = findViewById(R.id.counter);

        SoundPool soundPool = new SoundPool.Builder()
                .setMaxStreams(10) // Example: allow up to 10 simultaneous streams
                .build();

        int soundId = soundPool.load(this, R.raw.cheeeeeese, 1); // 'this' is the Context, 1 is priority
        int soundId2 = soundPool.load(this, R.raw.join, 1); // 'this' is the Context, 1 is priority
        int soundId3 = soundPool.load(this, R.raw.areyou, 1); // 'this' is the Context, 1 is priority

        frame = findViewById(R.id.root_frame);

        Button reset = findViewById(R.id.reset);


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count=0;
                counter.setText(count + "");
                soundPool.play(soundId3, 1.0f, 1.0f, 1, 0, 1.0f);
                for (int i = 0; i < frame.getChildCount(); i++) {
                    View child = frame.getChildAt(i);
                    if (child instanceof TextView) {
                        ((TextView) child).setText("");
                    }
                }

                ObjectAnimator scaleX = ObjectAnimator.ofFloat(reset, "scaleX", 1.0f, 1.2f, 1.0f);
                ObjectAnimator scaleY = ObjectAnimator.ofFloat(reset, "scaleY", 1.0f, 1.2f, 1.0f);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(scaleX, scaleY);
                animatorSet.setDuration(400);
                animatorSet.start();
            }
        });

        cheese.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                //Animation bounce = AnimationUtils.loadAnimation(this, R.anim.anim);
               // cheese.startAnimation(bounce);

                count++;
                counter.setText(count + "");

                mac();

                if(count%25==0)
                    soundPool.play(soundId2, 1.0f, 1.0f, 1, 0, 1.0f);
                else
                    soundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);

                ObjectAnimator scaleX = ObjectAnimator.ofFloat(cheese, "scaleX", 1.0f, 1.2f, 1.0f);
                ObjectAnimator scaleY = ObjectAnimator.ofFloat(cheese, "scaleY", 1.0f, 1.2f, 1.0f);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(scaleX, scaleY);
                animatorSet.setDuration(400);
                animatorSet.start();
            }
        });
    }

    public void mac() {
        System.out.println("JKHAEFJKLHDKJFHAJKDSHFLKASHDFKJLADSHFKJASDF");
        for (int i = 0; i < 5; i++) {
            TextView chez = new TextView(this);
            chez.setText("🧀");
            chez.setTextSize(32);

            Random random = new Random();
            int x = random.nextInt(frame.getWidth() - 100);
            int y = random.nextInt(frame.getHeight() - 100);

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
            );
            params.leftMargin = x;
            params.topMargin = y;
            chez.setLayoutParams(params);
            frame.addView(chez);
        }
    }


}