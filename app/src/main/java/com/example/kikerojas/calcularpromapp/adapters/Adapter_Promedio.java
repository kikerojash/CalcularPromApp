package com.example.kikerojas.calcularpromapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kikerojas.calcularpromapp.R;
import com.example.kikerojas.calcularpromapp.Utils.Constans;
import com.example.kikerojas.calcularpromapp.entidades.Entities_Promedio;
import com.example.kikerojas.calcularpromapp.holders.Holder_Promedio;

import java.util.List;

/**
 * Created by kikerojas on 30/09/2016.
 */

public class Adapter_Promedio extends RecyclerView.Adapter<Holder_Promedio> {

    private static final String TAG = "Adapter_Promedio";
    private List<Entities_Promedio> mListPromedio ;
    private Activity mactivity;
    private Context mContext;

    public OnPromedioClickListener listener;



    public interface OnPromedioClickListener{
        void onPromedioClickListener(int position, Entities_Promedio entitiesPromedio, View view);
        void onAddNewPromedio(Double Total);
    }


    public Adapter_Promedio(Context mContext,List<Entities_Promedio> mListPromedio, Activity mactivity, OnPromedioClickListener listener) {
        this.mContext = mContext;
        this.mListPromedio = mListPromedio;
        this.mactivity = mactivity;
        this.listener = listener;
    }

    @Override
    public Holder_Promedio onCreateViewHolder(ViewGroup parent, int viewType) {
        //Log.d(Utils.TAG, "onCreateViewHolder");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.adapter_layout_promedio, parent, false);
        // Return a new holder instance
        return new Holder_Promedio(contactView);
    }

    @Override
    public void onBindViewHolder(final Holder_Promedio holder, int position) {
        final Entities_Promedio entities_promedio = mListPromedio.get(position);

        holder.bind(entities_promedio,mContext,listener);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //  CharSequence items[] = new CharSequence[] {"Editar", "Eliminar"};
                final CharSequence[] items = {"Editar", "Eliminar"};

                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        mactivity);
                alertDialogBuilder.setTitle("Selecione Opción");
                alertDialogBuilder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // the user clicked on colors[which]
                        if (items[which].equals("Eliminar")) {
                            alertDialogBuilder.setTitle("Eliminar");
                            // set dialog message
                            alertDialogBuilder
                                    .setMessage("Desea eliminar!")
                                    .setCancelable(false)
                                    .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            listener.onPromedioClickListener(Constans.CLICK_ELIMINAR_PROMEDIO, entities_promedio,holder.itemView);


                                        }
                                    })
                                    .setNegativeButton("No",new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            // if this button is clicked, just close
                                            // the dialog box and do nothing
                                            dialog.cancel();
                                        }
                                    });
                            // create alert dialog
                            AlertDialog alertDialog = alertDialogBuilder.create();
                            // show it
                            alertDialog.show();
                        } else if (items[which].equals("Editar")) {
                            listener.onPromedioClickListener(Constans.CLICK_EDITAR_PROMEDIO, entities_promedio, holder.itemView);

                        } else if (items[which].equals("Cancel")) {
                            dialog.dismiss();
                        }

                    }
                });
                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialogBuilder.show();
                /*Final Dialog*/
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListPromedio.size();
    }

    /*ADD SAVE PROMEDIO*/

    public void addnewPromedio(Entities_Promedio entitiesPromedio){
        mListPromedio.add(entitiesPromedio);
        int position = getItemCount();
        notifyItemInserted(--position);//3 o 2 position
        listener.onAddNewPromedio(getTotal());


    }
    public Double getTotal(){
        Double total = 0.0;
        for (int i=0; i < mListPromedio.size(); i++){
            total += mListPromedio.get(i).getmTotal();
        }
        return total;
    }



}
