package com.example.kikerojas.calcularpromapp.holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.kikerojas.calcularpromapp.R;
import com.example.kikerojas.calcularpromapp.adapters.Adapter_Promedio;
import com.example.kikerojas.calcularpromapp.entidades.Entities_Promedio;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kikerojas on 30/09/2016.
 */

public class Holder_Promedio extends RecyclerView.ViewHolder {

    //@BindView(R.id.et_type) public TextView mType;
    @BindView(R.id.tv_unidad) public TextView mTipoUnidad;
 //   @BindView(R.id.tv_date) public TextView mDate;
    @BindView(R.id.txt_info_promedio) public TextView mInfoPromedio;
//    @BindView(R.id.tv_mPromedio1) public TextView mEtpromedio1;
//    @BindView(R.id.tv_mPromedio2) public TextView mEtpromedio2;
//    @BindView(R.id.tv_mPromedio3) public TextView mEtpromedio3;
   @BindView(R.id.tv_total) public TextView mEtTotal;

    public Holder_Promedio(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(Entities_Promedio entitiesPromedio, Context mcontext, Adapter_Promedio.OnPromedioClickListener listener) {
        String tetextinfPromedio = String.format(mcontext.getString(R.string.string_info_promedios),entitiesPromedio.getmPromedio()+"",entitiesPromedio.getmPromedio2()+"",
                entitiesPromedio.getmPromedio3()+"",entitiesPromedio.getmFecha()+"");
        mEtTotal.setText(entitiesPromedio.getmTotal()+"");
        mInfoPromedio.setText(tetextinfPromedio);
        switch (entitiesPromedio.getmTipoUnidad()){
            case "I Unidad":
                mTipoUnidad.setVisibility(View.VISIBLE);
                mTipoUnidad.setText(entitiesPromedio.getmTipoUnidad()+"");
                break;
            case "II Unidad":
                mTipoUnidad.setVisibility(View.VISIBLE);
                mTipoUnidad.setText(entitiesPromedio.getmTipoUnidad()+"");
                break;
            case "III Unidad":
                mTipoUnidad.setVisibility(View.VISIBLE);
                mTipoUnidad.setText(entitiesPromedio.getmTipoUnidad()+"");
                break;
            case "Unidad Final":
                mTipoUnidad.setVisibility(View.VISIBLE);
                mTipoUnidad.setText(entitiesPromedio.getmTipoUnidad()+"");
                break;
        }
    }
}
