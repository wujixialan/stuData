import com.google.gson.Gson;
import com.stu.entity.StuData;
import com.stu.utils.SerializeUtil;
import redis.clients.jedis.Jedis;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by zxg on 2018/7/16.
 */
public class TestJedis {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("123.207.55.72");
        int page = 2;
        int limit = 2;
        page = (page - 1) * limit;
        Set<String> id = jedis.zrange("id", page, limit);
        System.out.println(id.size());

    }
}
