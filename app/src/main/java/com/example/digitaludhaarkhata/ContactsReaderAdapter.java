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

public class ContactsReaderAdapter extends RecyclerView.Adapter<ContactsReaderAdapter.LanguageViewHolder>
{
    private List<ContactsReaderInfo> contactsReaderInfoList;

    public ContactsReaderAdapter(List<ContactsReaderInfo> contactsReaderInfoList)
    {
        this.contactsReaderInfoList = contactsReaderInfoList;
    }


    @NonNull
    @Override
    public LanguageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.contact_reader,parent,false);
        return new LanguageViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final LanguageViewHolder holder, int position)
    {
        ContactsReaderInfo contactsReaderInfo = contactsReaderInfoList.get(position);
        holder.textViewIcon.setText(contactsReaderInfo.getName().substring(0,1));
        holder.textViewName.setText(contactsReaderInfo.getName());
        holder.textViewPhone.setText(contactsReaderInfo.getPhone());

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
    public int getItemCount() {
        return contactsReaderInfoList.size();
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
        TextView textViewPhone;

        public LanguageViewHolder(@NonNull View itemView, final OnItemClickListener listener)
        {
            super(itemView);

            materialCardViewContact = itemView.findViewById(R.id.idCardViewContactReader);
            textViewIcon = itemView.findViewById(R.id.idTextViewIconContactReader);
            textViewName = itemView.findViewById(R.id.idTextViewContactNameContactReader);
            textViewPhone = itemView.findViewById(R.id.idTextViewContactPhoneContactReader);


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
