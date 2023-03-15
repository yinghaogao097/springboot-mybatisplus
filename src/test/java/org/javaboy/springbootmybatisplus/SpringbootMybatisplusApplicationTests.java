package org.javaboy.springbootmybatisplus;

import org.javaboy.springbootmybatisplus.mapper.UserMapper;
import org.javaboy.springbootmybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringbootMybatisplusApplicationTests {
    @Resource
    private UserMapper userMapper;

    @Test
    public void getById() {
        System.out.println(userMapper.selectById(1));
    }

    @Test
    public void testInsertData() {
        User user =
                User.builder()
                        .userName("条件删除")
                        .name("条件删除")
                        .age(15)
                        .email("itcast22@itcast.cn")
                        .password("222222")
                        .build();
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }

    @Test
    public void testDeleteById() {
//        int i = userMapper.deleteById(18);
//        System.out.println(i);
//        int i = userMapper.deleteBatchIds(Arrays.asList(16, 17));
//        System.out.println(i);
        Map<String, Object> map = new HashMap<>();

//delete from tb_user where user_name = ? and age = ?
        map.put("user_name", "条件删除");
        map.put("age", "15");

        userMapper.deleteByMap(map);
    }


}
