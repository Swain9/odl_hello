package com.catt.carbon.dao;

import com.catt.carbon.model.User;

import java.util.List;

/**
 * 用户模块接口
 *
 * @author zhangmaolin
 * @since 2018年6月9日 18点05分
 */
public interface UserDao {
    /**
     * 初始化数据
     */
    void init();

    /**
     * 根据部门id获取所有用户
     *
     * @param deptId 部门id
     * @return List<User>
     */
    List<User> getAllUserByDeptId(Integer deptId);
}
