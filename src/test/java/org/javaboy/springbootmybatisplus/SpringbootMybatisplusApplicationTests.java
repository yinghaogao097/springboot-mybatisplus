package org.javaboy.springbootmybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    public void test1() {
        Page<User> page = new Page<>(2, 3);
        userMapper.findGtIdByPage(page, 3L);
        System.out.println(page.getRecords());

        System.out.println(page.getPages());
        System.out.println(page.getTotal());

    }

    @Test
    public void testLambda() {
        // 创建分页对象
        Page<User> page = new Page<>(1, 3);

        // 创建LambdaQueryWrapper对象
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery(User.class)
                .like(User::getUserName, "伤")
                .eq(User::getPassword, "123456")
                .in(User::getAge, 19, 25, 29)
                .orderByDesc(User::getAge);

        // 执行查询
        userMapper.selectPage(page, lambdaQuery);

        System.out.println("总页数：" + page.getPages());
        System.out.println("总记录数" + page.getTotal());

        // 当前页数据
        page.getRecords().forEach(System.out::println);
    }


    @Test
    public void testOr() {
        // select * from tb_user where user_name = ? or age < ? and name in (?,?)
        QueryWrapper<User> query = Wrappers.query();
        query
                .eq("user_name", "赵敏")
                .or().lt("age", 25)
                // 需要哪个字段可以只查询id
                .select("id");
        userMapper.selectList(query).forEach(System.out::println);
    }


    /**
     * 要求：查询用户中姓名包含"伤"，密码为"123456",且年龄为19或者25或者29，查询结果按照年龄降序排序；
     */
    @Test
    public void testSelectPageQuery() {
//        // 构建查询条件
//        QueryWrapper<User> query = Wrappers.query();
//        query
//                .like("user_name", "伤")
//                .eq("password", "123456")
//                .in("age", 19, 25, 29)
//                .orderByDesc("age");
//        // 构建分页请求条件
//        Page<User> pageReq = new Page<>(1, 5);
//
//        // 执行分页请求 结果会回填到pageReq queryWrapper为null则查询所有数据
//        userMapper.selectPage(pageReq, query);
//
//        System.out.println("总页数：" + pageReq.getPages());
//        System.out.println("总记录数" + pageReq.getTotal());
//
//        // 当前页数据
//        pageReq.getRecords().forEach(System.out::println);

        // 构建查询条件
        QueryWrapper<User> query = Wrappers.query(new User())
                .like("user_name", "伤")
                .eq("password", "123456")
                .in("age", 19, 25, 29)
                .orderByDesc("age");
        userMapper.selectList(query).forEach(System.out::println);

    }

    @Test
    public void testSelectPage() {
        // 当前页码
        int page = 2;
        // 每页数量
        int size = 3;

        // 构建分页请求条件
        Page<User> pageReq = new Page<>(page, size);

        // 执行分页请求 结果会回填到pageReq queryWrapper为null则查询所有数据
        userMapper.selectPage(pageReq, null);

        System.out.println("总页数：" + pageReq.getPages());
        System.out.println("总记录数" + pageReq.getTotal());

        // 当前页数据
        pageReq.getRecords().forEach(System.out::println);
    }

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

    @Test
    public void testUpdateById() {
        User user = User.builder().
                id(2L).
                password("111111").
                build();
        int count = userMapper.updateById(user);
        System.out.println(count);
    }

}
