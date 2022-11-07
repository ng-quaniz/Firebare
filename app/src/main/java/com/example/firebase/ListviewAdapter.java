package com.example.firebase;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;




import java.io.Serializable;
import java.util.ArrayList;

public class ListviewAdapter extends RecyclerView.Adapter<ListviewAdapter.MyViewHolder> {
    Context context;
    ArrayList<Sinhvien> list;
    boolean kt = false;




    public ListviewAdapter(Context context, ArrayList<Sinhvien> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.listview,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        Sinhvien sv = list.get(position);
        holder.tv_name.setText(sv.getName());
        holder.tv_mail.setText(sv.getEmail());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Detail.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("", (Serializable) sv);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                kt = true;
                AlertDialog.Builder alertDiaLog = new AlertDialog.Builder(context);
                alertDiaLog.setTitle("Are you delete?");
                alertDiaLog.setIcon(R.mipmap.ic_launcher);
                alertDiaLog.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "Delate Success..", Toast.LENGTH_SHORT).show();

                    }
                });
                alertDiaLog.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                });
                alertDiaLog.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_mail;
        private  RelativeLayout layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout);
            tv_name = itemView.findViewById(R.id.tvname);
            tv_mail = itemView.findViewById(R.id.tvmail);
        }
    }
}
