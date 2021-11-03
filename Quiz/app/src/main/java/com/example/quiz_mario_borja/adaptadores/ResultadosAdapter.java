package com.example.quiz_mario_borja.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quiz_mario_borja.R;

import java.util.ArrayList;

public class ResultadosAdapter extends RecyclerView.Adapter<ResultadosAdapter.ResultadoViewHolder> {

    ArrayList<Object[]> listaResultados;

    public ResultadosAdapter(ArrayList<Object[]> listaResultados){
        this.listaResultados = listaResultados;
    }

    @NonNull
    @Override
    public ResultadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resultado, null, false);
        return new ResultadoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultadoViewHolder holder, int position) {
        Object aux [] = listaResultados.get(position);
        holder.text_nombre.setText(aux[0].toString());
        holder.text_puntos.setText(aux[1].toString());
    }

    @Override
    public int getItemCount() {
        return listaResultados.size();
    }

    public class ResultadoViewHolder extends RecyclerView.ViewHolder {

        TextView text_nombre, text_puntos;

        public ResultadoViewHolder(@NonNull View itemView) {
            super(itemView);

            text_nombre = itemView.findViewById(R.id.text_name);
            text_puntos = itemView.findViewById(R.id.text_puntos);
        }
    }
}
