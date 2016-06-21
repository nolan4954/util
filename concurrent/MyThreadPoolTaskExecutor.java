package com.best.web.htyt.util.concurrent;

import java.util.concurrent.BlockingQueue;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class MyThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3544586396652268843L;

	protected BlockingQueue<Runnable> createQueue(int queueCapacity) {
		if (queueCapacity > 0) {
			return new LimitedQueue<Runnable>(queueCapacity);
		}
		else {
			return super.createQueue(queueCapacity);
		}
	}
}
