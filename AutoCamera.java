package Project;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class AutoCamera implements Runnable{
	
	private Camera<Object> camera;
	ReentrantLock lock = new ReentrantLock();
	
	public AutoCamera(Camera camera){
		this.camera = camera;
	}
	
	@Override
	public void run() {
		
		lock.lock();
		
		long ID = Thread.currentThread().getId();
		System.out.println("Thread ID: " + ID);
		
		for (int i=0; i<2; i++){       //Number of auto camera loops
			camera.makePhoto(2);	   //Number of photos in each auto loop
		}
		System.out.println();
		
		lock.unlock();
		
		try{
			Thread.sleep(3000); //2 seconds
		}catch(InterruptedException e)
		{}
	}

}
