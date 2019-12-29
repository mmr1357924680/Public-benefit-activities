package com.mengxuegu.springboot.controller;

import com.mengxuegu.springboot.common.Pager;
import com.mengxuegu.springboot.entities.*;
import com.mengxuegu.springboot.mapper.ActivityMapper;
import com.mengxuegu.springboot.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Controller
@Transactional
public class ActivityController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ActivityMapper activityMapper;
    @Autowired
    UserMapper userMapper;


    @GetMapping("/toLogoPage")
    public String toLogoPage(Map<String, Object> map,Pager<Logo> pager){
        logger.info("活动LOGO展示页面。。。" );
        List<Logo> lists = activityMapper.getShowLogoView(pager);
        map.put("logos",lists);
        return "activity/logoPage";
    }
    @GetMapping("/activities")
    public String list(Map<String, Object> map, String name, Pager<Activity> pager){//传入活动名称,查这个活动
        logger.info("活动查询。。。" + name);
        List<Activity> lists = activityMapper.getActivityByPage(pager);
        map.put("activity",lists);
        return "activity/list";
    }
    @GetMapping("/statistics")
    public String statistics(Map<String, Object> map){//返回活动信息统计信息
        logger.info("返回活动统计信息。。。");
        Statistics statistics = activityMapper.statistics();
        map.put("statistics",statistics);
        statistics.setPassRatio(statistics.getPassRatio()*100);
        statistics.setActAvg(statistics.getActAvg()*100);
        statistics.setActAvgComment(statistics.getActAvgComment()*100);
        logger.info("审核通过率"+Double.toString(statistics.getPassRatio()));
        logger.info("活动平均参加人数比例"+Double.toString(statistics.getActAvg()));
        logger.info("活动平均评论人数比例"+Double.toString(statistics.getActAvgComment()));

        //在统计页面，显示用户提交参加申请，还未通过，需要管理员确认的列表
        List<JoinActivity> joinActivityWaitSureList = activityMapper.getJoinActivityWaitSureList();
        logger.info("记录"+joinActivityWaitSureList);
        map.put("joinActivityWaitSureList",joinActivityWaitSureList);

        logger.info("返回。。。");
        return "activity/statistics";
    }
    //管理员前往编辑前台logo页面
    @GetMapping("/editLogoView")
    public String editLogoView(Map<String, Object> map,Pager<Logo> pager){
        logger.info("前往logo编辑页面");
        List<Logo> lists = activityMapper.getLogoView(pager);
        logger.info("logo>>>>>>>>>>>>"+lists);
        map.put("logo",lists);
        return "activity/showBackSide";
    }

    @GetMapping("/activitiesUser")//普通用户查看活动列表
    public String listN(Map<String, Object> map, String name, Pager<Activity> pager){//传入活动名称,查这个活动
        logger.info("活动查询(普通用户)。。。" + name);
        List<Activity> lists = activityMapper.getActivityByPage(pager);
        map.put("activity",lists);

        return "activityN/list";
    }
    /**
     * type = null 进入查看详情页面view.html，
     * type=update 则是进入update.html
     */
    @GetMapping("/activity/{id}")
    public String view(@PathVariable("id") Integer id,
                       @RequestParam(value="type", defaultValue = "view") String type,
                       Map<String, Object> map) {
        logger.info("查询" + id + "的详细信息");

        Activity activity = activityMapper.getActivityById(id);

        List<JoinActivity> joinActivities = activityMapper.getJoinActivityLists(id);

        map.put("activity", activity);

        map.put("joinActivities",joinActivities);

        // type = null 则进入view.html， type=update 则是进入update.html
        return "activity/" + type;
    }

    //修改信息（若一开始state为0，后来变成1，对应用户加5积分）
    @PutMapping("/activity")
    public String update(Activity activity) {
        logger.info("更改信息。。。");

        //查询该活动之前是否未发布
        Activity a = activityMapper.getActivityById(activity.getId());
        if (a.getState()==0){
            if (activity.getState()==1){
                //找到对应用户id
                int userId = activityMapper.getUserIdByActId(activity.getId());
                //该用户加5积分
                userMapper.addFivePoint(userId);
            }
        }
        //更新操作
        activityMapper.updateActivity(activity);
        return "redirect:activities";
    }

    //前往添加 页面
    @GetMapping("/activity")
    public String toAddPage() {
        return "activity/add";
    }


    //添加数据（同时向中间表中添加关联id）
    @PostMapping("/activity")
    public String add(Activity activity, HttpServletRequest request) {

        logger.info("添加数据" + activity);
        //保存数据操作
        activityMapper.addActivity(activity);
        //中间表添加数据
        CreateActivity createActivity = new CreateActivity();
        User user = (User) request.getSession().getAttribute("loginUser");
        createActivity.setUid(user.getId());
        createActivity.setAid(activity.getId());
        activityMapper.addCreateRelation(createActivity);
        return "redirect:/activitiesUser";
    }

    //删除
    @DeleteMapping("/activity/{id}")
    public String delete(@PathVariable("id") Integer id) {
        logger.info("删除操作, id=" + id);
        activityMapper.deleteActivityById(id);

        return "redirect:/activities";
    }
    //用户提交参加活动申请
    @GetMapping("joinActivity")
    public String joinActivity(JoinActivity jActivity,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("loginUser");
        //关联表增加参加活动关系
        JoinActivity joinActivity = new JoinActivity();
        joinActivity.setUid(user.getId());
        joinActivity.setAid(jActivity.getAid());
        joinActivity.setComments(jActivity.getComments());
        activityMapper.addJoinRelation(joinActivity);
        return "redirect:/activitiesUser";
    }

    //管理员通过参加活动的申请（加1积分）
    @GetMapping("passJoinActivity")
    public String passJoinActivity( Integer uid, Integer aid){
        //管理员通过参加活动的申请（加1积分）
        logger.info("增加1积分");
        User user = new User();
        user.setId(uid);
        activityMapper.addPointByUid(user);
        //通过申请，join_activity关联表state变成1
       JoinActivity joinActivity = new JoinActivity();
       joinActivity.setAid(aid);
       joinActivity.setUid(uid);
       activityMapper.passJoin(joinActivity);
        return "redirect:/activitiesUser";
    }

}
