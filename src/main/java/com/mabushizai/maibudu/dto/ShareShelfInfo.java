package com.mabushizai.maibudu.dto;

import com.mabushizai.maibudu.domain.ShareShelf;
import lombok.Data;

/**
 * @author Gan Pengyu
 * CreateDate 2022/7/5
 */
@Data
public class ShareShelfInfo extends ShareShelf {

    private String nickname;

    private String avatar;

    private String code;

}
