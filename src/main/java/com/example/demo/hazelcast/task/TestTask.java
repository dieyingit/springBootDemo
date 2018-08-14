package com.example.demo.hazelcast.task;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.runner.CommandRunner;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;

/**
 * Hazelcast可执行的Callable task
 * 
 * @author lijin
 *
 */
public class TestTask implements Callable<Boolean>, Serializable, HazelcastInstanceAware {
	Logger log = LoggerFactory.getLogger(TestTask.class);

	private static final long serialVersionUID = 5760176879737810698L;

	private HazelcastInstance hz;
	private Date marketDate;

	public TestTask(Date marketDate) {
		this.marketDate = marketDate;
	}

	@Override
	public Boolean call() {
		log.info("execute TestTask with paramters: {},{}", this.marketDate, this.hz);
		return true;
	}

	@Override
	public void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
		this.hz = hazelcastInstance;
	}

}
