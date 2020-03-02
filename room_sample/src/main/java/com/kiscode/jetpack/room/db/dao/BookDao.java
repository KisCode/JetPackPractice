package com.kiscode.jetpack.room.db.dao;

import com.kiscode.jetpack.room.pojo.Book;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Description: Book 数据库表操作
 * 所有增删查改dao操作必需在子线程进行处理
 * Author: Kris Keno
 * Date : 2020/2/29 9:59 AM
 **/
@Dao
public interface BookDao {
    //------------------  insert  ------------------
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBookList(List<Book> bookList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBookList(Book[] books);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBookList(Book book);


    //------------------  query  ------------------

    /**
     * 根据id查询指定图书
     *
     * @param id
     * @return
     */
    @Query("select * from Book where id==:id limit 1")
    Book getBookById(int id);

    /***
     * 查询全部
     * @return
     */
    @Query("select * from Book")
    List<Book> getAllBookList();

    @Query("select * from Book")
//    Flowable<List<Book>> queryAllBookList();
    Observable<List<Book>> queryAllBookList();

    /**
     * 根据指定名称查询对应数据
     *
     * @param bookName 书名
     * @return
     */
    @Query("select * from Book where name ==:bookName")
    List<Book> getBookListByName(String bookName);


    /**
     * 根据关键字模糊查询
     *
     * @param nameKeyword 书名关键字
     * @return
     */
    @Query("select * from Book where name like:nameKeyword")
    List<Book> getBookListByKeyWord(String nameKeyword);

    /***
     * 查询指定价格区间的数据
     * @param minPricBe 最小价格区间
     * @param maxPrice 最大价格区间
     * @return
     */
    @Query("select * from Book where price between :minPricBe and :maxPrice ")
    List<Book> getBookListByPrice(float minPricBe, float maxPrice);


    @Delete
    void deleteBook(Book book);

    @Delete
    void delteBookList(List<Book> bookList);

}
