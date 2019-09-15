package com.mrphd.e_uitm;

import java.net.HttpCookie;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import com.google.android.material.navigation.NavigationView;

import android.webkit.HttpAuthHandler;
import android.widget.Toast;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_ilearn)
        {
            // Handle the camera action
        }
        else if (id == R.id.nav_eacademy)
        {

        }
        else if (id == R.id.nav_financial)
        {

        }
        else if (id == R.id.nav_ehep)
        {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Initialize account
        final HttpRequest request = new HttpRequest.Post()
                .withHeader("Host", "i-learn.uitm.edu.my")
                .withHeader("Connection", "keep-alive")
                .withHeader("Cache-Control", "max-age=0")
                .withHeader("Origin", "https://i-learn.uitm.edu.my")
                .withHeader("Upgrade-Insecure-Requests", "1")
                .withHeader("Content-Type", "application/x-www-form-urlencoded")
                .withHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36")
                .withHeader("Sec-Fetch-Mode", "navigate")
                .withHeader("Sec-Fetch-User", "?1")
                .withHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3")
                .withHeader("Sec-Fetch-Site", "same-origin")
                .withHeader("Referer", "https://i-learn.uitm.edu.my/v3/users/loginForm/1")
                .withHeader("Accept-Encoding", "gzip, deflate, br")
                .withHeader("Accept-Language", "en-US,en;q=0.9")
                .withHeader("Set-Cookie", "_ga=GA1.3.1396156418.1562080327; __tawkuuid=e::i-learn.uitm.edu.my::YwRJzjdMOpzri5Tuh4nAdE5Cea0b6tJ7w79g7L0nJuILtDm1Ltudi4rfmO91QkT+::2; rxVisitor=1568542766043OL30O8URRTHQTCE9SDIFS7B2MOA6LQQE; CAKEPHP=loj71ohcaf7h6setv5g72flhd2; cookiesession1=47E99C00WQ9MU3CUYHCMCRVMGPLZAF25; _gid=GA1.3.406486059.1568554170; _gat_gtag_UA_117845915_1=1; TawkConnectionTime=0; dtPC=1$154578263_592h-vUMSLQUTXFJMAEOHYXGRISBDOYGHVUKGA; dtLatC=20; rxvt=1568556391335|1568554158810; dtSa=true%7CC%7C-1%7CLogin%7C-%7C1568554610103%7C154578263_592%7Chttps%3A%2F%2Fi-learn.uitm.edu.my%2Fv3%2Fusers%2FloginForm%2F1%7C%7C1568554587325%7C%7C; dtCookie=1$SALKMIUN01403G2PG3LDQ4SNV16HJ235|simsweb.uitm.edu.my|1|17d402e2bb72c628|0")
                .withParam("_method", "POST")
                .withParam("data%5BUser%5D%5Busername%5D", "2016692964")
                .withParam("data%5BUser%5D%5Bpassword%5D", "Hb061097");

        try
        {
            String response = request.execute("https://i-learn.uitm.edu.my/v3/login").get();
            Toast.makeText(MainActivity.this, "Success: " + response, Toast.LENGTH_SHORT).show();
        }catch(final Exception e){
            Toast.makeText(MainActivity.this, "Error: " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
