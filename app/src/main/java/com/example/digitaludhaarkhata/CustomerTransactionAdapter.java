package com.example.digitaludhaarkhata;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;
import java.util.Random;

public class CustomerTransactionAdapter extends RecyclerView.Adapter<CustomerTransactionAdapter.LanguageViewHolder>
{
    private List<CustomerTransactionInfo> customerTransactionInfoList;

    public CustomerTransactionAdapter(List<CustomerTransactionInfo> customerTransactionInfoList)
    {
        this.customerTransactionInfoList = customerTransactionInfoList;
    }


    @NonNull
    @Override
    public LanguageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.customer_transactions,parent,false);
        return new LanguageViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final LanguageViewHolder holder, int position)
    {
        CustomerTransactionInfo customerTransactionInfo = customerTransactionInfoList.get(position);

        holder.textViewDate.setText(customerTransactionInfo.getDate());
        holder.textViewYouGave.setText(customerTransactionInfo.getUserGave());
        holder.textViewYouGot.setText(customerTransactionInfo.getUserGot());

//        holder.materialCardViewSelectLanguage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                holder.materialCardViewSelectLanguage.toggle();
//            }
//        });

//        Random mRandom = new Random();
//        int color = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
//
//        GradientDrawable gradientDrawable = (GradientDrawable) holder.textViewIcon.getBackground().getCurrent();
//        gradientDrawable.setColor(color);

    }

    @Override
    public int getItemCount() {
        return customerTransactionInfoList.size();
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
        TextView textViewDate;
        TextView textViewYouGave;
        TextView textViewYouGot;

        public LanguageViewHolder(@NonNull View itemView, final OnItemClickListener listener)
        {
            super(itemView);

            materialCardViewContact = itemView.findViewById(R.id.idCardViewCustomerTransaction);
            textViewDate = itemView.findViewById(R.id.idTextViewDateCustomerTransaction);
            textViewYouGave = itemView.findViewById(R.id.idTextViewYouGaveCustomerTransaction);
            textViewYouGot = itemView.findViewById(R.id.idTextViewYouGotCustomerTransaction);
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
