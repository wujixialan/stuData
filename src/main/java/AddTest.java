import com.google.gson.Gson;
import com.stu.entity.StuData;
import redis.clients.jedis.Jedis;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

/**
 * 用来创建测试数据的
 */
public class AddTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("123.207.55.72");
        StuData student = new StuData();
        Gson gson = new Gson();
        for (int i =1 ;i<100;i++){
            //设置id为UUID
            String id = UUID.randomUUID().toString().replace("-", "").toLowerCase();
            student.setId(id);
            int i1 = new Random().nextInt(400);
            student.setName("xxxxx"+i1/2);
            student.setBirthday(LocalDate.now());
            student.setDescription("tttt"+i*i*i);
            student.setAvgScore(i1);
            jedis.zadd("id", student.getAvgScore(), gson.toJson(student));
        }
    }
}
