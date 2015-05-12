package javaThread;
//test for start!
class Print1_100Astroid extends Thread {  
    @Override  
    public void run() {  
        for (int i = 0; i < 100; ++i) {  
            System.out.print("*");  
            try {  
                Thread.sleep(100);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
}

public class ThreadTest {
	public static void main(String[] args) {
		Thread backThread = new Print1_100Astroid();
		
		backThread.start();
		
		for (int i = 0; i < 100; ++i) {  
            System.out.print("#");  
            try {  
                Thread.sleep(100);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
	}
}