package com.kiscode.jetpack.room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.kiscode.jetpack.room.db.AppDatabase;
import com.kiscode.jetpack.room.pojo.Author;
import com.kiscode.jetpack.room.pojo.Book;
import com.kiscode.jetpack.room.pojo.ClassRoom;
import com.kiscode.jetpack.room.pojo.Student;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableOperator;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RoomSampleActivity extends AppCompatActivity implements View.OnClickListener {
    private AppDatabase mDataBase;

    private Button btnInitDatabase;
    private Button btnQueryAllBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_sample);
        initData();
        initView();
    }

    private void initData() {
        mDataBase = AppDatabase.getInstance();
    }

    private void initView() {
        btnInitDatabase = (Button) findViewById(R.id.btn_init_database);
        btnInitDatabase.setOnClickListener(this);
        btnQueryAllBook = (Button) findViewById(R.id.btn_query_all_book);
        btnQueryAllBook.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_init_database:
//                insertBookList();
                insertClassRoomList();
                break;
            case R.id.btn_query_all_book:
//                queryAllBookList();
                queryAllClassRoomList();
                break;
        }
    }

    private void queryAllBookList() {
        mDataBase.bookDao().queryAllBookList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Book>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Book> books) {
                        for (Book book : books) {
                            Log.i("allBookList", book.toString());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void insertBookList() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                //不允许在UI线程使用
                Random random = new Random();
                List<Book> bookList = new ArrayList<>();
                for (int i = 0; i < 100; i++) {
                    Book book = new Book();
                    book.setId(i);
                    book.setName("Book Name with " + i);
                    book.setPrice(100 * random.nextFloat());
                    book.setPubulisTime(new Date());

                    Author author = new Author();
                    author.setId(i);
                    author.setName("Author Name with " + i);
                    book.setAuthor(author);

                    bookList.add(book);
                }
                mDataBase.bookDao().insertBookList(bookList);
            }
        }).start();
    }

    private void insertClassRoomList() {


        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<ClassRoom> classRoomArrayList = new ArrayList<>();
                for (int i = 0; i < 15; i++) {
                    ClassRoom classRoom = new ClassRoom();
                    classRoom.setNumber(i + 1);
                    classRoom.setName("v1.ClassRoom Name:" + i);
//                    classRoom.setCount(30);

                    List<Student> studentList = new ArrayList<>();
                    for (int j = 1; j < 50; j++) {
                        Student student = new Student();
                        student.setName("Student " + j);
                        studentList.add(student);
                    }

//            classRoom.setStudentList(studentList);

                    classRoomArrayList.add(classRoom);
                }

                mDataBase.classRoomDao().insertClassRoomList(classRoomArrayList);
            }
        }).start();
    }


    private void queryAllClassRoomList() {
        mDataBase.classRoomDao()
                .queryAllClassRoomList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ClassRoom>>() {
                    @Override
                    public void accept(List<ClassRoom> classRoomList) throws Exception {
                        for (ClassRoom classRoom : classRoomList) {
                            Log.i("classRoom", classRoom.toString());
                        }
                    }
                });
    }
}
