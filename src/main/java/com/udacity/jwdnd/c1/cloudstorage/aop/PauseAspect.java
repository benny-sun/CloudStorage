package com.udacity.jwdnd.c1.cloudstorage.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PauseAspect {

    @Pointcut("within(com.udacity.jwdnd.c1.cloudstorage..*)")
    public void inCloudStoragePackage() {
    }

    @After("inCloudStoragePackage()")
    public void pauseAfterMethodExecution() throws InterruptedException {
        Thread.sleep(500); // 暫停兩秒
    }
}
