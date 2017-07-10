/*package com.ricebean.petenet001.sos_urgent;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import com.github.clans.fab.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.ricebean.petenet001.sos_urgent.Adapters.MyAdapter;
import com.ricebean.petenet001.sos_urgent.Adapters.UrgenceAdapter;
import com.ricebean.petenet001.sos_urgent.Object.Urgence;

import java.util.ArrayList;

import static com.ricebean.petenet001.sos_urgent.R.string.close;
import static com.ricebean.petenet001.sos_urgent.R.string.open;


public class MainActivity extends AppCompatActivity{

    //nos clés de valeurs à passer dans l'intent
    public static final String TITRE_URGENCE = "titre_urgence";
    public static final String DESCRIPTION_URGENCE = "description_urgence";
    public static final String IMAGE_URGENCE = "image_urgence";
    public static final String QUE_FAIRE = "que_faire";
    public static final String NE_PAS_FAIRE = "ne_pas_faire";


    public DrawerLayout mDrawerLayout;
    public NavigationView mNavigationView;
    public Toolbar toolbar;

    private FloatingActionMenu fam;
    private FloatingActionButton fabUrgencePolice, fabUrgenceMedicale, fabUrgenceIncendie;


    //recyclerView
    RecyclerView urgenceRecyclerView;
    MyAdapter urgenceAdapter;
    RecyclerView.LayoutManager layoutManager;

    //initialisation de notre list
    ArrayList<Urgence> urgenceList = new ArrayList<>();
    String[] titres_urgence, descriptions_urgence, que_faire, ne_pas_faire;
    int[] images_urgence = {R.drawable.snake, R.drawable.noyade};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_main_activity);

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle(getTitle());
        setSupportActionBar(toolbar);



        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.navigationView);
        //Drawer Menu
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, open, close) {
            @Override
            public void onDrawerOpened(View drawerView) {

                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();



        urgenceRecyclerView = (RecyclerView) findViewById(R.id.my_recyclerView);
        //remplir la liste des urgence
        initializeUrgence();

        urgenceRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        urgenceAdapter = new MyAdapter(this,urgenceList);

        layoutManager = new LinearLayoutManager(this);
        urgenceRecyclerView.setLayoutManager(layoutManager);
        urgenceRecyclerView.setHasFixedSize(true);
        urgenceRecyclerView.setAdapter(urgenceAdapter);


        fam = (FloatingActionMenu) findViewById(R.id.fab_menu);
        fabUrgencePolice = (FloatingActionButton) findViewById(R.id.fab2);
        fabUrgenceMedicale = (FloatingActionButton) findViewById(R.id.fab3);
        fabUrgenceIncendie = (FloatingActionButton) findViewById(R.id.fab1);

        //methode d'écoute de chaque click des bouttons
        fabUrgenceIncendie.setOnClickListener(onButtonClick());
        fabUrgenceMedicale.setOnClickListener(onButtonClick());
        fabUrgencePolice.setOnClickListener(onButtonClick());
        fam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fam.isOpened()) {
                    fam.close(true);
                }
            }
        });



    }

    private void initializeUrgence() {
        titres_urgence = getResources().getStringArray(R.array.titres_urgence);
        descriptions_urgence = getResources().getStringArray(R.array.descriptions_urgence);
        que_faire = getResources().getStringArray(R.array.desc_que_faire_urgence);
        ne_pas_faire = getResources().getStringArray(R.array.desc_ne_pa_faire_urgence);

        int i = 0;

        for (String titres : titres_urgence) {
            Urgence urgence = new Urgence(titres_urgence[i], descriptions_urgence[i], que_faire[i], ne_pas_faire[i], images_urgence[i]);
            urgenceList.add(urgence);
            i++;
        }


    }




    private View.OnClickListener onButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == fabUrgencePolice) {
                    urgencePolice();
                } else if (view == fabUrgenceMedicale) {
                    urgenceMedicale();
                } else if(view == fabUrgenceIncendie){
                    urgenceIncendie();
                }else{
                    Toast.makeText(MainActivity.this, "nothing", Toast.LENGTH_SHORT).show();
                }
                fam.close(true);
            }
        };
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.app_menu, menu);
        //getMenuInflater().inflate(R.menu.menu_drawer,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_about:

                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setView(R.layout.about_section)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                builder.setCancelable(true);
                            }
                        });
                builder.show();

                return true;

            case R.id.share:
                Toast.makeText(this, "partager", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void urgencePolice(){

        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:06-993-68-76"));

        try{

            startActivity(phoneIntent);
            finish();
            Log.i("resultat","Appel reussi");
        }catch(ActivityNotFoundException anfe){
            Toast.makeText(this, "Appel échoué,veuilez reéssayer s'il vous plait", Toast.LENGTH_SHORT).show();
            Log.i("resultat","Appel échoué");
        }
    }
    private void urgenceMedicale(){

        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:069276466"));

        try{

            startActivity(phoneIntent);
            finish();
            Log.i("resultat","Appel reussi");
        }catch(ActivityNotFoundException anfe){
            Toast.makeText(this, "Appel échoué,veuilez reéssayer s'il vous plait", Toast.LENGTH_SHORT).show();
            Log.i("resultat","Appel échoué");
        }
    }

    private void urgenceIncendie(){

        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:068881901"));

        try{

            startActivity(phoneIntent);
            finish();
            Log.i("resultat","Appel reussi");
        }catch(ActivityNotFoundException anfe){
            Toast.makeText(this, "Appel échoué,veuilez reéssayer s'il vous plait", Toast.LENGTH_SHORT).show();
            Log.i("resultat","Appel échoué");
        }
    }

    private void sendSOS(){

        /*Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");

        smsIntent.putExtra("address", new String("066706715"));
        smsIntent.putExtra("sms_body", new String("test de méssage sos"));*/
/*
        String numPhone = "069276466";
        String username = "Alyson peter";
        String message = "De : "+username+"\ntest de méssage sos-urgent";


        try{

            SmsManager smsManager =  SmsManager.getDefault();
            smsManager.sendTextMessage(numPhone, null, message, null, null);

            Toast.makeText(getApplicationContext(), "sms envoyé", Toast.LENGTH_SHORT).show();
            Log.i("resultat","sms transmis");
        }catch(Exception e){
            Toast.makeText(getApplicationContext(), "sms échoué,veuilez reéssayer s'il vous plait", Toast.LENGTH_SHORT).show();
            Log.i("resultat","sms échoué");
            e.printStackTrace();
        }
    }

}*/
