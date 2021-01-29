package com.sj.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sj.user.feign.entity.UserBean;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author sijia
 * @desc ...
 * @date 2021-01-18 14:52:01
 */
public interface UserMapper extends BaseMapper<UserBean> {
}
