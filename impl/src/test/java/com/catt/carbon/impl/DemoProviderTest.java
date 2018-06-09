package com.catt.carbon.impl;

import org.junit.Before;
import org.junit.Test;
import org.opendaylight.controller.md.sal.binding.impl.BindingDOMDataBrokerAdapter;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.demo.rev150105.HelloInputBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.demo.rev150105.HelloOutput;
import org.opendaylight.yangtools.yang.common.RpcResult;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.Assert.*;

public class DemoProviderTest {

    private DemoProvider demoProvider;

    @Before
    public void init(){
        demoProvider = new DemoProvider(null);
    }
    @Test
    public void testHello() throws ExecutionException, InterruptedException {
        HelloInputBuilder input = new HelloInputBuilder();
        input.setName("张茂林");
        Future<RpcResult<HelloOutput>> hello = demoProvider.hello(input.build());
        HelloOutput result = hello.get().getResult();
        System.out.println(result);
    }

}