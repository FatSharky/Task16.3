package by.training.task16.synchronizer.cyclic_barrier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Resource {
	private static final int SLEEP_TIME = 1000;
	private static final int COUNT = 5;
	private static final Logger logger = LogManager.getRootLogger();
	/**
	 * ��������� ������ CyclicBarrier �� ��������� ������ 5, ��� �������� ���-��
	 * ��������������� ������� � ������������ �����. ������ ��������� �����
	 * ������������� ����� ������� �������� ������ �������.
	 */
	private CyclicBarrier cyclicBarrier = new CyclicBarrier(COUNT);

	/**
	 * ����� ����������� �������� operation1 ����� �������� ����� await() �
	 * ��������� ������� �� ������� - ��� � ���� ����� �������������. �����
	 * ���������� ���� ����� ����� ��������, ������� ��������� � ������
	 * ���������� ���� ������. �� ��� ��� ������ ����� �����������.
	 * 
	 * @throws InterruptedException
	 * @throws BrokenBarrierException
	 */
	public void operation1() throws InterruptedException, BrokenBarrierException {
		logger.debug("Thread " + Thread.currentThread().getName() + " start the operation1");
		Thread.sleep(SLEEP_TIME);
		logger.debug("Thread " + Thread.currentThread().getName() + " end the operation1");
		cyclicBarrier.await();
	}

	public void operation2() throws InterruptedException {
		logger.debug("Thread " + Thread.currentThread().getName() + " start the operation2");
		Thread.sleep(SLEEP_TIME);
		logger.debug("Thread " + Thread.currentThread().getName() + " end the operation2");
	}
}
