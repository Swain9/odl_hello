package com.catt.carbon.impl;

import com.google.common.util.concurrent.Futures;
import org.opendaylight.yang.gen.v1.urn.com.maolin.order.manager.rev150105.GetOrderInfoOutput;
import org.opendaylight.yang.gen.v1.urn.com.maolin.order.manager.rev150105.GetOrderInfoOutputBuilder;
import org.opendaylight.yang.gen.v1.urn.com.maolin.order.manager.rev150105.OrderManagerService;
import org.opendaylight.yang.gen.v1.urn.com.maolin.order.rev150105.OrderPending;
import org.opendaylight.yang.gen.v1.urn.com.maolin.order.rev150105.order.grouping.OrderBuilder;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.opendaylight.yangtools.yang.common.RpcResultBuilder;

import java.util.concurrent.Future;

public class OrderManagerProvider implements OrderManagerService {
    /**
     * @return <code>java.util.concurrent.Future</code> <code>orderInfo</code>, or <code>null</code> if not present
     */
    @Override
    public Future<RpcResult<GetOrderInfoOutput>> getOrderInfo() {
        OrderBuilder orderBuilder = new OrderBuilder();
        orderBuilder.setOrderId("WX20180615");
        orderBuilder.setOrderType(OrderPending.class);

        GetOrderInfoOutputBuilder getOrderInfoOutputBuilder = new GetOrderInfoOutputBuilder();
        getOrderInfoOutputBuilder.setOrder(orderBuilder.build());

        return Futures.immediateFuture(RpcResultBuilder.success(getOrderInfoOutputBuilder).build());
    }
}
