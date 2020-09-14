package edu.hunau.os.PC;

import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class PCDemo {

	JFrame frame;

	public static void main(String[] args) {

		JOptionPane.showMessageDialog(null, "请注意,窗口绘制适用于桌面100%缩放");
		JOptionPane.showMessageDialog(null, "若显示不正常，请前往系统显示设置页面缩放为100%");

		Integer productorNum = Integer.parseInt(JOptionPane.showInputDialog("请输入生产者数量"));
		Integer consumerNum = Integer.parseInt(JOptionPane.showInputDialog("请输入消费者数量"));
		Integer createNum = Integer.parseInt(JOptionPane.showInputDialog("请输入每个生产者要生产的物品数量"));
		Integer takeNum = Integer.parseInt(JOptionPane.showInputDialog("请输入每个消费者要消费的物品数量"));
		Integer speed = Integer.parseInt(JOptionPane.showInputDialog("请输入生产消费所执行的速度(2000代表2秒一条信息)"));

		Resources resource = new Resources(speed);
		Producer producer = new Producer(resource, createNum);
		Consumer consumer = new Consumer(resource, takeNum);
		for (int i = 0; i < productorNum; i++) {
			new Thread(producer, i + "号生产者").start();
		}
		for (int i = 0; i < consumerNum; i++) {
			new Thread(consumer, i + "号消费者").start();
		}
	}

	/**
	 * Create the application.
	 */
	public PCDemo(JLabel lblSasa, JTextArea textArea) {
		initialize(lblSasa, textArea);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JLabel lblSasa, JTextArea textArea) {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.getContentPane().setLayout(null);
		lblSasa.setBounds(0, 0, 800, 22);
		lblSasa.setFont(new Font("楷体", Font.BOLD, 20));
		lblSasa.setHorizontalAlignment(SwingConstants.CENTER);
		lblSasa.setVerticalAlignment(SwingConstants.TOP);
		lblSasa.setForeground(Color.WHITE);
		frame.getContentPane().add(lblSasa);

		textArea.setFont(new Font("幼圆", Font.BOLD, 17));
		textArea.setForeground(Color.WHITE);
		textArea.setBackground(Color.BLACK);
		textArea.setBounds(0, 30, 800, 245);

		frame.getContentPane().add(textArea);

		JScrollPane ScrollPane = new JScrollPane(textArea);
		ScrollPane.setBounds(0, 30, 800, 235);

		frame.getContentPane().add(ScrollPane);

		frame.setBounds(100, 100, 800, 325);
		frame.setLocationByPlatform(true);
		int x = (int) (Math.random() * 1300);
		int y = (int) (Math.random() * 700);
		System.out.println(x + ":" + y);
		frame.setLocation(x, y);
		frame.setDefaultCloseOperation(2);
	}

}

class Producer implements Runnable {
	private Resources resource;
	private Integer createNum;

	public Producer(Resources resource, Integer createNum) {
		this.resource = resource;
		this.createNum = createNum;
	}

	@Override
	public void run() {
		JTextArea textArea = new JTextArea();
		JLabel lb = new JLabel();
		PCDemo window = new PCDemo(lb, textArea);
		window.frame.setVisible(true);
		int ptotal;
		long start = System.currentTimeMillis();
		for (ptotal = 0; ptotal < createNum; ptotal++) {
			resource.create(textArea);
			lb.setText("当前生产者生产了" + (ptotal + 1) + "件物品/-缓冲区占用情况:【" + (resource.map.size()) + "】总大小:【8】)");
		}
		long end = System.currentTimeMillis();
		textArea.append("生产者已完毕！！！\n");
		threadPrintInfo(textArea, start, end, resource);
		lb.setText("当前生产者生产了" + (ptotal) + "件物品/-缓冲区占用情况:【" + (resource.map.size()) + "】总大小:【8】)");

		System.out.println("生产者已完毕，当前缓冲区：");
		resource.map.forEach((k, v) -> {
			System.out.print("-->【YY-" + v + "】");
		});
		System.out.println();

	}

