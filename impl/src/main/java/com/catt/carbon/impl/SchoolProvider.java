package com.catt.carbon.impl;

import com.catt.carbon.dao.UserDao;
import com.google.common.util.concurrent.Futures;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv4Address;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.school.rev150105.GetAllSchoolUserListOutput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.school.rev150105.GetAllSchoolUserListOutputBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.school.rev150105.SchoolService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.school.rev150105.school.user.list.grouping.SchoolUserListBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.school.rev150105.school.user.list.grouping.school.user.list.UserList;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.school.rev150105.school.user.list.grouping.school.user.list.UserListBuilder;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.opendaylight.yangtools.yang.common.RpcResultBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class SchoolProvider implements SchoolService {

    private static final Logger LOG = LoggerFactory.getLogger(UserProvider.class);
    private final UserDao userDao;

    public SchoolProvider(final UserDao userDao) {
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
     * @return <code>java.util.concurrent.Future</code> <code>allSchoolUserList</code>, or <code>null</code> if not present
     */
    @Override
    public Future<RpcResult<GetAllSchoolUserListOutput>> getAllSchoolUserList() {
        GetAllSchoolUserListOutputBuilder getAllSchoolUserListOutputBuilder = new GetAllSchoolUserListOutputBuilder();

        SchoolUserListBuilder schoolUserListBuilder = new SchoolUserListBuilder();
        Ipv4Address ipv4Address = new Ipv4Address("8.8.8.8");
        schoolUserListBuilder.setSchoolIp(ipv4Address);

        List<UserList> list = new ArrayList<>();
        UserListBuilder builder = new UserListBuilder();
        builder.setParentId(1);
        list.add(builder.build());

        schoolUserListBuilder.setUserList(list);

        getAllSchoolUserListOutputBuilder.setSchoolUserList(schoolUserListBuilder.build());

        return Futures.immediateCheckedFuture(RpcResultBuilder.success(getAllSchoolUserListOutputBuilder).build());
    }

}
