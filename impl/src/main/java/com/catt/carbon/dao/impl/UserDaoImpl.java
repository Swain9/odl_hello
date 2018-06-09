package com.catt.carbon.dao.impl;

import com.catt.carbon.model.User;
import com.catt.carbon.dao.UserDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserDaoImpl implements UserDao {

    private Map<Integer, List<User>> map = new ConcurrentHashMap<>();

    /**
     * 初始化数据
     */
    @Override

    public void init() {
        List<User> list = new ArrayList<User>() {{
            add(new User(1, "张三", 18, "长沙市"));
            add(new User(2, "李四", 38, "长沙市"));
            add(new User(3, "王五", 28, "北京市"));
            add(new User(4, "刘麻子", 98, "长沙市"));
            add(new User(5, "林志玲", 18, "台湾"));
        }};
        map.put(1, list);
    }

    /**
     * 根据部门id获取所有用户
     *
     * @param deptId 部门id
     * @return List<User>
     */
    @Override
    public List<User> getAllUserByDeptId(Integer deptId) {
        return map.get(deptId);
    }
}
