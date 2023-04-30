package com.example.edusystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class SubjectActivity extends AppCompatActivity {

    private RecyclerView mRecycleView;
    private ArrayList<Subject> mSubjectList;
    private SubjectItemAdapter mAdapter;

    private FirebaseFirestore mFirestore;
    private CollectionReference mItems;

    private FirebaseUser user;

    private int gridNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        mRecycleView = findViewById(R.id.recycleView);
        mRecycleView.setLayoutManager(new GridLayoutManager(this, gridNumber));
        mSubjectList = new ArrayList<>();

        mAdapter = new SubjectItemAdapter(this, mSubjectList);
        mRecycleView.setAdapter(mAdapter);

        mFirestore = FirebaseFirestore.getInstance();
        mItems = mFirestore.collection("Subjects");

        queryData();

    }

    private void queryData() {
        mSubjectList.clear();

        mItems.orderBy("name").get().addOnSuccessListener(queryDocumentSnapshots -> {
            for(QueryDocumentSnapshot document : queryDocumentSnapshots) {
                Subject subject = document.toObject(Subject.class);
                mSubjectList.add(subject);
            }
            if(mSubjectList.size() == 0) {
                initializeData();
                queryData();
            }
            mAdapter.notifyDataSetChanged();
        });

    }

    private void initializeData() {
        String[] subjectsName = getResources().getStringArray(R.array.subject_item_names);
        String[] subjectsType = getResources().getStringArray(R.array.subject_item_types);
        String[] subjectsCredit = getResources().getStringArray(R.array.subject_item_credit);


        for (int i = 0; i < subjectsName.length; i++) {
            mItems.add(new Subject(subjectsName[i], subjectsType[i], subjectsCredit[i]));

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}