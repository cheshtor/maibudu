package com.mabushizai.maibudu.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Pengyu Gan
 * CreateDate 2022/6/27
 */
@Data
public class BookDetails extends Book {

    private Byte readStatus;

    private LocalDateTime createDate;

}
