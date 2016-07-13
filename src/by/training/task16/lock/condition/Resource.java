package by.training.task16.lock.condition;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Resource {
	private static final int SLEEP_TIME = 1000;
	private static final Logger logger = LogManager.getRootLogger();

	private Lock lock = new ReentrantLock();

	private Condition operation1Made = lock.newCondition();
	private Condition operation2Made = lock.newCondition();

	private boolean operation1MadeFlag = false;

	/**
	 * ѕри входе в метод поток вызывает метод lock(), тем самым получа€
	 * блокировку на ресурс, если же ресурс уже используетс€ другим потоком, то
	 * данный поток будет блокирован до момента освобождени€ ресурса. try ≈сли
	 * операци€ operation1 уже произведена, поток после вызова метода await()
	 * блокируетс€ до тех пор пока не выполнитс€ operation2 и на объекте
	 * Condition не вызовитс€ метод signal(). ѕосле выполнени€ operation1 поток
	 * мен€ет значение фалговой переменной на true.
	 */
	public void operation1() {
		logger.debug("Thread " + Thread.currentThread().getName() + " try to make operation1");

		lock.lock();
		try {

			while (operation1MadeFlag) {
				operation2Made.await();
			}
			logger.debug("Thread " + Thread.currentThread().getName() + " start to make operation1");
			Thread.sleep(SLEEP_TIME);

			operation1MadeFlag = true;
			logger.debug("Thread " + Thread.currentThread().getName() + " end to make operation1");

			operation1Made.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {

			lock.unlock();
		}
	}

	/**
	 * то же самое
	 */
	public void operation2() {
		logger.debug("Thread " + Thread.currentThread().getName() + " try to make operation2");

		lock.lock();
		try {

			while (!operation1MadeFlag) {
				operation1Made.await();
			}
			logger.debug("Thread " + Thread.currentThread().getName() + " start to make operation2");
			Thread.sleep(SLEEP_TIME);

			operation1MadeFlag = false;
			logger.debug("Thread " + Thread.currentThread().getName() + " end to make operation2");

			operation2Made.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
