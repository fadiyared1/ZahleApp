package com.example.menu;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ViewHolder> {

    Questions[] questions;
    Context context;

    public QuestionsAdapter(Questions[] questions, Message activity) {

        this.questions = questions;
        this.context = activity;


    }


    @Override


    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.questionsform, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Questions questions_list = questions[position];
        holder.textViewQuestion.setText(questions_list.getQuestion());
        holder.textViewName.setText(questions_list.getName());
        holder.textViewTime.setText(questions_list.getTime());


    }

    @Override
    public int getItemCount() {
        return questions.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewQuestion;
        TextView textViewTime;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTime = itemView.findViewById(R.id.time);
            textViewName = itemView.findViewById(R.id.name);
            textViewQuestion = itemView.findViewById(R.id.question);
        }


    }








}





