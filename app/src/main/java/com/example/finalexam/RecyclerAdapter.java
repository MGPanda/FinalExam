package com.example.finalexam;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private Context _c;
    private Cursor _cu;
    private ArrayList<Content> _al;
    public RecyclerAdapter(Context c, Cursor cu) {
        this._c = c;
        this._cu = cu;
        do {
            _al.add(new Content(A,B,C,D));
        } while (_cu.moveToNext());
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
        holder.bind(_al.get(position));
    }

    @Override
    public int getItemCount() {
        return _al.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView _tv1, _tv2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            _tv1 = itemView.findViewById(R.id.textView);
            _tv2 = itemView.findViewById(R.id.textView2);
        }
        public void bind(Content c) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(_c, Detail.class);
                    _c.startActivity(i);
                }
            });
        }
    }
}
