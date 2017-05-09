package software.blackstone.masjidtawheedmobileradio;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static software.blackstone.masjidtawheedmobileradio.R.id.media_play;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MediaPlayer.OnPreparedListener {
    String urlSource = null;
    String url = "https://masjidtawheed.net/";

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.arab_english) {
            // Handle the camera action
            urlSource = "https://api.spreaker.com/v2/users/7725967/episodes?limit=1";
        } else if (id == R.id.somali) {
            urlSource = "https://api.spreaker.com/v2/users/8106997/episodes?limit=1";

        } else if (id == R.id.haara) {
            urlSource = "https://api.spreaker.com/v2/users/8106997/episodes?limit=1";
        }

        else if (id == R.id.web) {
            urlSource = "https://api.spreaker.com/v2/users/8106997/episodes?limit=1";
            Uri uriUrl = Uri.parse(url);
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            startActivity(launchBrowser);
        }

        else if (id == R.id.mailsend) {
            Intent email = new Intent(Intent.ACTION_SEND);

            // need this to prompts email client only
            email.setData(Uri.parse("mailto:blackstonesoftwarellc@gmail.com"));
            email.setType("message/rfc822");
            startActivity(Intent.createChooser(email, "Choose an Email client"));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    MediaPlayer mediaPlayer = new MediaPlayer();
    //String url = "http://api.spreaker.com/listen/episode/9431951/shoutcast?force_http=true";
    //String url = "https://api.spreaker.com/v2/users/7725967/episodes?limit=1";
    ImageButton buttonPlay;
    ImageButton buttonStop;
    ImageButton buttonPause;

    public OkHttpClient client = new OkHttpClient();
    public Gson gson = new Gson();


    public class HttpRequestTask extends AsyncTask<Void, Void, RadioItems[]> {

        public RadioItems[] doInBackground(Void... params) {


            final Request request = new Request.Builder().url(urlSource).build();

            RadioItems[] items = null;


            try {
                final com.squareup.okhttp.Response response = client.newCall(request).execute();

                if(response.isSuccessful()) {
                    final RadioProgramInfo radioProgramInfo = gson.fromJson(response.body().charStream(), RadioProgramInfo.class);

                    items = radioProgramInfo.getResponse().getItems();
                    //String dynamicSiteUrl = items[0].getSite_url();

                } else {
                    throw new RuntimeException("ooops!");
                }

            } catch (Throwable t) {
                Log.e("MainActivity", t.getMessage(), t);
            }

            return items;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


        buttonPlay = (ImageButton) findViewById(media_play);
        buttonPlay.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    final RadioItems[] items = new HttpRequestTask().execute().get();
                    //mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer = new MediaPlayer();
                    //mediaPlayer.release();
//                    mediaPlayer = null;
                    String sourceStream = "http://api.spreaker.com/listen/episode/" + items[0].getEpisode_id() + "/shoutcast?force_http=true";
                    String lectureType = items[0].getType();
                    String lectureTopic = items[0].getTitle();
                    //String logoMasjid = items[0].image_original_url;

                    TextView titleOfLecture = (TextView) findViewById(R.id.songName);
                    titleOfLecture.setText(lectureTopic);
                    TextView typeOfLecture = (TextView) findViewById(R.id.type);
                    typeOfLecture.setText(lectureType);
//                    if (Objects.equals(lectureType, "RECORDED")) {
//                        typeOfLecture.setTextColor(Integer.parseInt("#12741e"));
//                    }
//                    else if (Objects.equals(lectureType, "LIVE")) {
//                        typeOfLecture.setTextColor(Integer.parseInt("#FF0000"));
//                    }
//                    masjidLogo.setImageDrawable(Drawable.createFromPath(logoMasjid));
                    //Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(masjidLogo);

                    //String sourceStream2 = "https://www.spreaker.com/episode/9643439/shoutcast?force_http=true";


                    if (mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                        mediaPlayer.release();
                        //mediaPlayer = null;
                    }


                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setDataSource(sourceStream);
                    mediaPlayer.prepareAsync();
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
                    {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mediaPlayer.start();
                        }
                    });

                } catch (IllegalArgumentException e) {
                    Toast.makeText(getApplicationContext(), "Setting Lesson Stream. Please wait (1)", Toast.LENGTH_LONG).show();
                } catch (SecurityException e) {
                    Toast.makeText(getApplicationContext(), "Setting Lesson Stream. Please wait (2)", Toast.LENGTH_LONG).show();
                } catch (IllegalStateException e) {
                    Toast.makeText(getApplicationContext(), "Setting Lesson Stream. Please wait (3)", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
//                try {
//                    mediaPlayer.prepare();
//                } catch (IllegalStateException e) {
//                    Toast.makeText(getApplicationContext(), "Setting Lesson Stream. Please wait (4)", Toast.LENGTH_LONG).show();
//                } catch (IOException e) {
//                    Toast.makeText(getApplicationContext(), "Setting Lesson Stream. Please wait (5)", Toast.LENGTH_LONG).show();
//                }
                mediaPlayer.start();
            }
        });

        buttonStop = (ImageButton) findViewById(R.id.media_pause);
        buttonStop.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    mediaPlayer.release();
                }
            }
        });

//        buttonPause = (ImageButton) findViewById(R.id.paused);
//        buttonPause.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
//                    mediaPlayer.pause();
//                }
//            }
//        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
   /* @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.arab_english) {
            // Handle the camera action


        } else if (id == R.id.somali) {

        } else if (id == R.id.haara) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/

    @Override
    protected void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client.connect();
        //new HttpRequestTask().execute();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://software.blackstone.masjidtawheedmobileradio/http/host/path")
        );
        //AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://software.blackstone.masjidtawheedmobileradio/http/host/path")
        );
        //AppIndex.AppIndexApi.end(client, viewAction);
        //client.disconnect();
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {

    }

}
