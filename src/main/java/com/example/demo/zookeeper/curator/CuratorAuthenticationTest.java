package com.example.demo.zookeeper.curator;

import java.util.ArrayList;
import java.util.List;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.ACLProvider;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooDefs.Perms;
import org.apache.zookeeper.ZooKeeper.States;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;

/**
 * 框架Curator使用 Fluent Style的编码风格
 * 
 * @author lijin
 *
 */
public class CuratorAuthenticationTest {
	/** zookeeper地址 */
	static final String CONNECT_ADDR = "192.168.22.50:2181,192.168.22.50:2182,192.168.22.50:2183";
	/** session超时时间 */
	static final int SESSION_OUTTIME = 5000;// ms
	static final int CONNECTION_OUTTIME = 3000;

	public static void main(String[] args) throws Exception {

		// 1 重试策略：初试时间为1s 重试10次
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
		// 2 不加authorization，则会抛出NoAuth的异常
		CuratorFramework cf = CuratorFrameworkFactory.builder().connectString(CONNECT_ADDR)
				.sessionTimeoutMs(SESSION_OUTTIME).retryPolicy(retryPolicy).namespace("base1")
				.connectionTimeoutMs(CONNECTION_OUTTIME).authorization("digest", "admin:admin".getBytes()).build();
		// .authorization("digest", "admin:admin".getBytes()).build();
		// 3 开启连接
		cf.start();

		System.out.println(new String(cf.getData().forPath("/key1")));
		System.out.println(new String(cf.getData().forPath("/key2")));

		cf.close();
	}
}
