package com.example.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AnswersAdapter extends RecyclerView.Adapter<AnswersAdapter.ViewHolder> {

    Answers[] answers;
    Context context;

    public AnswersAdapter(Answers[] answers, MainActivity2 activity) {

        this.answers = answers;
        this.context = activity;
    }
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.answersform, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Answers answers_list = answers[position];
        holder.textViewMessage.setText(answers_list.getAnswer());
        holder.textViewName.setText(answers_list.getName());
        holder.textViewTime.setText(answers_list.getTime());
    }

    @Override
    public int getItemCount() {
        return answers.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewName;
        TextView textViewMessage;
        TextView textViewTime;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTime= itemView.findViewById(R.id.time);
            textViewName = itemView.findViewById(R.id.name);
            textViewMessage = itemView.findViewById(R.id.answer);

        }
    }








}