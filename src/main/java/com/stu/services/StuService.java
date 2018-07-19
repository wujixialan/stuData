package com.stu.services;

import com.stu.entity.StuData;

import java.util.List;

/**
 * @author kevin zhao
 * @date 2018/7/16
 */
public interface StuService {
    void add(StuData... stuData);

    /**
     * 分页查询数据。
     * @param page
     * @param limit
     * @return
     */
    List<StuData> stuList(int page, int limit);

    long total();

    /**
     * 修改时，查询的学生
     * @param id
     * @return
     */
    StuData stuFind(String id);

    /**
     * 修改用户
     * @param stuData
     */
    void stuModify(StuData stuData);

    /**
     * 删除学生信息
     * @param id
     */
    void stuDelete(String id);
}
