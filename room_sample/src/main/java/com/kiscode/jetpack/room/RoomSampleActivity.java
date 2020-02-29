package com.kiscode.jetpack.room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.kiscode.jetpack.room.db.AppDatabase;
import com.kiscode.jetpack.room.pojo.Author;
import com.kiscode.jetpack.room.pojo.Book;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
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
        mDataBase = ((RoomSampleApp) getApplication()).getRoomDatabase();
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
                insertBookList();
                break;
            case R.id.btn_query_all_book:
                queryAllBookList();
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

                    Author author =new Author();
                    author.setId(i);
                    author.setName("Author Name with "+i);
                    book.setAuthor(author);

                    bookList.add(book);
                }
                mDataBase.bookDao().insertBookList(bookList);
            }
        }).start();
    }
}
