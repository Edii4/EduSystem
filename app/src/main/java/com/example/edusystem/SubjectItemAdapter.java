package com.example.edusystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.content.Context;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SubjectItemAdapter extends RecyclerView.Adapter<SubjectItemAdapter.ViewHolder> implements Filterable {
    private ArrayList<Subject> mSubjectData;
    private ArrayList<Subject> mSubjectDataAll;
    private Context mContext;
    private int lastPosition = -1;

    SubjectItemAdapter(Context context, ArrayList<Subject> itemsData) {
        this.mSubjectData = itemsData;
        this.mSubjectDataAll = itemsData;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_subjects, parent, false));
    }

    @Override
    public void onBindViewHolder(SubjectItemAdapter.ViewHolder holder, int position) {
         Subject currentItem = mSubjectData.get(position);

         holder.bindTo(currentItem);
    }

    @Override
    public int getItemCount() {
        return mSubjectData.size();
    }

    @Override
    public Filter getFilter() {
        return subjectFilter;
    }
    private Filter subjectFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Subject> filteredList = new ArrayList<>();
            FilterResults results = new FilterResults();

            if(constraint == null || constraint.length() == 0) {
                results.count = mSubjectDataAll.size();
                results.values = mSubjectDataAll;
            }
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(Subject item : mSubjectDataAll) {
                    if(item.name.toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }

                results.count = filteredList.size();
                results.values = filteredList;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mSubjectData = (ArrayList) results.values;
            notifyDataSetChanged();
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mNameText;
        private TextView mTypeText;
        private TextView mCreditText;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mNameText = itemView.findViewById(R.id.subjectName);
            mTypeText = itemView.findViewById(R.id.subjectType);
            mCreditText = itemView.findViewById(R.id.subjectCredit);


        }

        public void bindTo(Subject currentItem) {
            mNameText.setText(currentItem.name);
            mTypeText.setText(currentItem.type);
            mCreditText.setText(currentItem.credit);
        }
    };
}

