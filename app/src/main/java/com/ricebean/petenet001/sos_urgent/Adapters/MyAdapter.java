package com.ricebean.petenet001.sos_urgent.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import com.ricebean.petenet001.sos_urgent.Object.DetailsActivity;
import com.ricebean.petenet001.sos_urgent.Object.Urgence;
import com.ricebean.petenet001.sos_urgent.R;

import java.util.ArrayList;



/**
 * Created by Petenet001 on 31/03/2017.
 */

public class MyAdapter extends  RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Urgence> listeUrgence = new ArrayList<>();
    private Context context;

    public MyAdapter(Context context, ArrayList<Urgence> listeUrgence ) {
        this.listeUrgence = listeUrgence;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, null);

        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Urgence urgence = listeUrgence.get(position);

        holder.titre_urgence.setText(urgence.getUrgence_title());
        holder.description.setText(urgence.getUrgence_description());
        holder.image_urgence.setImageResource(urgence.getUrgence_image());
        holder.que_faire.setText(urgence.getFaire());
        holder.ne_pas_faire.setText(urgence.getNePasfaire());


        final  String titre = urgence.getUrgence_title();
        final  String descriptionUrgence = urgence.getUrgence_description();
        final  String que_faire_si = urgence.getFaire();
        final  String pas_faire_si = urgence.getNePasfaire();
        final  int imageUrgent = urgence.getUrgence_image();

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
               sendDatas(titre,descriptionUrgence,que_faire_si,pas_faire_si,imageUrgent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return listeUrgence.size();
    }

    private void sendDatas(String titre, String descriptionUrgence, String que_faire_si, String pas_faire_si, int image){
        Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("TITRE",titre);
                intent.putExtra("DESCRIPTION",descriptionUrgence);
                intent.putExtra("QUE_FAIRE",que_faire_si);
                intent.putExtra("NE_PAS_FAIRE",pas_faire_si);
                intent.putExtra("IMAGE",image);
          context.startActivity(intent);
    }


        public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            private ImageView image_urgence;
            private TextView titre_urgence, description, que_faire, ne_pas_faire;

            ItemClickListener  itemClickListener ;

            public MyViewHolder(View itemView) {
                super(itemView);

                image_urgence = (ImageView) itemView.findViewById(R.id.image_item);
                titre_urgence = (TextView) itemView.findViewById(R.id.titre_urgence);
                description = (TextView) itemView.findViewById(R.id.description_urgence);
                que_faire = (TextView) itemView.findViewById(R.id.que_faire);
                ne_pas_faire = (TextView) itemView.findViewById(R.id.ne_pas_faire);

                itemView.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {
                this.itemClickListener.OnItemClick(v,getLayoutPosition());
            }

            public void setItemClickListener(ItemClickListener ic ){
                this.itemClickListener=ic;
            }
        }


}
