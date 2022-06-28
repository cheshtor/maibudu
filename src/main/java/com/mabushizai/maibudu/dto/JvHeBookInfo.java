package com.mabushizai.maibudu.dto;

import com.mabushizai.maibudu.constants.SysStatusEnum;
import com.mabushizai.maibudu.domain.Book;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 聚合数据 API 返回的图书信息
 *
 * @author Pengyu Gan
 * CreateDate 2022/6/21
 */
@Data
public class JvHeBookInfo implements Serializable {

    private String levelNum;

    private String subtitle;

    private String author;

    private String pubdate;

    private String origin_title;

    private String binding;

    private String pages;

    private String images_medium;

    private String images_large;

    private String publisher;

    private String isbn10;

    private String isbn13;

    private String title;

    private String summary;

    private String price;

    public Book convert() {
        Book book = new Book();
        book.setTitle(this.getTitle());
        book.setSubtitle(this.getSubtitle());
        book.setAuthor(this.getAuthor());
        if (StringUtils.hasLength(this.getPubdate())) {
            String[] parts = this.getPubdate().split("-");
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            LocalDate publishDate = LocalDate.of(year, month, 1);
            book.setPublishDate(publishDate);
        }
        book.setPublisher(this.getPublisher());
        book.setIsbn(StringUtils.hasLength(this.getIsbn13()) ? this.getIsbn13() : this.getIsbn10());
        book.setSummary(this.getSummary());
        if (StringUtils.hasLength(this.getPages())) {
            book.setPages(Integer.parseInt(this.getPages()));
        }
        if (StringUtils.hasLength(this.getPrice())) {
            book.setPrice(new BigDecimal(this.getPrice()));
        }
        book.setBinding(this.getBinding());
        book.setCover(this.getImages_large());
        book.setSysStatus(SysStatusEnum.NORMAL.getValue());
        return book;
    }

}
