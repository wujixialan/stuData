package com.stu.services.impl;

import com.stu.dao.StuDao;
import com.stu.dao.impl.StuDaoImpl;
import com.stu.entity.StuData;
import com.stu.services.StuService;

import java.util.List;

/**
 * @author kevin zhao
 * @date 2018/7/16
 */
public class StuServiceImpl implements StuService {
    private StuDao stuDao = new StuDaoImpl();
    @Override
    public void add(StuData... stuData) {
        stuDao.add(stuData);
    }

    /**
     * 分页查询数据。
     *
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<StuData> stuList(int page, int limit) {
        return stuDao.stuList(page, limit);
    }

    @Override
    public long total() {
        return stuDao.total();
    }

    /**
     * 修改时，查询的学生
     *
     * @param id
     * @return
     */
    @Override
    public StuData stuFind(String id) {
        return stuDao.stuFind(id);
    }

    /**
     * 修改用户
     *
     * @param stuData
     */
    @Override
    public void stuModify(StuData stuData) {
        stuDao.stuModify(stuData);
    }

    /**
     * 删除学生信息
     *
     * @param id
     */
    @Override
    public void stuDelete(String id) {
        stuDao.stuDelete(id);
    }
}
