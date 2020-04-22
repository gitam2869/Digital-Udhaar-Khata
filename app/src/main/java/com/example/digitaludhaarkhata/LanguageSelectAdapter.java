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

import org.w3c.dom.Text;

import java.util.List;
import java.util.Random;

public class LanguageSelectAdapter extends RecyclerView.Adapter<LanguageSelectAdapter.LanguageViewHolder>
{
    private List<LanguageSelectInfo> languageSelectInfoList;

    public LanguageSelectAdapter(List<LanguageSelectInfo> languageSelectInfoList)
    {
        this.languageSelectInfoList = languageSelectInfoList;
    }


    @NonNull
    @Override
    public LanguageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.select_language,parent,false);
        return new LanguageViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final LanguageViewHolder holder, int position)
    {
        LanguageSelectInfo languageSelectInfo = languageSelectInfoList.get(position);
        holder.textViewIcon.setText(languageSelectInfo.getLanguageName().substring(0,1));
        holder.textViewLanguage.setText(languageSelectInfo.getLanguageName());
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
        return languageSelectInfoList.size();
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
        MaterialCardView materialCardViewSelectLanguage;
        TextView textViewIcon;
        TextView textViewLanguage;

        public LanguageViewHolder(@NonNull View itemView, final OnItemClickListener listener)
        {
            super(itemView);

            materialCardViewSelectLanguage = itemView.findViewById(R.id.idCardViewSelectLanguageSelectLanguage);
            textViewIcon = itemView.findViewById(R.id.idTextViewIconSelectLanguage);
            textViewLanguage = itemView.findViewById(R.id.idTextViewLanguageNameSelectLanguage);


            materialCardViewSelectLanguage.setOnClickListener(new View.OnClickListener()
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
