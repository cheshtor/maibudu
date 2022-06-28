package com.mabushizai.maibudu.dto;

import com.mabushizai.maibudu.domain.Book;
import com.mabushizai.maibudu.domain.BookDetails;
import lombok.Data;

import java.io.Serializable;

/**
 * 书籍简要信息
 *
 * @author Pengyu Gan
 * CreateDate 2022/6/27
 */
@Data
public class BookSlimInfo implements Serializable {

    private Long id;

    private String title;

    private String subTitle;

    private String author;

    private String cover;

    private Byte readStatus;

    public void extract(Book book, Byte readStatus) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.subTitle = book.getSubtitle();
        this.author = book.getAuthor();
        this.cover = book.getCover();
        this.readStatus = readStatus;
    }

    public void doSlim(BookDetails bookDetails) {
        this.id = bookDetails.getId();
        this.title = bookDetails.getTitle();
        this.subTitle = bookDetails.getSubtitle();
        this.author = bookDetails.getAuthor();
        this.cover = bookDetails.getCover();
        this.readStatus = bookDetails.getReadStatus();
    }

}
