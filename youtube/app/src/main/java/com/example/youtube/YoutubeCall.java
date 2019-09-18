package com.example.youtube;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;


public class YoutubeCall extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;

    private RecyclerView recyclerView;
    private VideoAdapter adapter;
    private List<Video> videoList;

 //  Intent intent;
  /*  Bundle extras = intent.getExtras();
        if(extras != null) {
        String data = extras.getString("keyName");
    }*/








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtube_call);

  /*      ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);*/


       /* setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("@string/app_name");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
*/
        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(Config.YOUTUBE_API_KEY, this);
/*

        Intent intent =getIntent();
       ur1=(String)intent.getSerializableExtra("ur1");
       String name=(String) intent.getSerializableExtra("name");
       String viewd=(String) intent.getSerializableExtra("views");
*/

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowCustomEnabled(true);
        toolbar.setTitle("Youtube");


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        videoList = new ArrayList<>();
          adapter = new VideoAdapter(this, videoList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        preparevideos();

        try {
            Glide.with(this).load(R.drawable.download).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//end onCreat
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), HomePageActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    @Override
       public void onPause () {
        super.onPause();
        finish();
    }
    private void preparevideos() {
        int[] covers = new int[]{
                R.drawable.cupcake,
                R.drawable.cupcake,
                R.drawable.cupcake,
        };

        Video a1 = new Video("cheesecake", 13, covers[0]);
        videoList.add(a1);

        Video a2 = new Video("carrot cake", 8, covers[1]);
        videoList.add(a2);

        Video a3 = new Video("vanilla cake", 11, covers[2]);
        videoList.add(a3);


        adapter.notifyDataSetChanged();
    }


    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {
            player.cueVideo("iu36cQEm2Qs"); // Plays https://www.youtube.com/watch?v=fhWaJi1Hsfo

        }
    }

    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            String error = String.format(getString(R.string.player_error), errorReason.toString());
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.YOUTUBE_API_KEY, this);
        }
    }

    protected Provider getYouTubePlayerProvider() {
        return youTubeView;
    }
    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }






}
