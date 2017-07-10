package com.ricebean.petenet001.sos_urgent.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ricebean.petenet001.sos_urgent.Adapters.MyAdapter;
import com.ricebean.petenet001.sos_urgent.Object.Urgence;
import com.ricebean.petenet001.sos_urgent.R;

import java.util.ArrayList;

public class ListUrgencesFragments extends Fragment {

    public static final String TAG = "listUrgencesFragments";
    public String[] titres_urgence, descriptions_urgence,que_faire,ne_pas_faire;
    public int[] images_urgence = {R.drawable.signesavc,R.drawable.snake, R.drawable.noyade,R.drawable.incendie,R.drawable.ballesblessures};
    private ArrayList<Urgence> urgenceList = new ArrayList<>();
    public MyAdapter urgenceAdapter ;
    public RecyclerView urgenceRecyclerView;


    public ListUrgencesFragments() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_main_fragment,container,false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeUrgence();
        urgenceRecyclerView = (RecyclerView) view.findViewById(R.id.my_recyclerView);
        urgenceRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        urgenceRecyclerView.setHasFixedSize(true);
        urgenceAdapter = new MyAdapter(view.getContext(),urgenceList);
        urgenceRecyclerView.setAdapter(urgenceAdapter);
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




}
