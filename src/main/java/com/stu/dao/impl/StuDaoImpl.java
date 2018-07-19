package com.stu.dao.impl;

import com.google.gson.Gson;
import com.stu.dao.StuDao;
import com.stu.entity.StuData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author zxg
 * @date 2018/7/16
 */
public class StuDaoImpl extends BaseDaoImpl implements StuDao {
    /**
     * 学生数据添加
     *
     * @param stuData：学生数据对象
     */
    @Override
    public void add(StuData... stuData) {
        Gson gson = new Gson();
        Arrays.stream(stuData).forEach(ele -> {
            getJedis().zadd("id", ele.getAvgScore(), gson.toJson(ele));
        });
    }

    @Override
    public List<StuData> stuList(int page, int limit) {
        List<StuData> stuDataList = new ArrayList<>();
        Gson gson = new Gson();
        Set<String> id = getJedis().zrange("id", (page - 1) * limit, (page - 1) * limit + 9);
        id.stream().forEach(ele -> {
            stuDataList.add(gson.fromJson(ele, StuData.class));
        });
        return stuDataList;
    }

    @Override
    public long total() {
        return getJedis().zrange("id", 0, -1).size();
    }

    @Override
    public StuData stuFind(String id) {
        Set<String> strings = getJedis().zrange("id", 0, -1);
        Gson gson = new Gson();
        AtomicReference<StuData> stuData = new AtomicReference<>(new StuData());
        strings.stream().forEach(ele -> {
            StuData stu = gson.fromJson(ele, StuData.class);
            if (stu.getId().trim().equals(id)) {
                stuData.set(stu);
                return;
            }
        });

        return stuData.get();
    }

    @Override
    public void stuModify(StuData stuData) {
        Set<String> strings = getJedis().zrange("id", 0, -1);
        System.out.println(strings.size());
        Gson gson = new Gson();
        List<StuData> list = new ArrayList<>();
        AtomicReference<StuData> delStu = new AtomicReference<>(new StuData());
        strings.stream().forEach(ele -> {
            StuData stu = gson.fromJson(ele, StuData.class);
            if (stu.getId().equals(stuData.getId())) {
                delStu.set(stu);
            }
        });
        getJedis().zrem("id", gson.toJson(delStu.get()));
        getJedis().zadd("id", stuData.getAvgScore(), gson.toJson(stuData));
    }

    @Override
    public void stuDelete(String id) {
        Set<String> strings = getJedis().zrange("id", 0, -1);
        Gson gson = new Gson();
        AtomicReference<StuData> delStu = new AtomicReference<>(new StuData());
        strings.stream().forEach(ele -> {
            StuData stu = gson.fromJson(ele, StuData.class);
            if (stu.getId().trim().equals(id)) {
                delStu.set(stu);
            }
        });
        getJedis().zrem("id", gson.toJson(delStu.get()));
    }
}
