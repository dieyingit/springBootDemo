package com.example.demo;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.Future;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastJpaDependencyAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.example.demo.hazelcast.task.TestTask;
import com.example.demo.util.ApplicationUtil;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import com.hazelcast.core.IMap;
import com.hazelcast.core.Member;
import com.ulisesbocchio.jasyptspringboot.environment.StandardEncryptableEnvironment;

//注释这两个Hazelcast的autoconfiguration，自己启动hazelcastInstance
@SpringBootApplication(exclude = { HazelcastAutoConfiguration.class, HazelcastJpaDependencyAutoConfiguration.class })
@EnableCaching // 开启缓存，需要显示的指定
public class SpringBootDemoApplication {

	@Bean
	public ExitCodeGenerator exitCodeGenerator() {
		return () -> 44;
	}

	public static void main(String[] args) {
		// 1.普通启动方式
		// SpringApplication application = new
		// SpringApplication(SpringBootDemoApplication.class);
		// application.setBannerMode(Mode.OFF);
		// application.setAddCommandLineProperties(false);
		// application.addListeners(new AppListener());
		// ConfigurableApplicationContext cxt = application.run(args);
		// System.out.println("Custom Exist Code: " + application.exit(cxt));

		// 2. 增加了jasypt支持后的启动方式
		ConfigurableApplicationContext ctx = new SpringApplicationBuilder()
				.environment(new StandardEncryptableEnvironment()).sources(SpringBootDemoApplication.class).run(args);
		// 启动Hazelcast
		ApplicationUtil.setHazelcastInstance(initHazelcastService());
		testHazelcast();
	}

	private static HazelcastInstance initHazelcastService() {
		Config config = new Config();
		// 修改单个节点默认的分区个数,还有其他很多属性可以修改
		// config.setProperty(GroupProperty.PARTITION_COUNT, slaveNum + 1);
		int slaveNum = 4;
		// 和xml里<properties>同样的效果
		config.setProperty("hazelcast.partition.count", (slaveNum + 1) + "");
		// Hazelcast.newHazelcastInstance(config);
		/*
		 * 如果没有提供一个config给Hazelcast，hz会按照以下顺序寻找配置文件 1.System.getProperty(
		 * "hazelcast.config");---classpath:config/hazelcast-config.xml
		 * 2.当前工作目录hazelcast.xml 3.classpath下的hazelcast.xml 4.hazelcast
		 * package里的hazelcast.xml
		 */
		System.setProperty("hazelcast.config", "classpath:config/hazelcast-config.xml");
		return Hazelcast.newHazelcastInstance();
	}

	private static void testHazelcast() {
		// 1.Imap
		HazelcastInstance hz = ApplicationUtil.getHazelcastInstance();
		IMap<String, Object> testMap = hz.getMap("test");
		System.out.println(testMap.toString() + ", size :" + testMap.size() + ", HeapCost:"
				+ testMap.getLocalMapStats().getHeapCost() + " bytes ");
		IMap<String, Object> testMap2 = hz.getMap("test2");
		System.out.println(testMap2.toString() + ", size :" + testMap2.size() + ", HeapCost:"
				+ testMap2.getLocalMapStats().getHeapCost() + " bytes ");
		// 2. task
		IExecutorService service = hz.getExecutorService("customized");
		TestTask task = new TestTask(new Date());
		Map<Member, Future<Boolean>> taskFutures = service.submitToAllMembers(task);
		getFutureResults(taskFutures);
	}

	@SuppressWarnings("unused")
	private static boolean getFutureResults(Map<Member, Future<Boolean>> taskFutures) {
		boolean calcualteResult = true;
		for (Member member : taskFutures.keySet()) {
			Future<Boolean> result = taskFutures.get(member);
			try {
				boolean futureResult = result.get();
				System.out.println("Get Result from one node: " + member + ", result is: " + futureResult);
				calcualteResult = calcualteResult && futureResult;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return calcualteResult;
	}

}
