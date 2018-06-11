package com.catt.carbon.impl;

import com.alibaba.fastjson.JSON;
import com.catt.carbon.dao.UserDao;
import com.catt.carbon.model.User;
import com.google.common.util.concurrent.Futures;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.user.rev150105.GetAllUserByDeptIdInput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.user.rev150105.GetAllUserByDeptIdOutput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.user.rev150105.GetAllUserByDeptIdOutputBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.user.rev150105.UserService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.user.rev150105.get.all.user.by.dept.id.output.UserList;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.user.rev150105.get.all.user.by.dept.id.output.UserListBuilder;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.opendaylight.yangtools.yang.common.RpcResultBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class UserProvider implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserProvider.class);
    private final UserDao userDao;

    public UserProvider(final UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Method called when the blueprint container is created.
     */
    public void init() {
        LOG.info("UserProvider Session Initiated");
    }

    /**
     * Method called when the blueprint container is destroyed.
     */
    public void close() {
        LOG.info("UserProvider Closed");
    }

    /**
     * @param input
     * @return <code>java.util.concurrent.Future</code> <code>allUserByDeptId</code>, or <code>null</code> if not present
     */
    @Override
    public Future<RpcResult<GetAllUserByDeptIdOutput>> getAllUserByDeptId(GetAllUserByDeptIdInput input) {
        //判断是否有参数传进来
        if (input == null) {
            //如果没有参数，创建一个值为null的返回参数的Builder对象
            GetAllUserByDeptIdOutputBuilder builder = new GetAllUserByDeptIdOutputBuilder();
            //返回null
            return Futures.immediateFuture(RpcResultBuilder.success(builder).build());
        }
        //有参数
        Integer deptId = input.getDeptId();
        //取出数据
        List<User> list = userDao.getAllUserByDeptId(deptId);

        String listStr = JSON.toJSONString(list);
        LOG.info(listStr);

        //将数据传递给List<UserList>
        List<UserList> outputList = new ArrayList<>();
        //遍历取出的数据
        for (User user : list) {
            //实例化一个UserList构建对象
            UserListBuilder builder = new UserListBuilder();
            //将值传入构建对象中
            builder.setId(user.getId());
            builder.setUserName(user.getUserName());
            builder.setUserAge(user.getUserAge());
            builder.setUserAddress(user.getUserAddress());
            //调用build()方法，生成UserList对象
            UserList userList = builder.build();
            //将UserList对象存入List<UserList>中
            outputList.add(userList);
        }
        //创建一个GetAllUserByDeptIdOutput的Builder对象
        GetAllUserByDeptIdOutputBuilder outputBuilder = new GetAllUserByDeptIdOutputBuilder();
        //将List<UserList>传入GetAllUserByDeptIdOutputBuilder中
        outputBuilder.setUserList(outputList);
        //返回数据
        return Futures.immediateFuture(RpcResultBuilder.success(outputBuilder).build());
    }
}
