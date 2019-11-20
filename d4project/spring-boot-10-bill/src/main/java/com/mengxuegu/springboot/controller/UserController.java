package com.mengxuegu.springboot.controller;

import com.mengxuegu.springboot.common.Pager;
import com.mengxuegu.springboot.entities.User;
import com.mengxuegu.springboot.mapper.ActivityMapper;
import com.mengxuegu.springboot.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 供应商的控制层
 * @Auther: 梦学谷
 */
@Controller
@Transactional
public class UserController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UserMapper userMapper;
    @Autowired
    ActivityMapper activityMapper;
    

    @GetMapping("/users")
    public String list(Map<String, Object> map, String username,Pager<User> pager) {
        pager.setUsername(username);
        logger.info("用户列表查询。。。" + pager.getUsername());
        List<User> users = userMapper.getUsers(pager);
        map.put("users", users);
        map.put("username", pager.getUsername());

        return "user/list";
    }

    /**
     * type = null 进入查看详情页面view.html，
     * type=update 则是进入update.html
     */
    @GetMapping("/user/{id}")
    public String view(@PathVariable("id") Integer id,
                       @RequestParam(value="type", defaultValue = "view") String type,
                       Map<String, Object> map) {
        logger.info("查询" + id + "的详细信息");

        User user = userMapper.getUserById(id);
        //发布活动详情
        List<Integer> createIds = userMapper.getCreateAct(id);
        //参加活动详情
        List<Integer> joinIds = userMapper.getJoinAct(id);

/*        //通过2个id列表找到这个人发布的所有活动和参加的所有活动
        List<String> createNames = activityMapper.getNamesByCreateIds(createIds);
        List<String> joinNames = activityMapper.getNamesByJoiniIds(joinIds);*/
        // type = null 则进入view.html， type=update 则是进入update.html
/*        logger.info("createNames"+createNames);
        logger.info("joinNames",joinNames);*/
        map.put("user", user);
/*        map.put("createNames",createNames);
        map.put("joinNames",joinNames);*/
        return "user/" + type;
    }

    //修改供应商信息
    @PutMapping("/user")
    public String update(User user) {
        logger.info("更改信息。。。");
        //更新操作
        userMapper.updateUser(user);

        return "redirect:users";
    }

    //前往添加 页面
    @GetMapping("/user")
    public String toAddPage() {
        return "user/add";
    }


    //添加数据
    @PostMapping("/user")
    public String add(User user) {
        logger.info("添加数据" + user);
        //保存数据操作
        userMapper.addUser(user);

        return "redirect:/users";
    }

    //删除
    @DeleteMapping("/user/{id}")
    public String delete(@PathVariable("id") Integer id) {
        logger.info("删除操作, id=" + id);
        userMapper.deleteUserById(id);

        return "redirect:/users";
    }

    @GetMapping("/user/pwd")
    public String toUpdatePwdPage() {
        return "main/password";
    }

    @ResponseBody
    @GetMapping("/user/pwd/{oldPwd}")
    public Boolean checkPwd(HttpSession session, @PathVariable("oldPwd") String oldPwd) {
        logger.info("旧密码:" + oldPwd);
        //1.从Session中获取当前登录用户的User对象
        User user = (User) session.getAttribute("loginUser");
        if(user.getPassword().equals(oldPwd)) {
            //输入的旧密码正确
            return true;
        }
        return false;
    }

    @PostMapping("/user/pwd")
    public String updatePwd(HttpSession session, String password) {
        //1.从Session中获取当前登录用户信息
        User user = (User) session.getAttribute("loginUser");
        //2. 更新密码
        user.setPassword(password);
        //3. 提交到数据库
        userMapper.updateUser(user);
        //4. 注销重新登录
        return "redirect:/logout";
    }


}
