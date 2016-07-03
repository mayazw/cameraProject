package Project;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class main {

	public static void main(String[] args) throws InterruptedException{
		
		Camera<DiffusionFilter> camera1 = new Camera<>(true, "Canon", new DiffusionFilter());
		//Camera<PolarizerFilter> camera2 = new Camera<>(true, "Samsung", new PolarizerFilter());

		//List<Photo> album = getAlbum();
		//Photo.getAlbumDescription(album);
		
		/*Collections.sort(album);
		
		for (int i=0; i<album.size()-1; i++){
			if (album.get(i).date != album.get(i+1).date){
					Photo.getAlbumDescription(album, Photo.df.format(album.get(i).date));
			}
		}*/
		
		//addToPool();
		
		List<Photo> camera1Album = camera1.makePhoto(1);
		System.out.println(camera1Album.get(0).toString());
		//Photo.getAlbumDescription(camera1Album);
		//camera1.printAlbum();
		
		//camera2.makePhoto(2);
		//camera2.printAlbum();
		
		//camera1.turnOn();
		//takeOne(camera1);

		//camera2.turnOn();
		//takeOne(camera2);
		
		/*for (int i=0; i<1; i++){          //Number of times the thread is running
			new Thread(new AutoCamera(camera1)).start();
			new Thread(new AutoCamera(camera2)).start();
			Thread.sleep(2000);
		}*/
				
	}
	
	private static List<Photo> getAlbum(){
		List<Photo> album = new ArrayList<>();
		
		album.add(new Photo("IMG_0109", "15/06/2016"));
		album.add(new Photo("IMG_0182", "13/02/2016"));
		album.add(new Photo("IMG_0381", "15/06/2016"));
		album.add(new Photo("IMG_0540", "20/05/2016"));
		album.add(new Photo("IMG_0618", "13/02/2016"));
		
		return album;
	}
	
	protected static DataOutputStream createFile(String fileName){
		try {
			File listOfNames = new File(fileName);
			DataOutputStream infoToWrite = new DataOutputStream(
					new BufferedOutputStream(
							new FileOutputStream(listOfNames)));
			return infoToWrite;
			
		} catch (FileNotFoundException e) {
			System.out.println("An I/O Error Occurred");
			
			System.exit(0);
		}
		return null;
	}
	
	private static void takeOne(Camera<? extends IFilter> camera){
		try{
			System.out.println(camera.getFilter());
		}catch(IllegalArgumentException e){
			System.out.println("Error: Number of photos must be more than 0");
		}finally{
			camera.turnOff();
		}

	}
	
	public static void addToPool(){
		
		ScheduledThreadPoolExecutor eventPool = new ScheduledThreadPoolExecutor(2);
		
		eventPool.scheduleAtFixedRate(new AutoCamera(new Camera<>(true, "Canon", new DiffusionFilter())), 0, 3, SECONDS);
		eventPool.scheduleAtFixedRate(new AutoCamera(new Camera<>(true, "Samsung", new PolarizerFilter())), 3, 3, SECONDS);
	
		System.out.println("Number of Threads: " +Thread.activeCount());
		
		Thread[] listOfThreads = new Thread[Thread.activeCount()];
		System.out.println();
		
		Thread.enumerate(listOfThreads);
		
		for(Thread i: listOfThreads){    //print thread Id
			System.out.println("Thread ID: " + i.getId());
		}
		
		eventPool.shutdown();
		
		try{
			Thread.sleep(2000);
		}catch(InterruptedException e){}
		
	}

}
