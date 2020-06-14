# 测试dubbo微服务

环境准备：
1、打包：mvn clean package
2、准备运行目录，比如/User/xxx/dubbo-test,复制打包好的serviceA-1.0.0-SNAPSHOT.jar以及serviceB-1.0.0-SNAPSHOT.jar到该目录
3、本地运行nacos 1.1.4

测试场景一：（先启动消费者，后启动提供者，关闭提供者，观察日志）
步骤：
1、启动一个终端，进入运行目录，启动ServiceB（服务消费者）
     java -jar -Ddubbo.protocol.port=20900 -Dserver.port=18088 serviceB-1.0.0-SNAPSHOT.jar
2、启动另外一个终端，进入运行目录，启动ServiceA（服务提供者）
    java -jar -Ddubbo.protocol.port=20801 -Dserver.port=9091 serviceA-1.0.0-SNAPSHOT.jar
3、验证业务：
    http://127.0.0.1:18088/testLocalDateTime。期望：成功
4、Ctrl+C终止ServiceA（服务提供者）
    观察ServiceB（服务消费者）的终端日志有无连接失败信息，期望：无持续连接失败信息。
    验证业务，由于没有ServiceA（服务提供者）运行，期望：失败

测试场景二：（先启动消费者，后启动提供者，关闭提供者，修改dubbo启动端口启动，观察日志）
步骤：
1、启动一个终端，进入运行目录，启动ServiceB（服务消费者）
     java -jar -Ddubbo.protocol.port=20900 -Dserver.port=18088 serviceB-1.0.0-SNAPSHOT.jar
2、启动另外一个终端，进入运行目录，启动ServiceA（服务提供者）
    java -jar -Ddubbo.protocol.port=20801 -Dserver.port=9091 serviceA-1.0.0-SNAPSHOT.jar
3、验证业务：
    http://127.0.0.1:18088/testLocalDateTime 期望：成功
4、Ctrl+C终止ServiceA（服务提供者）
    观察ServiceB（服务消费者）的终端日志有无连接失败信息，期望：无持续连接失败信息。
    验证业务，由于没有ServiceA（服务提供者）运行，期望：失败
5、在ServiceA（服务提供者）终端，修改dubbo端口启动
java -jar -Ddubbo.protocol.port=20802 -Dserver.port=9091 serviceA-1.0.0-SNAPSHOT.jar
6、验证业务：
    http://127.0.0.1:18088/testLocalDateTime 期望：成功
7、Ctrl+C终止ServiceA（服务提供者）
    观察ServiceB（服务消费者）的终端日志有无连接失败信息，期望：无持续连接失败信息。
    验证业务，由于没有ServiceA（服务提供者）运行，期望：失败

测试场景三：（先启动提供者，后启动消费者，关闭提供者，修改dubbo启动端口启动，观察日志）
步骤：
1、启动另外一个终端，进入运行目录，启动ServiceA（服务提供者）
    java -jar -Ddubbo.protocol.port=20801 -Dserver.port=9091 serviceA-1.0.0-SNAPSHOT.jar
2、启动一个终端，进入运行目录，启动ServiceB（服务消费者）
     java -jar -Ddubbo.protocol.port=20900 -Dserver.port=18088 serviceB-1.0.0-SNAPSHOT.jar
3、验证业务：
    http://127.0.0.1:18088/testLocalDateTime 期望：成功
4、Ctrl+C终止ServiceA（服务提供者）
    观察ServiceB（服务消费者）的终端日志有无连接失败信息，期望：无。
    验证业务，由于没有ServiceA（服务提供者）运行，期望：失败
5、在ServiceA（服务提供者）终端，修改dubbo端口启动
java -jar -Ddubbo.protocol.port=20802 -Dserver.port=9091 serviceA-1.0.0-SNAPSHOT.jar
6、验证业务：
    http://127.0.0.1:18088/testLocalDateTime 期望：成功
7、Ctrl+C终止ServiceA（服务提供者）
    观察ServiceB（服务消费者）的终端日志有无连接失败信息，期望：无持续连接失败信息。
    验证业务，由于没有ServiceA（服务提供者）运行，期望：失败

测试场景四：（先启动消费者，后启动2个提供者，关闭其中1个提供者，观察日志，再关闭剩下的提供者，观察日志）
步骤：
1、启动一个终端，进入运行目录，启动ServiceB（服务消费者）
     java -jar -Ddubbo.protocol.port=20900 -Dserver.port=18088 serviceB-1.0.0-SNAPSHOT.jar
