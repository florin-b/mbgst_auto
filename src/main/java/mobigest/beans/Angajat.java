package mobigest.beans;

public class Angajat {

	private String cod;
	private String nume;

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	@Override
	public String toString() {
		return "Angajat [cod=" + cod + ", nume=" + nume + "]";
	}

}
