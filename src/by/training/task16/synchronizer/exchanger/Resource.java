package by.training.task16.synchronizer.exchanger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.Exchanger;

public class Resource {
	private static final int SLEEP_TIME = 1000;
	private static final Logger logger = LogManager.getRootLogger();
	/**
	 * Создается объект Exchanger с параметризированным типом Integer, через
	 * этот объект потки в последствии смоогут обмениваться ссылками на объекты
	 * класса Integer в определенной синхронизируемой точке.
	 */
	private Exchanger<Integer> exchanger = new Exchanger<>();

	/**
	 * При вызове метода exchange() поток либо ожидает прибытия другого потока
	 * для обмена данными, либо если второй поток уже ожидает производит обмен
	 * ссылками на объекты класса Integer.
	 * 
	 * @throws InterruptedException
	 */
	public void useResource() throws InterruptedException {
		logger.debug("Thread " + Thread.currentThread().getName() + " start to use resource");
		Thread.sleep(SLEEP_TIME);

		Random random = new Random();
		int valueToExchange = random.nextInt(10);
		logger.debug("Thread " + Thread.currentThread().getName() + " ready to exchange value " + valueToExchange);
		int valueFromExchange = exchanger.exchange(valueToExchange);
		logger.debug("Thread " + Thread.currentThread().getName() + " get the exchange value " + valueFromExchange);

		Thread.sleep(SLEEP_TIME);
		logger.debug("Thread " + Thread.currentThread().getName() + " end to use resource");
	}
}