2、启动另外一个终端，进入运行目录，启动ServiceA（服务提供者）
    java -jar -Ddubbo.protocol.port=20801 -Dserver.port=9091 serviceA-1.0.0-SNAPSHOT.jar
3、启动第三个终端，进入运行目录，启动ServiceA（服务提供者）
    java -jar -Ddubbo.protocol.port=20802 -Dserver.port=9092 serviceA-1.0.0-SNAPSHOT.jar
4、验证业务：
    http://127.0.0.1:18088/testLocalDateTime 期望：成功
5、Ctrl+C终止1个ServiceA（服务提供者）
    观察ServiceB（服务消费者）的终端日志有无连接失败信息，期望：无持续连接失败信息。
    验证业务，由于还有1个ServiceB（服务提供者）运行，期望：成功
6、Ctrl+C终止最后1个ServiceA（服务提供者）
    观察ServiceB（服务消费者）的终端日志有无连接失败信息，期望：无持续连接失败信息。
    验证业务，由于没有ServiceA（服务提供者）运行，期望：失败

测试场景五：（先启动消费者，后启动2个提供者，关闭其中1个提供者，观察日志，再启动已关闭的提供者，观察日志）
步骤：
1、启动一个终端，进入运行目录，启动ServiceB（服务消费者）
     java -jar -Ddubbo.protocol.port=20900 -Dserver.port=18088 serviceB-1.0.0-SNAPSHOT.jar
2、启动另外一个终端，进入运行目录，启动ServiceA（服务提供者）
    java -jar -Ddubbo.protocol.port=20801 -Dserver.port=9091 serviceA-1.0.0-SNAPSHOT.jar
3、启动第三个终端，进入运行目录，启动ServiceA（服务提供者）
    java -jar -Ddubbo.protocol.port=20802 -Dserver.port=9092 serviceA-1.0.0-SNAPSHOT.jar
4、验证业务：
    http://127.0.0.1:18088/testLocalDateTime， 期望：正常
5、在第三个终端，Ctrl+C终止最后1个ServiceA（服务提供者）
    观察ServiceB（服务消费者）的终端日志有无连接失败信息，期望：无持续连接失败信息。
    验证业务，http://127.0.0.1:18088/testLocalDateTime， 期望：正常
6、在第三个终端，修改dubbo端口重新启动ServiceA（服务提供者）
    java -jar -Ddubbo.protocol.port=20803 -Dserver.port=9093 serviceA-1.0.0-SNAPSHOT.jar
7、验证业务：
    http://127.0.0.1:18088/testLocalDateTime， 期望：正常



Test scenario 1: (start the consumer first, then the provider, close the provider, and observe the log)
Steps:
1. Start a terminal, enter the running directory, and start serviceb (service consumer)
java -jar -Ddubbo.protocol.port=20900 -Dserver.port=18088 serviceB-1.0.0-SNAPSHOT.jar
2. Start another terminal, enter the running directory, and start ServiceA (service provider)
java -jar -Ddubbo.protocol.port=20801 -Dserver.port=9091 serviceA-1.0.0-SNAPSHOT.jar
3. Verification business:
http://127.0.0.1:18088/testLocalDateTime。 Expectation: success
4. CTRL + C terminate ServiceA (service provider)
Observe whether the terminal log of serviceb (service consumer) has connection failure information. It is expected that there is no continuous connection failure information.
Validate business, because no serviceA (service provider) is running, expect: failure

Test scenario 2: (start the consumer first, then the provider, close the provider, modify the Dubbo startup port to start, and observe the log)
Steps:
1. Start a terminal, enter the running directory, and start serviceb (service consumer)
java -jar -Ddubbo.protocol.port=20900 -Dserver.port=18088 serviceB-1.0.0-SNAPSHOT.jar
2. Start another terminal, enter the running directory, and start ServiceA (service provider)
java -jar -Ddubbo.protocol.port=20801 -Dserver.port=9091 serviceA-1.0.0-SNAPSHOT.jar
3. Verification business:
Http://127.0.0.1:18088/testlocaldatetime expected: success
4. CTRL + C terminate ServiceA (service provider)
Observe whether the terminal log of serviceb (service consumer) has connection failure information. It is expected that there is no continuous connection failure information.
Validate business, because no serviceA (service provider) is running, expect: failure
5. At the service a terminal, modify the Dubbo port to start
java -jar -Ddubbo.protocol.port=20802 -Dserver.port=9091 serviceA-1.0.0-SNAPSHOT.jar
6. Verification business:
Http://127.0.0.1:18088/testlocaldatetime expected: success
7. CTRL + C terminate ServiceA (service provider)
Observe whether the terminal log of serviceb (service consumer) has connection failure information. It is expected that there is no continuous connection failure information.
Validate business, because no serviceA (service provider) is running, expect: failure

