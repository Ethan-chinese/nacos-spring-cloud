package com.sj.user.feign.fallback;

import com.sj.user.feign.entity.UserBean;
import com.sj.user.feign.service.IUserService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author sijia
 * @desc ...
 * @date 2021-01-15 09:02:20
 */
@Slf4j
public class UserFallback implements IUserService {

    @Override
    public int getUser(UserBean userBean) {
        log.error("操作失败 用户id为 ->" + userBean.getId());
        return 0;
    }
}
