import java.util.ArrayList;

public class Treballador {
	
	private int id;
	static int idSeguent = 1;
	private String nom;
	private String cognom;
	private String correu;
	private ArrayList<String> uniforme = new ArrayList<String>();
	
	public Treballador(String nom, String cognom, String correu) {
		this.id = idSeguent;
		this.idSeguent++;
		this.nom = nom;
		this.cognom = cognom;
		this.correu = correu;
		//this.uniformitat; A veure si xurruca sense declararlo al constructor
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
	public String getCognom() {
		return this.cognom;
	}
	public String getCorreu() {
		return this.correu;
	}
	public ArrayList<String> getUniforme() {
		return this.uniforme;
	}

	
	/*public static void setIdSeguent(int idSeguent) {
		Treballador.idSeguent = idSeguent;
	}*/
	/*public void setUniformitat(ArrayList<Uniformitat> uniformitat) {
		this.uniformitat = uniformitat;
	}*/
	public void setId(int id) {
		this.id = id;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setCognom(String cognom) {
		this.cognom = cognom;
	}
	public void setCorreu(String correu) {
		this.correu = correu;
	}
	
	
	public void addUniformitat(String uniformitat) {
		this.uniforme.add(uniformitat);
	}
	public void removeUniformitat(String uniformitat) {
		this.uniforme.remove(uniformitat);
	}
	
	
	public String informar() {
		return"El meu nom és " + this.nom + " " + this.cognom + ", sóc un treballador amb la id " + this.id 
				+ " i tinc: " + this.uniforme + ".";
	}
	
	
	
	
	
	
	

}