Test scenario 3: (start the provider first, then the consumer, close the provider, modify the Dubbo start port to start, and observe the log)
Steps:
1. Start another terminal, enter the running directory, and start ServiceA (service provider)
java -jar -Ddubbo.protocol.port=20801 -Dserver.port=9091 serviceA-1.0.0-SNAPSHOT.jar
2. Start a terminal, enter the running directory, and start serviceb (service consumer)
java -jar -Ddubbo.protocol.port=20900 -Dserver.port=18088 serviceB-1.0.0-SNAPSHOT.jar
3. Verification business:
Http://127.0.0.1:18088/testlocaldatetime expected: success
4. CTRL + C terminate ServiceA (service provider)
Observe whether the terminal log of serviceb (service consumer) has connection failure information. Expectation: none.
Validate business, because no serviceA (service provider) is running, expect: failure
5. At the service a terminal, modify the Dubbo port to start
java -jar -Ddubbo.protocol.port=20802 -Dserver.port=9091 serviceA-1.0.0-SNAPSHOT.jar
6. Verification business:
Http://127.0.0.1:18088/testlocaldatetime expected: success
7. CTRL + C terminate ServiceA (service provider)
Observe whether the terminal log of serviceb (service consumer) has connection failure information. It is expected that there is no continuous connection failure information.
Validate business, because serviceA (service provider) is running, expect: failure

Test scenario 4: (start the consumer first, then start two providers, close one of them, observe the log, and then close the remaining providers, observe the log)
Steps:
1. Start a terminal, enter the running directory, and start serviceb (service consumer)
java -jar -Ddubbo.protocol.port=20900 -Dserver.port=18088 serviceB-1.0.0-SNAPSHOT.jar
2. Start another terminal, enter the running directory, and start ServiceA (service provider)
java -jar -Ddubbo.protocol.port=20801 -Dserver.port=9091 serviceA-1.0.0-SNAPSHOT.jar
3. Start the third terminal, enter the running directory, and start ServiceA (service provider)
java -jar -Ddubbo.protocol.port=20802 -Dserver.port=9092 serviceA-1.0.0-SNAPSHOT.jar
4. Verification business:
Http://127.0.0.1:18088/testlocaldatetime expected: success
5. CTRL + C terminate 1 ServiceA (service provider)
Observe whether the terminal log of serviceb (service consumer) has connection failure information. It is expected that there is no continuous connection failure information.
Validate the business, since there is still one serviceb running, expect: success
6. CTRL + C terminate the last ServiceA (service provider)
Observe whether the terminal log of serviceb (service consumer) has connection failure information. It is expected that there is no continuous connection failure information.
Validate business, expecting: failure because no serviceA (service provider) is running

Test scenario 5: (start the consumer first, then start two providers, close one of them, observe the log, and then start the closed provider, observe the log)
Steps:
1. Start a terminal, enter the running directory, and start serviceb (service consumer)
java -jar -Ddubbo.protocol.port=20900 -Dserver.port=18088 serviceB-1.0.0-SNAPSHOT.jar
2. Start another terminal, enter the running directory, and start ServiceA (service provider)
java -jar -Ddubbo.protocol.port=20801 -Dserver.port=9091 serviceA-1.0.0-SNAPSHOT.jar
3. Start the third terminal, enter the running directory, and start ServiceA (service provider)
java -jar -Ddubbo.protocol.port=20802 -Dserver.port=9092 serviceA-1.0.0-SNAPSHOT.jar
4. Verification business:
Http://127.0.0.1:18088/testlocaldatetime, expected: normal
5. On the third terminal, Ctrl + C terminates the last ServiceA (service provider)
Observe whether the terminal log of serviceb (service consumer) has connection failure information. It is expected that there is no continuous connection failure information.
Validation service, http://127.0.0.1:18088/testlocaldatetime, expected: normal
6. In the third terminal, modify Dubbo port and restart ServiceA (service provider)
java -jar -Ddubbo.protocol.port=20803 -Dserver.port=9093 serviceA-1.0.0-SNAPSHOT.jar
7. Verification business:
Http://127.0.0.1:18088/testlocaldatetime, expected: normal