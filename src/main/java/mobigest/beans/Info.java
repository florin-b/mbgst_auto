package mobigest.beans;

public class Info {

	private boolean succes;
	private String infoText;
	private String infoTime;

	public boolean isSucces() {
		return succes;
	}

	public void setSucces(boolean succes) {
		this.succes = succes;
	}

	public String getInfoText() {
		return infoText;
	}

	public void setInfoText(String infoText) {
		this.infoText = infoText;
	}

	public String getInfoTime() {
		return infoTime;
	}

	public void setInfoTime(String infoTime) {
		this.infoTime = infoTime;
	}

	@Override
	public String toString() {
		return "Info [succes=" + succes + ", infoText=" + infoText + ", infoTime=" + infoTime + "]";
	}

	
	
}
