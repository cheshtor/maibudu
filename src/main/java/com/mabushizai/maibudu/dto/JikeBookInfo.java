package com.mabushizai.maibudu.dto;

import com.mabushizai.maibudu.config.MaibuduException;
import com.mabushizai.maibudu.domain.Book;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 极客 免费 API 返回的书籍信息
 * https://jike.xyz/jiekou/isbn.html
 *
 * @author Pengyu Gan
 * CreateDate 2022/6/21
 */
@Data
public class JikeBookInfo implements Serializable {

    private long id;

    private String name;

    private String subname;

    private String author;

    private String translator;

    private String publishing;

    private String published;

    private String designed;

    private String code;

    private long douban;

    private int doubanScore;

    private String brand;
    private String weight;

    private String size;

    private String pages;

    private String photoUrl;

    private String localPhotoUrl;

    private String price;

    private String froms;

    private int num;

    private Date createTime;

    private Date uptime;

    private String authorIntro;

    private String description;

    public Book convert() {
        try {
            Book book = new Book();
            book.setDoubanId(String.valueOf(this.getDouban()));
            book.setTitle(this.getName());
            book.setSubtitle(this.getSubname());
            book.setAuthor(this.getAuthor());
            book.setPublishDate(this.getPublished());
            book.setPublisher(this.getPublishing());
            book.setIsbn(String.valueOf(this.getId()));
            String summary = this.getDescription();
            if (summary.length() > 65535) {
                summary = summary.substring(0, 65530) + "...";
            }
            book.setSummary(summary);
            book.setPages(this.getPages());
            book.setPrice(this.getPrice());
            book.setBinding(this.getDesigned());
            book.setCover(this.getPhotoUrl());
            book.setScore(String.valueOf(this.getDoubanScore()));
            return book;
        } catch (Throwable e) {
            throw new MaibuduException("分析书籍信息失败");
        }
    }

}
