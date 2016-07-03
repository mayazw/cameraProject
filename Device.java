package Project;

public class Device implements IDevice{
	
	private boolean status;

	public void turnOn() {
		status = true;
	}

	public void turnOff() {
		status = false;
	}

	public boolean isOn() {
		return status;
	}
}
