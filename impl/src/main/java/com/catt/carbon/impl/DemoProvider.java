/*
 * Copyright © 2017 ZhangMaoLin and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package com.catt.carbon.impl;

import com.google.common.util.concurrent.Futures;
import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.demo.rev150105.DemoService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.demo.rev150105.HelloInput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.demo.rev150105.HelloOutput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.demo.rev150105.HelloOutputBuilder;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.opendaylight.yangtools.yang.common.RpcResultBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Future;

/**
 * DemoService是api模块中自动生成的接口类
 * Demo是yang中定义的module demo
 */
public class DemoProvider implements DemoService {

    private static final Logger LOG = LoggerFactory.getLogger(DemoProvider.class);

    private final DataBroker dataBroker;

    public DemoProvider(final DataBroker dataBroker) {
        this.dataBroker = dataBroker;
    }

    /**
     * Method called when the blueprint container is created.
     */
    public void init() {
        LOG.info("DemoProvider Session Initiated");
    }

    /**
     * Method called when the blueprint container is destroyed.
     */
    public void close() {
        LOG.info("DemoProvider Closed");
    }

    /**
     * @param input yang中定义的input输入参数
     * @return yang中定义的output输出参数
     */
    @Override
    public Future<RpcResult<HelloOutput>> hello(HelloInput input) {
        try {

            //获取入参
            String name = new String(input.getName().getBytes("GBK"), "UTF-8");
            //实例化一个出参构建类
            HelloOutputBuilder builder = new HelloOutputBuilder();
            builder.setGreeting(name + "，你好呀");

            return Futures.immediateFuture(RpcResultBuilder.success(builder).build());
        } catch (Exception e) {
            LOG.error("请求失败", e);
        }

        HelloOutputBuilder builder = new HelloOutputBuilder();
        return Futures.immediateFuture(RpcResultBuilder.success(builder).build());
    }
}