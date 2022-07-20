package com.mabushizai.maibudu.test;

import com.mabushizai.maibudu.MaibuduApplication;
import com.mabushizai.maibudu.domain.BookCompleteInfo;
import com.mabushizai.maibudu.dto.BookVO;
import com.mabushizai.maibudu.dto.Page;
import com.mabushizai.maibudu.dto.PageModel;
import com.mabushizai.maibudu.service.ShelfBookService;
import com.mabushizai.maibudu.utils.UserContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gan Pengyu
 * CreateDate 2022/7/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MaibuduApplication.class)
public class ShelfBookServiceTest {

    @Resource
    private ShelfBookService shelfBookService;

    @Test
    public void testList() {
        UserContext.initForTest();
        PageModel pageModel = new PageModel(1L, 10);
        Page<BookCompleteInfo> page = shelfBookService.list(pageModel, null, null);

        List<BookVO> vs = new ArrayList<>();
        for (BookCompleteInfo row : page.getRows()) {
            BookVO vo = new BookVO();
            BeanUtils.copyProperties(row, vo);
            vs.add(vo);
        }
        PageModel model = new PageModel(page.getPageNo(), page.getPageSize());
        model.setTotalCount(page.getTotalCount());
        model.setTotalPages(page.getTotalPages());
        Page<BookVO> res = new Page<>(model, vs);
        List<BookVO> end = res.getRows();

        Assert.assertEquals(10, end.size());
    }

}
