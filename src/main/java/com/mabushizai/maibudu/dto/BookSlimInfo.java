package com.mabushizai.maibudu.dto;

import com.mabushizai.maibudu.domain.Book;
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

    private String publisher;

    private String cover;

    public void doSlim(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.subTitle = book.getSubtitle();
        this.author = book.getAuthor();
        this.publisher = book.getPublisher();
        this.cover = book.getCover();
    }

}
