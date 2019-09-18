package com.example.youtube;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.LinearLayout;

        import androidx.appcompat.app.AppCompatActivity;


public class SplashActivity extends Activity implements View.OnClickListener {

    LinearLayout splashLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashLayout = findViewById(R.id.splash_youtube);
        splashLayout.setOnClickListener(this);
    }

    @Override
    public void onPause () {
        super.onPause();
        finish();
    }
    public void onClick(View view) {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
    }//end onClick()
}
