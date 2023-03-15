package org.javaboy.springbootmybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.springbootmybatisplus.pojo.User;

/**
 * @Author：Achen
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 查询大于指定id的用户信息，并分页查询实现
     * @param page
     * @param id
     * @return
     */
    IPage<User> findGtIdByPage(IPage<User> page, @Param("id") Long id);
}
