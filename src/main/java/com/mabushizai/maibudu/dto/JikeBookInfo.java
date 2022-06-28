package com.mabushizai.maibudu.dto;

import com.mabushizai.maibudu.constants.SysStatusEnum;
import com.mabushizai.maibudu.domain.Book;
import com.mabushizai.maibudu.utils.StringUtil;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
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
        Book book = new Book();
        book.setTitle(this.getName());
        book.setSubtitle(this.getSubname());
        book.setAuthor(this.getAuthor());
        if (StringUtils.hasLength(this.getPublished())) {
            String[] parts = this.getPublished().split("-");
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            LocalDate publishDate = LocalDate.of(year, month, 1);
            book.setPublishDate(publishDate);
        }
        book.setPublisher(this.getPublishing());
        book.setIsbn(String.valueOf(this.getId()));
        String summary = this.getDescription();
        if (summary.length() > 1024) {
            summary = summary.substring(0, 1020) + "...";
        }
        book.setSummary(summary);
        if (StringUtils.hasLength(this.getPages())) {
            book.setPages(Integer.parseInt(this.getPages()));
        }
        if (StringUtils.hasLength(this.getPrice())) {
            book.setPrice(new BigDecimal(StringUtil.removeChinese(this.getPrice())));
        }
        book.setBinding(this.getDesigned());
        book.setCover(this.getPhotoUrl());
        book.setSysStatus(SysStatusEnum.NORMAL.getValue());
        return book;
    }

}
