package com.mengxuegu.springboot.mapper;

import com.mengxuegu.springboot.common.Pager;
import com.mengxuegu.springboot.entities.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ActivityMapper {
    List getActivityByPage(@Param("entity") Pager<Activity> pager);

    Activity getActivityById(Integer id);

    void updateActivity(Activity activity);

    void addActivity(Activity activity);

    void deleteActivityById(Integer id);

    void addCreateRelation(CreateActivity createActivity);

    int getUserIdByActId(Integer id);

    void addJoinRelation(JoinActivity joinActivity);

    Statistics statistics();

    List<Logo> getLogoView(@Param("entity") Pager<Logo> pager);

    List<JoinActivity> getJoinActivityLists(Integer id);//通过活动id找评论者的评论，连表找评论者的姓名

    List<JoinActivity> getJoinActivityWaitSureList();//得到未通过审核的用户参加活动申请

    void addPointByUid(User user);//通过用户id给对应用户增加1积分

    void passJoin(JoinActivity joinActivity);//对应关联表状态设为1

    List<Logo> getShowLogoView(@Param("entity") Pager<Logo> pager);

/*    List<String> getNamesByCreateIds(@Param("list") List<Integer> createIds);

    List<String> getNamesByJoiniIds(@Param("list") List<Integer> joinIds);*/
}
