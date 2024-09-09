# Issue Description

The issue reproduced in this sample project first occurred in one of my Micronaut Lambda projects, when I tried to 
upgrade to Micronaut platform version 4.6.1 (from version 4.4.3).

Running the provided test `HomeControllerForLambdaRuntimeTest` (tried with Java 17 on Windows OS) produces the following runtime error:

```
10:19:14.053 [main] ERROR i.m.http.server.RouteExecutor - Unexpected error occurred: Error encoding object [ClaimDTO(id=1, claimDate=null, circumstances=null)] to JSON: Incompatible types: declared root type ([reference type, class java.util.Optional<com.example.ClaimDTO><[simple type, class com.example.ClaimDTO]>]) vs `com.example.ClaimDTO`
io.micronaut.http.codec.CodecException: Error encoding object [ClaimDTO(id=1, claimDate=null, circumstances=null)] to JSON: Incompatible types: declared root type ([reference type, class java.util.Optional<com.example.ClaimDTO><[simple type, class com.example.ClaimDTO]>]) vs `com.example.ClaimDTO`
at io.micronaut.json.body.JsonMessageHandler.decorateWrite(JsonMessageHandler.java:129)
at io.micronaut.json.body.JsonMessageHandler.writeTo(JsonMessageHandler.java:142)
at io.micronaut.http.netty.body.NettyJsonHandler.writeTo(NettyJsonHandler.java:125)
at io.micronaut.servlet.http.ServletHttpHandler.encodeResponse(ServletHttpHandler.java:520)
at io.micronaut.servlet.http.ServletHttpHandler.onComplete(ServletHttpHandler.java:272)
at io.micronaut.servlet.http.ServletHttpHandler.lambda$service$6(ServletHttpHandler.java:237)
at io.micronaut.core.execution.ImperativeExecutionFlowImpl.onComplete(ImperativeExecutionFlowImpl.java:132)
at io.micronaut.servlet.http.ServletHttpHandler.service(ServletHttpHandler.java:235)
at io.micronaut.servlet.http.ServletHttpHandler.exchange(ServletHttpHandler.java:183)
at io.micronaut.servlet.http.ServletHttpHandler.exchange(ServletHttpHandler.java:173)
at io.micronaut.function.aws.proxy.payload1.ApiGatewayProxyRequestEventFunction.handleRequest(ApiGatewayProxyRequestEventFunction.java:59)
at com.example.HomeControllerForLambdaRuntimeTest.fetch(HomeControllerForLambdaRuntimeTest.java:116)
at com.example.HomeControllerForLambdaRuntimeTest.testGetClaimFound(HomeControllerForLambdaRuntimeTest.java:96)
at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
at java.base/java.lang.reflect.Method.invoke(Method.java:568)
at org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:766)
at org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
at org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
at io.micronaut.test.extensions.junit5.MicronautJunit5Extension$2.proceed(MicronautJunit5Extension.java:142)
at io.micronaut.test.extensions.AbstractMicronautExtension.interceptEach(AbstractMicronautExtension.java:162)
at io.micronaut.test.extensions.AbstractMicronautExtension.interceptTest(AbstractMicronautExtension.java:119)
at io.micronaut.test.extensions.junit5.MicronautJunit5Extension.interceptTestMethod(MicronautJunit5Extension.java:129)
at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker$ReflectiveInterceptorCall.lambda$ofVoidMethod$0(InterceptingExecutableInvoker.java:103)
at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.lambda$invoke$0(InterceptingExecutableInvoker.java:93)
at org.junit.jupiter.engine.execution.InvocationInterceptorChain$InterceptedInvocation.proceed(InvocationInterceptorChain.java:106)
at io.micronaut.test.extensions.junit5.MicronautJunit5Extension$2.proceed(MicronautJunit5Extension.java:142)
at io.micronaut.test.extensions.AbstractMicronautExtension.interceptEach(AbstractMicronautExtension.java:162)
at io.micronaut.test.extensions.AbstractMicronautExtension.interceptTest(AbstractMicronautExtension.java:119)
at io.micronaut.test.extensions.junit5.MicronautJunit5Extension.interceptTestMethod(MicronautJunit5Extension.java:129)
at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker$ReflectiveInterceptorCall.lambda$ofVoidMethod$0(InterceptingExecutableInvoker.java:103)
at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.lambda$invoke$0(InterceptingExecutableInvoker.java:93)
at org.junit.jupiter.engine.execution.InvocationInterceptorChain$InterceptedInvocation.proceed(InvocationInterceptorChain.java:106)
at org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156)
at org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestableMethod(TimeoutExtension.java:147)
at org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestMethod(TimeoutExtension.java:86)
at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker$ReflectiveInterceptorCall.lambda$ofVoidMethod$0(InterceptingExecutableInvoker.java:103)
at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.lambda$invoke$0(InterceptingExecutableInvoker.java:93)
at org.junit.jupiter.engine.execution.InvocationInterceptorChain$InterceptedInvocation.proceed(InvocationInterceptorChain.java:106)
at org.junit.jupiter.engine.execution.InvocationInterceptorChain.proceed(InvocationInterceptorChain.java:64)
at org.junit.jupiter.engine.execution.InvocationInterceptorChain.chainAndInvoke(InvocationInterceptorChain.java:45)
at org.junit.jupiter.engine.execution.InvocationInterceptorChain.invoke(InvocationInterceptorChain.java:37)
at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.invoke(InterceptingExecutableInvoker.java:92)
at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.invoke(InterceptingExecutableInvoker.java:86)
at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$invokeTestMethod$8(TestMethodTestDescriptor.java:217)
at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.invokeTestMethod(TestMethodTestDescriptor.java:213)
at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:138)
at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:68)
at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:151)
at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:141)
at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:139)
at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:138)
at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95)
at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:155)
at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:141)
at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:139)
at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:138)
at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95)
at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:155)
at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:141)
at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:139)
at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:138)
at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95)
at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:198)
at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:169)
at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:93)
at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:58)
at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:141)
at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:57)
at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:103)
at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:85)
at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
at org.apache.maven.surefire.junitplatform.LazyLauncher.execute(LazyLauncher.java:56)
at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.execute(JUnitPlatformProvider.java:184)
at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.invokeAllTests(JUnitPlatformProvider.java:148)
at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.invoke(JUnitPlatformProvider.java:122)
at org.apache.maven.surefire.booter.ForkedBooter.runSuitesInProcess(ForkedBooter.java:385)
at org.apache.maven.surefire.booter.ForkedBooter.execute(ForkedBooter.java:162)
at org.apache.maven.surefire.booter.ForkedBooter.run(ForkedBooter.java:507)
at org.apache.maven.surefire.booter.ForkedBooter.main(ForkedBooter.java:495)
Caused by: com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Incompatible types: declared root type ([reference type, class java.util.Optional<com.example.ClaimDTO><[simple type, class com.example.ClaimDTO]>]) vs `com.example.ClaimDTO`
```
## Mitigation
Forcing ´micronaut-servlet-core´ to version 4.7.0 in `pom.xml` will make the issue go away (and this makes me suspect that this issue originates with some changes in that library) 

## Other remarks
Oddly enough, the Micronaut Netty runtime does NOT exhibit the issue. This can be observed by commenting out the relevant dependencies (see `pom.xml`)
and adding the `micronaut-http-server-netty` dependency and un-commenting and running the test class `HomeControllerForNettyRuntimeTest`.

## Micronaut 4.6.1 Documentation

- [User Guide](https://docs.micronaut.io/4.6.1/guide/index.html)
- [API Reference](https://docs.micronaut.io/4.6.1/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/4.6.1/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

- [Micronaut Maven Plugin documentation](https://micronaut-projects.github.io/micronaut-maven-plugin/latest/)
## Feature hamcrest documentation

- [https://hamcrest.org/JavaHamcrest/](https://hamcrest.org/JavaHamcrest/)


## Feature micronaut-aot documentation

- [Micronaut AOT documentation](https://micronaut-projects.github.io/micronaut-aot/latest/guide/)


## Feature maven-enforcer-plugin documentation

- [https://maven.apache.org/enforcer/maven-enforcer-plugin/](https://maven.apache.org/enforcer/maven-enforcer-plugin/)


## Feature junit-params documentation

- [https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests](https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests)