	static void threadPrintInfo(JTextArea textArea, long start, long end, Resources resource) {
		textArea.append("" + Thread.currentThread().getName() + "线程花费了:" + (end - start) + "ms\n当前缓冲区:");
		resource.map.forEach((k, v) -> {
			if (!resource.pc) {
				textArea.append("[Y-" + v + "]");
				resource.pc = true;
			} else
				textArea.append("->[Y-" + v + "]");
		});
		resource.pc = false;
		textArea.append("\n关闭所有窗口即退出程序!");
		textArea.selectAll();
	}
}

class Consumer implements Runnable {
	private Resources resource;
	private Integer takeNum;

	public Consumer(Resources resource, Integer takeNum) {
		this.resource = resource;
		this.takeNum = takeNum;
	}

	@Override
	public void run() {
		JTextArea textArea = new JTextArea();
		JLabel lb = new JLabel();

		PCDemo window = new PCDemo(lb, textArea);
		window.frame.setVisible(true);
		int ctotal;
		long start = System.currentTimeMillis();
		for (ctotal = 0; ctotal < takeNum; ctotal++) {
			resource.take(textArea);
			lb.setText("当前消费者买了【" + (ctotal + 1) + "】件物品)");
		}
		long end = System.currentTimeMillis();
		textArea.append("消费者已完毕！！！\n");
		Producer.threadPrintInfo(textArea, start, end, resource);
		lb.setText("当前消费者买了【" + (ctotal) + "】件物品) 缓冲区占用情况:【" + (resource.map.size()) + "】)");

		System.out.println("消费者已完毕，当前缓冲区:");
		resource.map.forEach((k, v) -> {
			System.out.print("-->【Y-" + v + "】");
		});
		System.out.println();

	}

}

class Resources {
	private int i = 0;
	private int pcount;
	private int ccount;
	private Integer speed = 1000;
	Map<Integer, Integer> map = new HashMap<>();
	boolean pc;
	private int index;
	Lock lock = new ReentrantLock();
	Condition conProducer = lock.newCondition();
	Condition conConsumer = lock.newCondition();

	public Resources(Integer speed) {
		this.speed = speed;
	}

	public void create(JTextArea textArea) {
		lock.lock();
		try {
			while (i < 0 || map.size() > 7) {
				textArea.append(("======[Y-" + pcount + "]") + "无法加入缓冲区======\n缓冲区已满:");
				map.forEach((k, v) -> {
					if (!pc) {
						textArea.append("[Y-" + v + "]");
						pc = true;
					} else
						textArea.append("->[Y-" + v + "]");
				});
				pc = false;
				textArea.append("\n关闭所有窗口即退出程序!\n");
				textArea.selectAll();
				System.out.print((pcount) + "缓冲区已满,等待消费：");
				map.forEach((k, v) -> {
					System.out.print("-->【Y-" + v + "】");
				});
				System.out.println();

				conProducer.await();
			}
			index = pcount % 8;
			map.put(index, pcount);
			textArea.append(Thread.currentThread().getName() + "\t在缓冲区：[" + index + "]\t生产物品:\t[Y-" + pcount + "]\n");
			System.out.print(Thread.currentThread().getName() + "\t在缓冲区：[" + index + "]\t生产物品:\t[Y-" + pcount + "]\n");
			i++;
			pcount++;
			Thread.sleep(speed);
			conConsumer.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void take(JTextArea textArea) {
		lock.lock();
		try {
			while (i <= 0) {
				textArea.append(Thread.currentThread().getName() + "\t发现缓冲区为0，走开了...\n当前缓冲区情况:NULL\n");
				textArea.selectAll();
				System.out.print(Thread.currentThread().getName() + "\t发现缓冲区为0，走开了...\n当前缓冲区情况:NULL\n");
				conConsumer.await();
			}
			index = ccount % 8;
			int value = map.get(index);
			map.remove(index);
			textArea.append(Thread.currentThread().getName() + "\t在缓冲区：[" + index + "]  消费物品:\t[Y-" + value + "]\n");
			System.out.println(Thread.currentThread().getName() + "\t在缓冲区：[" + index + "]消费【Y-" + value + "】号物品");
			i--;
			ccount++;
			Thread.sleep(speed);
			conProducer.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}