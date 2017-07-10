package com.ricebean.petenet001.sos_urgent.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ricebean.petenet001.sos_urgent.Object.Urgence;
import com.ricebean.petenet001.sos_urgent.R;

import java.util.ArrayList;


/**
 * Created by Petenet001 on 31/03/2017.
 */

public class UrgenceAdapter extends RecyclerView.Adapter<UrgenceAdapter.UrgenceViewHolder> {

    private ArrayList<Urgence> listeUrgence = new ArrayList<Urgence>();
    private Context context;
    private RecyclerViewItemClickListener listener;

    public UrgenceAdapter(ArrayList<Urgence> listeUrgence, Context context, RecyclerViewItemClickListener listener) {
        this.listeUrgence = listeUrgence;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public UrgenceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);
        return new UrgenceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UrgenceViewHolder holder, int position) {
        Urgence urgence = listeUrgence.get(position);

        if(urgence != null){
            holder.bind(urgence, listener);
        }
        /*
        holder.titre_urgence.setText(urgence.getUrgence_title());
        Glide.with(holder.image_urgence.getContext()).load(urgence.getUrgence_image()).into(holder.image_urgence);
       //holder.image_urgence.setImageResource(urgence.getUrgence_image());
        holder.description.setText(urgence.getUrgence_description());*/

    }

    @Override
    public int getItemCount() {
        return listeUrgence.size();
    }

    public interface RecyclerViewItemClickListener {
        void onClickListener(Urgence urgence, int position);
    }

    public static class UrgenceViewHolder extends RecyclerView.ViewHolder {
        private ImageView image_urgence;
        private TextView titre_urgence, description, que_faire, ne_pas_faire;

        public UrgenceViewHolder(View itemView) {
            super(itemView);

            image_urgence = (ImageView) itemView.findViewById(R.id.image_item);
            titre_urgence = (TextView) itemView.findViewById(R.id.titre_urgence);
            description = (TextView) itemView.findViewById(R.id.description_urgence);
            que_faire = (TextView) itemView.findViewById(R.id.que_faire);
            ne_pas_faire = (TextView) itemView.findViewById(R.id.ne_pas_faire);

        }

        //puis ajouter une fonction pour remplir la cellule en fonction d'un MyObject
        public void bind(final Urgence urgence, final RecyclerViewItemClickListener listener) {
            titre_urgence.setText(urgence.getUrgence_title());
            description.setText(urgence.getUrgence_description());
            image_urgence.setImageResource(urgence.getUrgence_image());
            //Glide.with(image_urgence.getContext()).load(urgence.getUrgence_image()).into(image_urgence);
            que_faire.setText(urgence.getFaire());
            ne_pas_faire.setText(urgence.getNePasfaire());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClickListener(urgence, getLayoutPosition());
                }
            });


        }
    }

}
