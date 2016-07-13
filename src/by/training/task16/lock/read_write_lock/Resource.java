package by.training.task16.lock.read_write_lock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Resource {
	private static final int SLEEP_TIME = 1000;

	private static final Logger logger = LogManager.getRootLogger();

	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	/**
	 * Поток, читающий данные входит в метод и вызывает метод lock() на
	 * readLock'е, тем самым сообщая о намерении произвести чтение. Если в это
	 * время проивзодится запись данных (т.е. взята блокировка на writeLock'е),
	 * то поток блокируется до окончания операции записи. Однако если другие
	 * потоки так же происводят чтение и вызвали lock() на readLock'е и ни один
	 * поток не проводит запись, то поток не будет блокирован и продолжит
	 * выполнение.
	 */
	public void readOperation() {
		logger.debug("Thread " + Thread.currentThread().getName() + " try to read");

		readWriteLock.readLock().lock();
		try {
			logger.debug("Thread " + Thread.currentThread().getName() + " start to read");
			Thread.sleep(SLEEP_TIME);
			logger.debug("Thread " + Thread.currentThread().getName() + " end to read");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {

			readWriteLock.readLock().unlock();
		}
	}

	/**
	 * Поток, который хочет записсать данные в ресурс при входе в метод вызывает
	 * lock() на writeLock'е, тем самым блокируя доступ к ресурсу для всех
	 * читающих потоков и потоков, которые так же хотят записать данные.
	 */
	public void writeOperation() {
		logger.debug("Thread " + Thread.currentThread().getName() + " try to write");

		readWriteLock.writeLock().lock();
		try {
			logger.debug("Thread " + Thread.currentThread().getName() + " start to write");
			Thread.sleep(SLEEP_TIME);
			logger.debug("Thread " + Thread.currentThread().getName() + " end to write");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {

			readWriteLock.writeLock().unlock();
		}
	}
}
