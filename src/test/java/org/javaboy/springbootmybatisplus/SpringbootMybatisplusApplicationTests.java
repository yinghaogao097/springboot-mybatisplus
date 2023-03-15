package org.javaboy.springbootmybatisplus;

import org.javaboy.springbootmybatisplus.mapper.UserMapper;
import org.javaboy.springbootmybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;

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
                        .userName("itheima22")
                        .name("itcast22")
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
        int i = userMapper.deleteBatchIds(Arrays.asList(16, 17));
        System.out.println(i);
    }


}
