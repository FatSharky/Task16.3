package by.training.task16.lock.lock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Resource {
	private static final int SLEEP_TIME = 1000;
	private static final Logger logger = LogManager.getRootLogger();

	private Lock lock = new ReentrantLock();

	/**
	 * При входе в метод поток вызывает метод lock(), тем самым получая
	 * блокировку на ресурс, если же ресурс уже используется другим потоком, то
	 * данный поток будет блокирован до момента освобождения ресурса.
	 * 
	 * После пользования ресурсом поток должен снять с него блокировку, тем
	 * самым открыв доступ к ресурсу уже заблокированным и ожидающим потокам.
	 * 
	 * @throws InterruptedException
	 */
	public void useResource() throws InterruptedException {
		logger.debug("Thread " + Thread.currentThread().getName() + " try to use resource");
		lock.lock();
		logger.debug("Thread " + Thread.currentThread().getName() + " start to use resource");
		try {
			Thread.sleep(SLEEP_TIME);
			logger.debug("Thread " + Thread.currentThread().getName() + " end to use resource");
		} finally {

			lock.unlock();
		}
	}
}
