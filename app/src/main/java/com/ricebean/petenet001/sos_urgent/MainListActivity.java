package com.ricebean.petenet001.sos_urgent;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.ricebean.petenet001.sos_urgent.Adapters.MyAdapter;
import com.ricebean.petenet001.sos_urgent.Object.Urgence;
import java.util.ArrayList;


public class MainListActivity extends AppCompatActivity{


    public String[] titres_urgence, descriptions_urgence,que_faire,ne_pas_faire;
    public int[] images_urgence = {R.drawable.signesavc,R.drawable.snake, R.drawable.noyade,R.drawable.incendie,R.drawable.ballesblessures};
    private ArrayList<Urgence> urgenceList = new ArrayList<>();
    public MyAdapter urgenceAdapter ;
    public RecyclerView urgenceRecyclerView;

    Toolbar toolbar;

    private FloatingActionMenu fam;
    private FloatingActionButton fabUrgencePolice, fabUrgenceMedicale, fabUrgenceIncendie;



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_main_activity);

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.soslogotoolbar);
        setTitle(R.string.app_name);

        initializeUrgence();
        urgenceRecyclerView = (RecyclerView) findViewById(R.id.my_recyclerView);
        urgenceRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        urgenceRecyclerView.setHasFixedSize(true);
        urgenceAdapter = new MyAdapter(this,urgenceList);
        urgenceRecyclerView.setAdapter(urgenceAdapter);

        floatingActionMenu();

    }


    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.list_main_activity);

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.soslogotoolbar);
        setTitle(R.string.app_name);

        initializeUrgence();
        urgenceRecyclerView = (RecyclerView) findViewById(R.id.my_recyclerView);
        urgenceRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        urgenceRecyclerView.setHasFixedSize(true);
        urgenceAdapter = new MyAdapter(this,urgenceList);
        urgenceRecyclerView.setAdapter(urgenceAdapter);

        floatingActionMenu();
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


    private void floatingActionMenu(){
        fam = (FloatingActionMenu) findViewById(R.id.fab_menu);
        fabUrgencePolice = (FloatingActionButton) findViewById(R.id.fab2);
        fabUrgenceMedicale = (FloatingActionButton) findViewById(R.id.fab3);
        fabUrgenceIncendie = (FloatingActionButton) findViewById(R.id.fab1);

        fam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fam.isOpened()) {
                    fam.close(true);
                }
            }
        });
        fabUrgenceIncendie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.isEnabled()){
                    urgenceIncendie();
                }
            }
        });
        fabUrgenceMedicale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.isEnabled()){
                    urgenceMedicale();
                }
            }
        });
        fabUrgencePolice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.isEnabled()){
                    urgencePolice();
                }

            }
        });


    }//fin floting menu


    private void urgencePolice(){
        String police;
        police = getResources().getString(R.string.urgence_police);

        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse(police));

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
        String medicale;
        medicale = getResources().getString(R.string.urgence_medicale);

        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse(medicale));

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
        String incendie;
        incendie = getResources().getString(R.string.urgence_incendie);


        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse(incendie));

        try{

            startActivity(phoneIntent);
            finish();
            Log.i("resultat","Appel reussi");
        }catch(ActivityNotFoundException anfe){
            Toast.makeText(this, "Appel échoué,veuilez reéssayer s'il vous plait", Toast.LENGTH_SHORT).show();
            Log.i("resultat","Appel échoué");
        }
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

                final AlertDialog.Builder builder = new AlertDialog.Builder(MainListActivity.this);
                builder.setView(R.layout.about_section)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                builder.setCancelable(true);
                            }
                        });
                builder.show();

                return true;

            case R.id.share:
                String sharedText = getResources().getString(R.string.intent_shared_text);
                String contentBody ="Découvrez notre Apps a l'addresse suivant https//:google.fr";
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                //shareIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                shareIntent.putExtra(Intent.EXTRA_TEXT, contentBody);
                startActivity(Intent.createChooser(shareIntent,sharedText));
                return true;

            case R.id.action_settings:
                Intent is = new Intent(getBaseContext(),SettingsActivity.class);
                startActivity(is);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }


    public void MakeToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}