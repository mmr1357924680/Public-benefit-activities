package com.mengxuegu.springboot.mapper;

import com.mengxuegu.springboot.common.Pager;
import com.mengxuegu.springboot.entities.Activity;
import com.mengxuegu.springboot.entities.CreateActivity;
import com.mengxuegu.springboot.entities.JoinActivity;
import com.mengxuegu.springboot.entities.Statistics;
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

/*    List<String> getNamesByCreateIds(@Param("list") List<Integer> createIds);

    List<String> getNamesByJoiniIds(@Param("list") List<Integer> joinIds);*/
}
