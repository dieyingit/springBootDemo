package com.example.demo.zookeeper.distributelock;

/**
 * 测试Zookeeper实现的分布式锁
 * 
 * @author lijin
 *
 */
public class DistributedLockTest {
	static int n = 500;

	public static void secskill() {
		System.out.println(--n);
	}

	public static void main(String[] args) {

		Runnable runnable = new Runnable() {
			public void run() {
				DistributedLock lock = null;
				try {
					lock = new DistributedLock("192.168.22.50:2181,192.168.22.50:2182,192.168.22.50:2183", "test1");
					lock.lock();
					secskill();
					try {
						Thread.sleep(4*1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + "正在运行");
				} finally {
					if (lock != null) {
						lock.unlock();
					}
				}
			}
		};

		for (int i = 0; i < 2; i++) {
			Thread t = new Thread(runnable);
			t.start();
		}
	}
}
