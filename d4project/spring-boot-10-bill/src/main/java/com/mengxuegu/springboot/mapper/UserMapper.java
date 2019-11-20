package com.mengxuegu.springboot.mapper;

import com.mengxuegu.springboot.common.Pager;
import com.mengxuegu.springboot.entities.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: 梦学谷
 */
//@Mapper 或 @MapperScan("com.mengxuegu.springboot.mapper")
public interface UserMapper {

    User getUserByUsername(String username);

    List<User> getUsers(@Param("entity") Pager<User> pager);

    User getUserById(Integer id);

    int addUser(User user);

    int deleteUserById(Integer id);

    int updateUser(User user);

    void addFivePoint(int userId);

    void addOnePoint(int id);

    List<Integer> getCreateAct(Integer id);

    List<Integer> getJoinAct(Integer id);
}
