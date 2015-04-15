
public class TestStartRunningAndNotify {
	static final int NUM_ATHLETES = 10;   

	static int readyCount = 0;  
	static Object ready = new Object();  
	static Object start = new Object();  

	public static void main(String[] args) {  
		Thread[] athletes = new Thread[NUM_ATHLETES];  

		// 创建运动员  
		for (int i = 0; i < athletes.length; ++i) {  
			athletes[i] = new Thread() {  
				@Override  
				public void run() {  
					System.out.println(Thread.currentThread().getName() + " ready!");  

					synchronized (ready) {
						++readyCount;
						ready.notify(); // 通知发令员，“I'm ready!”  
					}  

					// 等待发令枪响  
					try {  
						synchronized (start) {  
							start.wait();  
						}  
					} catch (InterruptedException e) {  
						e.printStackTrace();  
					}
					System.out.println(Thread.currentThread().getName() + " go!");  
				}
			};
		}  

		// 运动员上场  
		for (int i = 0; i < athletes.length; ++i)  
			athletes[i].start();  

		// 主线程充当裁判员角色  
		try {  
			synchronized (ready) {  
				// 等待所有运动员就位  
				while (readyCount < athletes.length) {  
					ready.wait();  
				}  
			}  
		} catch (Exception e) {  
			e.printStackTrace();  
		}  

		System.out.println(Thread.currentThread().getName() + " START!");  
		synchronized (start) {  
			start.notifyAll(); // 打响发令枪  
		}  
	}  
}
