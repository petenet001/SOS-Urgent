package com.ricebean.petenet001.sos_urgent.Object;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.ricebean.petenet001.sos_urgent.R;

import java.lang.reflect.Array;
import java.util.Locale;

public class DetailsActivity extends AppCompatActivity{

    TextView titre_urgence, description_urgence, detail_faire, nepasfaire;
    ImageView image_urgence;
    Toolbar toolbar;
    CollapsingToolbarLayout collapser;
    FloatingActionButton fabCall;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        collapser = (CollapsingToolbarLayout) findViewById(R.id.collapser);



        titre_urgence = (TextView) findViewById(R.id.detail_titre_urgence);
        image_urgence = (ImageView) findViewById(R.id.detail_image_urgence);
        description_urgence = (TextView) findViewById(R.id.detail_urgence_description);
        detail_faire = (TextView) findViewById(R.id.detail_que_faire);
        nepasfaire = (TextView) findViewById(R.id.detail_ne_pas_faire);
        fabCall = (FloatingActionButton) findViewById(R.id.fabCall);


        Intent currentIntent = this.getIntent();

        if(currentIntent != null) {

            String titre =currentIntent.getExtras().getString("TITRE");
            String description =currentIntent.getExtras().getString("DESCRIPTION");
            int imageUrgent =currentIntent.getExtras().getInt("IMAGE");
            String faire =currentIntent.getExtras().getString("QUE_FAIRE");
            String pasFaire =currentIntent.getExtras().getString("NE_PAS_FAIRE");

            image_urgence.setImageResource(imageUrgent);
            titre_urgence.setText(titre);
            description_urgence.setText(description);
            detail_faire.setText(faire);
            nepasfaire.setText(pasFaire);

            //toolbar = (Toolbar) findViewById(R.id.toolbar);
            //toolbar.setTitle(titre_urgence.getText().toString());
            setSupportActionBar(toolbar);


        }

        collapser.setTitle(titre_urgence.getText().toString());

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.action_about:

                        final AlertDialog.Builder builder = new AlertDialog.Builder(DetailsActivity.this);
                        builder.setView(R.layout.about_section)
                                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        builder.setCancelable(true);
                                    }
                                });
                        builder.show();

                        return true;

                    default:
                        return DetailsActivity.super.onMenuItemSelected(item.getItemId(),item);

                }
            }
        });
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        onFabCallClick();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_menu, menu);
        return true;
    }

    public void onFabCallClick(){




        fabCall.setOnClickListener(new View.OnClickListener() {

            /*
            *
            * String[] lg =  Locale.getISOLanguages();
                    for (String s : lg){
                        Log.d("TAG", "onClick: Local "+s);
                    }
            * */

            @Override
            public void onClick(View view) {
                String str = titre_urgence.getText().toString();
                if (str.toLowerCase().contains("incendie") || str.toLowerCase().contains("feu")){
                    urgenceIncendie();
                }else if(str.toLowerCase().contains("morsure") || str.toLowerCase().contains("blessure") || str.toLowerCase().contains("noyade") || str.toLowerCase().contains("fracture") || str.toLowerCase().contains("cardiaque") || str.toLowerCase().contains("étouffement")){
                    urgenceMedicale();
                }else if(str.toLowerCase().contains("vol") || str.toLowerCase().contains("cambriolage")){
                    urgencePolice();
                }
            }
        });

    }

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

}
