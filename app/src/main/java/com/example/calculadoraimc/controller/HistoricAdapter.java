package com.example.calculadoraimc.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calculadoraimc.R;

import java.util.ArrayList;

public class HistoricAdapter extends RecyclerView.Adapter<HistoricAdapter.ViewHolder> {
    private Context context;
    private ArrayList imc_id, date_id;

    public HistoricAdapter(Context context, ArrayList imc_id, ArrayList date_id) {
        this.context = context;
        this.imc_id = imc_id;
        this.date_id = date_id;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.historic_recyclerview, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imc_id.setText(String.valueOf(imc_id.get(position)));
        holder.date_id.setText(String.valueOf(date_id.get(position)));
    }

    @Override
    public int getItemCount() {
        return imc_id.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView imc_id, date_id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imc_id = itemView.findViewById(R.id.imcView);
            date_id = itemView.findViewById(R.id.dateView);
        }
    }
}
