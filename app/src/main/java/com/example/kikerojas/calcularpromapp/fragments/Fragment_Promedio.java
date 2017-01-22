package com.example.kikerojas.calcularpromapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kikerojas.calcularpromapp.R;
import com.example.kikerojas.calcularpromapp.adapters.Adapter_Promedio;
import com.example.kikerojas.calcularpromapp.entidades.Entities_Promedio;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by kikerojas on 30/09/2016.
 */

public class Fragment_Promedio extends Fragment implements Adapter_Promedio.OnPromedioClickListener{

    private RecyclerView recyclerView;
    private Adapter_Promedio adapter;

    List<Entities_Promedio> mlistPromedio= new ArrayList<>();

    public OnPromedioClickListener listener;

    public interface OnPromedioClickListener{
        void onPromedioClickListener(int position,Entities_Promedio entitiesPromedio,View view);
        void onAddNewPromedio(Double Total);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragments_promedio, container, false);
        ButterKnife.bind(this,rootView);

        recyclerView = (RecyclerView)rootView.findViewById(R.id.rv_expenses);
        adapter= new Adapter_Promedio(getActivity(),mlistPromedio,getActivity(),this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
      //  insertdata();
        return rootView;
    }
/*
    private void insertdata(){
        Entities_Promedio promedio = new Entities_Promedio("24/12/2016",12.0,12.0,20.1);
        mlistPromedio.add(promedio);
        promedio = new Entities_Promedio("24/12/2016",12.0,12.0,20.1);
        mlistPromedio.add(promedio);
        promedio = new Entities_Promedio("24/12/2016",12.0,12.0,20.1);
        mlistPromedio.add(promedio);
        promedio = new Entities_Promedio("24/12/2016",12.0,12.0,20.1);
        mlistPromedio.add(promedio);

    }
*/
    @Override
    public void onPromedioClickListener(int position, Entities_Promedio entitiesPromedio, View view) {
        listener.onPromedioClickListener(position,entitiesPromedio,view);
    }

    @Override
    public void onAddNewPromedio(Double Total) {
        listener.onAddNewPromedio(Total);
    }


    public void  addnewPromedio(Entities_Promedio entitiesPromedio){
        adapter.addnewPromedio(entitiesPromedio);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPromedioClickListener){
            //listenerAdd = (OnAddnewCajaGasto) context;
            listener = (OnPromedioClickListener) context;
        }else{
            throw new RuntimeException(context.toString() +
                    " must implement OnAddnewExpenses");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        // listenerAdd = null;
        listener = null;
    }
}
