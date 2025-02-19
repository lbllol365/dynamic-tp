/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dromara.dynamictp.example.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.dromara.dynamictp.core.plugin.DtpInterceptor;
import org.dromara.dynamictp.core.plugin.DtpIntercepts;
import org.dromara.dynamictp.core.plugin.DtpInvocation;
import org.dromara.dynamictp.core.plugin.DtpSignature;
import org.dromara.dynamictp.core.thread.DtpExecutor;
import org.dromara.dynamictp.core.thread.ScheduledDtpExecutor;

import java.lang.reflect.InvocationTargetException;

/**
 * @author windsearcher.lq
 */
@DtpIntercepts({
        @DtpSignature(clazz = DtpExecutor.class, method = "execute", args = {Runnable.class}),
        @DtpSignature(clazz = ScheduledDtpExecutor.class, method = "execute", args = {Runnable.class})
})
@Slf4j
public class TestExecuteInterceptor implements DtpInterceptor {

    @Override
    public Object intercept(DtpInvocation invocation) throws InvocationTargetException, IllegalAccessException {

        DtpExecutor dtpExecutor = (DtpExecutor) invocation.getTarget();
        String method = invocation.getMethod().getName();
        Object[] args = invocation.getArgs();
        log.info("TestExecuteInterceptor: dtpExecutor: {}, method: {}, args: {}", dtpExecutor, method, args);
        return invocation.proceed();
    }
}
