package com.example.fussionv2.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fussionv2.R;
import com.example.fussionv2.database.entitas.Topik;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TopikAdapter extends RecyclerView.Adapter<TopikAdapter.TopikViewHolder> implements Filterable {

    private List<Topik> topikList;
    private List<Topik> listFull;

    private Context context;
    private Dialog dialog;


    public interface Dialog {
        void onClick(int position);
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }


    public TopikAdapter(List<Topik> topikList, Context context) {
        this.topikList = topikList;
        this.context = context;
        listFull = new ArrayList<>(topikList);
    }

    @NonNull
    @Override
    public TopikViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_topik, parent, false);
        return new TopikAdapter.TopikViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopikAdapter.TopikViewHolder holder, int position) {
        holder.txt_name_topik.setText(topikList.get(position).namatopik);
        holder.txt_deskripsi.setText(topikList.get(position).deskripsi);
    }

    @Override
    public int getItemCount() {
        return topikList.size();
    }

    @Override
    public Filter getFilter() {
        return FilterTopik;
    }

    private Filter FilterTopik = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            String searchText = charSequence.toString().toLowerCase();
            List<Topik> tempList = new ArrayList<>();
            if (searchText.length() == 0 || searchText.isEmpty()) {
                tempList.addAll(listFull);
            } else {
                for (Topik item:listFull) {
                    if (item.getNamatopik().toLowerCase().contains(searchText)) {
                        tempList.add(item);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = tempList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            topikList.clear();
            topikList.addAll((Collection<? extends Topik>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class TopikViewHolder extends RecyclerView.ViewHolder {

        TextView txt_name_topik, txt_deskripsi;

        public TopikViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name_topik = itemView.findViewById(R.id.txt_name_topik);
            txt_deskripsi = itemView.findViewById(R.id.txt_deskripsi);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dialog != null) {
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }
}
