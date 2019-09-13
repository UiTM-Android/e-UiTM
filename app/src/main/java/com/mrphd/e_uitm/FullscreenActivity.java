package com.mrphd.e_uitm;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class FullscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        try { this.getSupportActionBar().hide(); } catch (final NullPointerException ignored){}
        // TODO: stuff
    }

}
