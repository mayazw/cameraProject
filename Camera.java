package Project;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Camera<T> implements IDevice{
	
	private ArrayList<Photo> album = new ArrayList<Photo>();
	private List<Photo> sortedAlbum = new ArrayList<Photo>();
	private Device device = new Device();
	private T filter;
	String modelNumber;
	
	public Camera(boolean isOn, String modelNumber, T filter){
		this.modelNumber = modelNumber;
		this.filter = filter;
	}

	public T getFilter() {
		return filter;
	}

	@Override
	public String toString() {
		return "" + filter + "";
	}

	@Override
	public void turnOn() {
		device.turnOn();
		System.out.println("Status: ON");
	}

	public void turnOff() {
		device.turnOff();
		System.out.println("Status: OFF" + '\n');
	}

	public boolean isOn() {
		return device.isOn();
	}
	
	public List<Photo> makePhoto(int photos) throws IllegalArgumentException{
		if (photos < 0){
			throw new IllegalArgumentException("Number is lower than 0");
		}else{
			for (int i=0; i<photos; i++){
				sortedAlbum.add(new Photo("IMG_"+(i+1), Photo.df.format(new Date())));
				//System.out.println("Photo number: " + i);
			}
			//System.out.println("Camera On: " + this.isOn());
			//System.out.println("Model number: " + modelNumber);
		}
		return sortedAlbum;
	}
	
		
	public void printAlbum(){
		Iterator<Photo> it = sortedAlbum.iterator();
		
		while (it.hasNext()){
			Photo description = it.next();
			System.out.println(description.getDescription());
		}
		
		/*for (Photo description: album){
			System.out.println(description.getDescription());
		}*/
	}

}
