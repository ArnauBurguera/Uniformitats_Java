
public class Uniformitat {
	
	private int id;
	static int idSeguent = 1;
	private String nom;
	private int unitats;
	
	public Uniformitat(String nom, int unitats) {
		this.id = idSeguent;
		this.idSeguent++;
		this.nom = nom;
		this.unitats = unitats;
	}

	public int getId() {
		return this.id;
	}
	/*public static int getIdSeguent() {
		return this.idSeguent;
	}*/
	public String getNom() {
		return this.nom;
	}
	public int getUnitats() {
		return this.unitats;
	}

	public void setId(int id) {
		this.id = id;
	}
	/*public static void setIdSeguent(int idSeguent) {
		Uniformitat.idSeguent = idSeguent;
	}*/
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setUnitats(int unitats) {
		this.unitats = unitats;
	}
	
	public void addUnitat() {
		this.unitats++;
	}
	public void removeUnitat() {
		this.unitats--;
	}
	
	
	
	
	

}
