package com.example.demo.zookeeper.curator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.ACLProvider;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;
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
public class CuratorTest {
	/** zookeeper地址 */
	// static final String CONNECT_ADDR =
	// "192.168.22.50:2181,192.168.22.50:2182,192.168.22.50:2183";
	/** session超时时间 */
	static final int SESSION_OUTTIME = 5000;// ms
	static final int CONNECTION_OUTTIME = 3000;

	public static void main(String[] args) throws Exception {
		// 创建一个测试的zookeeper服务器
		TestingServer server = new TestingServer(2181);
		server.start();
		// 创建ACL
		ACLProvider aclProvider = new ACLProvider() {
			private List<ACL> acl;

			@Override
			public List<ACL> getDefaultAcl() {
				if (acl == null) {
					ArrayList<ACL> acl = ZooDefs.Ids.CREATOR_ALL_ACL;
					acl.clear();
					acl.add(new ACL(Perms.ALL, new Id("auth", "admin:admin")));
					this.acl = acl;
				}
				return acl;
			}

			@Override
			public List<ACL> getAclForPath(String path) {
				return acl;
			}
		};
		// 1 重试策略：初试时间为1s 重试10次
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
		// 2 通过工厂创建连接
		CuratorFramework client = CuratorFrameworkFactory.builder().connectString(server.getConnectString())
				.sessionTimeoutMs(SESSION_OUTTIME).retryPolicy(retryPolicy).namespace("base1")
				.connectionTimeoutMs(CONNECTION_OUTTIME).aclProvider(aclProvider)
				.authorization("digest", "admin:admin".getBytes()).build();
		// CuratorFramework cf = CuratorFrameworkFactory.newClient(CONNECT_ADDR,
		// SESSION_OUTTIME, CONNECTION_OUTTIME,
		// retryPolicy);
		// 3 开启连接
		client.start();
		System.out.println(States.CONNECTED);
		System.out.println(client.getState());

		// 在namespace下新建node，实际路径为/base1/namespace
		client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/key1", "11111".getBytes());
		client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/key2", "22222".getBytes());
		client.setData().forPath("/key1", "tet".getBytes());

		System.out.println("/key1: " + new String(client.getData().forPath("/key1")));
		System.out.println("/key2: " + new String(client.getData().forPath("/key2")));

		// 删除path以及path的所有子节点
		client.delete().guaranteed().deletingChildrenIfNeeded().forPath("/key2");

		// 事务
		// client.inTransaction().check().forPath("/path").and().create().withMode(CreateMode.EPHEMERAL)
		// .forPath("/path", "data".getBytes()).and().setData().forPath("/path",
		// "data2".getBytes()).and()
		// .commit();
		//
		// System.out.println("/path: " + new
		// String(client.getData().forPath("/path")));

		// 异步处理
		Executor executor = Executors.newFixedThreadPool(2);
		client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
				.inBackground((curatorFramework, curatorEvent) -> {
					System.out.println(String.format("eventType:%s,resultCode:%s", curatorEvent.getType(),
							curatorEvent.getResultCode()));
				}, executor).forPath("/path");
		// 关闭测试的Zookeeper服务器
		// server.close();
	}
}
