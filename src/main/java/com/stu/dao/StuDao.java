package com.stu.dao;

import com.stu.entity.StuData;

import java.util.List;

/**
 *
 * @author zxg
 * @date 2018/7/16
 */
public interface StuDao {
    /**
     * 学生数据添加
     * @param stuData：学生数据对象
     */
    void add(StuData... stuData);

    List<StuData> stuList(int page, int limit);

    long total();

    StuData stuFind(String id);

    void stuModify(StuData stuData);

    void stuDelete(String id);
}
