package com.kiscode.paging;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kiscode.paging.adapter.StudentAdapter;
import com.kiscode.paging.model.db.AppDatabase;
import com.kiscode.paging.model.db.StudentDao;
import com.kiscode.paging.model.pojo.Student;

public class RoomLoadActivity extends BaseActionBarActivity {
    private static final String TAG = "LocalDataActivity";
    private RecyclerView recyclerView;
    private Button btnPopulate, btnClear;
    StudentDao studentDao;
    StudentAdapter adapter;
    LiveData<PagedList<Student>> allStudentLivePaged;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_data);

        getSupportActionBar().setTitle(R.string.title_room_paging);

        recyclerView = findViewById(R.id.recyclerView);

        adapter = new StudentAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        studentDao = AppDatabase.getInstance(this).getStudentDao();
        allStudentLivePaged = new LivePagedListBuilder<>(studentDao.getAllStudentList(), 10)
                .build();

        allStudentLivePaged.observe(this, new Observer<PagedList<Student>>() {
            @Override
            public void onChanged(PagedList<Student> students) {
                adapter.submitList(students);

                students.addWeakCallback(null, new PagedList.Callback() {
                    @Override
                    public void onChanged(int position, int count) {
                        Log.i(TAG, "onChanged" + students + "\tcount:" + count);
                    }

                    @Override
                    public void onInserted(int position, int count) {
                        Log.i(TAG, "onInserted position:" + position + "\tcount:" + count);

                    }

                    @Override
                    public void onRemoved(int position, int count) {
                        Log.i(TAG, "onRemoved position:" + position + "\tcount:" + count);
                    }
                });
            }
        });

        btnPopulate = findViewById(R.id.btnPopulate);
        btnClear = findViewById(R.id.btnClear);
        btnPopulate.setOnClickListener(v -> {
            //插入全部数据
            Student[] students = new Student[1000];
            for (int i = 0; i < 1000; i++) {
                Student student = new Student();
                student.setNumber(i);
                students[i] = student;
            }
            new InsertAsynTask(studentDao).execute(students);
        });

        btnClear.setOnClickListener(v -> {
            //删除全部数据
            new ClearAsyncTask(studentDao).execute();
        });
    }

    static class InsertAsynTask extends AsyncTask<Student, Void, Void> {
        StudentDao studentDao;

        public InsertAsynTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.insertStudentList(students);
            return null;
        }
    }

    static class ClearAsyncTask extends AsyncTask<Void, Void, Void> {
        StudentDao studentDao;

        public ClearAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            studentDao.deleteAllStudent();
            return null;
        }
    }
}