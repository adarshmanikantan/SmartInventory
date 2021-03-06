package com.adarsh.smartinventory.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adarsh.smartinventory.FirstCalculationActivity;
import com.adarsh.smartinventory.Model.ViewCustomerModel;
import com.adarsh.smartinventory.R;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.MyViewHolder> {
    Context context;
    ViewCustomerModel viewCustomerModel;
    public CustomerAdapter(Context context,ViewCustomerModel viewCustomerModel)
    {
        this.context=context;
        this.viewCustomerModel=viewCustomerModel;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_row,parent,false);
        CustomerAdapter.MyViewHolder holder=new CustomerAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.name.setText(viewCustomerModel.getDetails().getResults().get(position).getName());
        holder.code.setText(viewCustomerModel.getDetails().getResults().get(position).getCustomer_code());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=context.getSharedPreferences("customer",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("customer_name",viewCustomerModel.getDetails().getResults().get(position).getName());
                editor.putString("customer_code",viewCustomerModel.getDetails().getResults().get(position).getCustomer_code());
                editor.apply();
                Intent i=new Intent(context, FirstCalculationActivity.class);
                holder.itemView.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return viewCustomerModel.getDetails().getResults().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,code;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.customer_name);
            code=itemView.findViewById(R.id.customer_code);

        }
    }
}
