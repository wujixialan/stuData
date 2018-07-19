package com.stu.servlet;

import com.google.gson.Gson;
import com.stu.entity.StuData;
import com.stu.services.StuService;
import com.stu.services.impl.StuServiceImpl;
import com.stu.utils.FunctionName;
import com.stu.utils.Layui;
import com.stu.utils.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zxg on 2018/7/16.
 */
public class StuServlet extends HttpServlet {
    private StuService stuService = new StuServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method.trim().equals(FunctionName.add)) {
            stuAdd(req, resp);
        } else if (method.trim().equals(FunctionName.find)) {
            stuList(req, resp);
        } else if (method.trim().equals(FunctionName.edit)) {
            stuFind(req, resp);
        } else if (method.trim().equals(FunctionName.modify)) {
            stuModify(req, resp);
        } else if (method.trim().equals(FunctionName.delete)) {
            stuDelete(req, resp);
        }
    }


    private void stuDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        stuService.stuDelete(id);
        Map<Object, Object> map = new HashMap<>();
        Gson gson = new Gson();
        try {
            map.put("code", 200);
            map.put("msg", "删除成功");
        } catch (Exception e) {
            map.put("code", 400);
            map.put("msg", "删除失败");
        }
        resp.setContentType("application/json");//设置response响应类型
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        out.write(gson.toJson(map));
        out.flush();
        out.close();
    }

    private void stuModify(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String birthday = req.getParameter("birthday");
        LocalDate birth = format(birthday, "yyyy-MM-dd");
        String description = req.getParameter("description");
        Integer avgScore = Integer.valueOf(req.getParameter("avgScore"));
        StuData stuData = new StuData(id, name, birth, description, avgScore);
        Map<Object, Object> map = new HashMap<>();
        Gson gson = new Gson();
        try {
            stuService.stuModify(stuData);
            map.put("code", 200);
            map.put("msg", "修改成功");
        } catch (Exception e) {
            map.put("code", 400);
            map.put("msg", "修改失败");
        }
        resp.setContentType("application/json");//设置response响应类型
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        out.write(gson.toJson(map));
        out.flush();
        out.close();
    }

    private void stuFind(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        StuData stuData = stuService.stuFind(id);
        req.setAttribute("stuData", stuData);
        try {
            req.getRequestDispatcher("/modify.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stuList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String limitStr = req.getParameter("limit");
        String pageStr = req.getParameter("page");
        int limit = 0;
        int page = 0;
        try {
            limit = Integer.parseInt(limitStr);
            page = Integer.parseInt(pageStr);
        } catch (NumberFormatException e) {
            limit = 10;
            page = 1;
        }
        List<StuData> stuData = stuService.stuList(page, limit);
        long total = stuService.total();
        Page page1 = new Page();
        page1.setFirst(1);
        page1.setPre(page - 1);
        page1.setCurr(page);
        page1.setNext(page + 1);
        System.out.println((int) total / limit);
        int last = (int) total / limit + 1;
        System.out.println(last);
        page1.setLast(last);
        req.setAttribute("stu", stuData);
        req.setAttribute("total", total);
        req.setAttribute("page1", page1);
        try {
            req.getRequestDispatcher("/list.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param req：请求参数
     * @param resp：响应参数
     */
    private void stuAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = UUID.randomUUID().toString().replace("-", "");
        String name = req.getParameter("name");
        String birthday = req.getParameter("birthday");
        LocalDate birth = format(birthday, "yyyy-MM-dd");
        String description = req.getParameter("description");
        Integer avgScore = Integer.valueOf(req.getParameter("avgScore"));
        StuData stuData = new StuData(id, name, birth, description, avgScore);
        Map<Object, Object> map = new HashMap<>();
        Gson gson = new Gson();
        try {
            stuService.add(stuData);
            map.put("code", 200);
            map.put("msg", "添加成功");
        } catch (Exception e) {
            map.put("code", 400);
            map.put("msg", "添加失败");
        }
        resp.setContentType("application/json");//设置response响应类型
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        out.write(gson.toJson(map));
        out.flush();
        out.close();
    }

    private LocalDate format(String birthday, String pattern) {
        return LocalDate.parse(birthday, DateTimeFormatter.ofPattern(pattern));
    }
}
