package com.example.finalexam;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private Context _c;
    private Cursor _cu;
    public RecyclerAdapter(Context c, Cursor cu) {
        this._c = c;
        this._cu = cu;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(_c);
        View v = li.inflate(R.layout.row, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder._tv1.setText("text01");
        holder._tv2.setText("text02");
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView _tv1, _tv2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            _tv1 = itemView.findViewById(R.id.textView);
            _tv2 = itemView.findViewById(R.id.textView2);
        }
        public void bind() {

        }
    }
}
