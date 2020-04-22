package com.example.digitaludhaarkhata;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;
import java.util.Random;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.LanguageViewHolder>
{
    private List<CustomerListInfo> customerListInfoList;

    public CustomerListAdapter(List<CustomerListInfo> customerListInfoList)
    {
        this.customerListInfoList = customerListInfoList;
    }


    @NonNull
    @Override
    public LanguageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.customer_list,parent,false);
        return new LanguageViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final LanguageViewHolder holder, int position)
    {
        CustomerListInfo customerListInfo = customerListInfoList.get(position);
        holder.textViewIcon.setText(customerListInfo.getCustomerName().substring(0,1));
        holder.textViewName.setText(customerListInfo.getCustomerName());

//        holder.materialCardViewSelectLanguage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                holder.materialCardViewSelectLanguage.toggle();
//            }
//        });

        Random mRandom = new Random();
        int color = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));

        GradientDrawable gradientDrawable = (GradientDrawable) holder.textViewIcon.getBackground().getCurrent();
        gradientDrawable.setColor(color);


    }

    @Override
    public int getItemCount()
    {

        Log.d("sizeidfhkdjfkdjf", "getItemCount: "+customerListInfoList.size());
        return customerListInfoList.size();
    }


    //create variable

    private OnItemClickListener mListener;

    //interface

    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener = listener;
    }

    public static class LanguageViewHolder extends RecyclerView.ViewHolder
    {
        MaterialCardView materialCardViewContact;
        TextView textViewIcon;
        TextView textViewName;
        TextView textViewMoney;
        TextView textViewMoneyMessage;

        public LanguageViewHolder(@NonNull View itemView, final OnItemClickListener listener)
        {
            super(itemView);

            materialCardViewContact = itemView.findViewById(R.id.idCardViewSelectCustomerCustomerList);
            textViewIcon = itemView.findViewById(R.id.idTextViewIconCustomerList);
            textViewName = itemView.findViewById(R.id.idTextViewCustomerNameCustomerList);
//            textViewMoney = itemView.findViewById(R.id.idTextViewCustomerMoneyCustomerList);
//            textViewMoneyMessage = itemView.findViewById(R.id.idTextViewCustomerMoneyMessageCustomerList);


            materialCardViewContact.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if(listener != null)
                    {
                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION)
                        {
                            listener.onItemClick(position);
                        }
                    }
                }
            });


        }
    }
}
