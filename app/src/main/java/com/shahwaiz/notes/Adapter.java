package com.shahwaiz.notes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context context;
    ArrayList<note> arrContact;
    OnNoteListener onNoteListener;
    Adapter (Context context, ArrayList<note> arrContact,OnNoteListener onNoteListener){
        this.context=context;
        this.arrContact=arrContact;
        this.onNoteListener=onNoteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.contact_row,parent,false);
        ViewHolder viewHolder=new ViewHolder(v,onNoteListener);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText("Title: "+arrContact.get(position).Title);
//        holder.desc.setText("Description: "+arrContact.get(position).description);
        String stime=DateFormat.getDateTimeInstance().format(arrContact.get(position).Currenttime);
        holder.time.setText("Time: "+stime);


    }
    @Override
    public int getItemCount() {
        return arrContact.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView title,time;
        OnNoteListener onNoteListener;
        public ViewHolder(@NonNull View itemView,OnNoteListener onNoteListener)
        {
            super(itemView);
            title=itemView.findViewById(R.id.title_contact);

//            desc=itemView.findViewById(R.id.desc);
            time=itemView.findViewById(R.id.time);
            this.onNoteListener=onNoteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}
