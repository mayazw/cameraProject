package Project;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Photo implements Comparable<Photo>{
	
	private String text;
	private String fileName;
	protected Date date;
	public static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	public Photo(String text){
		this.text = text;
	}
	
	public Photo(String fileName, String date){
		this.fileName = fileName;
		
		try {
			this.date = df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public String getDescription(){
		return ("Name: " + fileName + "\nDate: " + df.format(date));
	}
	

	public static void getAlbumDescription(List<Photo> album){   //////Arrange all photos by date
		Collections.sort(album);
		
		for (int i=0; i<album.size()-1; i++){
			String dateName = df.format(album.get(i).date).replaceAll("/", "_");
			DataOutputStream photoOutput = main.createFile("C:\\Users\\anat\\workspace\\Project\\photos\\" + dateName + ".txt");
			
			try {
				photoOutput.writeUTF(df.format(album.get(i).date) + System.lineSeparator());
				photoOutput.writeUTF(System.lineSeparator());
				for(Photo photo: album){
					if ((df.format(photo.date)).equals((df.format(album.get(i).date)))){
						photoOutput.writeUTF(photo.fileName + System.lineSeparator());
					}
				}
				photoOutput.close();
			} catch (IOException e) {
				System.out.println("An I/O Exception Occurred");
				e.printStackTrace();
			}
		}
	}

	/*public static void getAlbumDescription(List<Photo> album, String date){   //////Arrange photos by the selected date
	    String dateName = date.replaceAll("/", "_");
		DataOutputStream photoOutput = main.createFile("C:\\Users\\anat\\workspace\\Project\\photos\\" + dateName + ".txt");
		
		try {
			photoOutput.writeUTF(date + System.lineSeparator());
			photoOutput.writeUTF(System.lineSeparator());
			for(Photo photo: album){
				if ((df.format(photo.date)).equals(date)){
					photoOutput.writeUTF(photo.fileName);
					photoOutput.writeUTF(System.lineSeparator());
				}
			}
			photoOutput.close();
		} catch (IOException e) {
			System.out.println("An I/O Exception Occurred");
			e.printStackTrace();
		}
	}*/
	
	
	@Override
	public int compareTo(Photo photo) {
		Date p1 = this.date;
		Date p2 = photo.date;
		
		if(p1.after(p2)){
			return 1;
		}else if(p1.before(p2)){
			return -1;
		}else{
			return 0;
		}
	}

}
