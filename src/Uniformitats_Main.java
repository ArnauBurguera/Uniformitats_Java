import java.util.ArrayList;
import java.util.Scanner;

public class Uniformitats_Main {

	static Uniformitat[] llistaUniformitats = {new Uniformitat("jaqueta",50), new Uniformitat("pantaló",50), new Uniformitat("sabates",50), new Uniformitat("mascaretes",50), new Uniformitat("guants",50)};
	static ArrayList<Treballador> treballadors = new ArrayList<Treballador>();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int opcio = -1;
		String nom = "";
		String cognom = "";
		String correu = "";

		//Treballadors per defecte
		crearTreballador("Joan","Pera","joanpera@gmail.com");
		crearTreballador("Quim","Cigaló","qcigalo@gmail.com");
		crearTreballador("Fabio Facundo","Herreira","erfacu@gmail.com");

		do {
			System.out.println("GESTOR D'EMPLEATS I UNIFORMES:\n"
					+ "0.Sortir de l'aplicació\n"
					+ "1.Registrar treballador\n"
					+ "2.Editar treballador\n"
					+ "3.Eliminar treballador\n"
					+ "4.Retirar uniformitat del magatzem\n"
					+ "5.Retornar uniformitat al magatzem\n"
					+ "6.Veure existències");

			opcio = sc.nextInt();

			while(opcio < 0 || opcio > 6) {
				System.out.print("El nombre introduït no és correcte.\nTorna-ho a intentar: ");
				opcio = sc.nextInt();
			}

			switch(opcio) {
			case 1:
				System.out.print("Escriu el nom del treballador: ");
				sc.nextLine();
				nom = sc.nextLine();
				System.out.print("Cognom: ");
				cognom = sc.nextLine();
				System.out.print("Email: ");
				correu = sc.nextLine();

				while(posicioLlistatTreballadors(nom,cognom) != -1) {
					System.out.print("Ja existeix un treballador amb aquest nom i cognoms.\nTorna-ho a provar.\n");
					System.out.print("Nom: ");
					nom = sc.nextLine();
					System.out.print("Cognom: ");
					cognom = sc.nextLine();
				}

				if(crearTreballador(nom,cognom,correu)){
					System.out.println("El treballador " + nom + " " + cognom + " ha estat donat d'alta correctament.");
				}else {
					System.out.println("El treballador " + nom + " " + cognom + " no s'ha pogut donar d'alta corectament. "
							+ "No hi ha estoc d'uniformes.");	
				}
				System.out.println("");
				break;
			case 2:
				System.out.println("Escriu el nom del treballador que vols editar del següent llistat: ");

				llistaTreballadors();

				System.out.print("Nom: ");
				sc.nextLine();
				nom = sc.nextLine();
				System.out.print("Cognom: ");
				cognom = sc.nextLine();

				while(posicioLlistatTreballadors(nom,cognom) == -1) {
					System.out.print("No existeix cap treballador amb aquest nom i cognoms.\nTorna-ho a intentar.\n");
					System.out.print("Nom: ");
					nom = sc.nextLine();
					System.out.print("Cognom: ");
					cognom = sc.nextLine();
				}

				System.out.println("");
				menuEditor(nom, cognom);
				System.out.println("");
				break;
			case 3:
				System.out.println("Escriu el nom del treballador que vols donar de baixa del següent llistat: ");

				llistaTreballadors();

				System.out.print("Nom: ");
				sc.nextLine();
				nom = sc.nextLine();
				System.out.print("Cognom: ");
				cognom = sc.nextLine();

				while(posicioLlistatTreballadors(nom,cognom) == -1) {
					System.out.print("No existeix cap treballador amb aquest nom i cognoms.\nTorna-ho a provar.\n");
					System.out.print("Nom: ");
					nom = sc.nextLine();
					System.out.print("Cognom: ");
					cognom = sc.nextLine();
				}

				doanrBaixaTreballador(nom,cognom);
				System.out.println("El treballador " + nom + " " + cognom + " ha estat donat de baixa correctament.\n");
				break;
			case 4:
				menuRetirar();
				System.out.println("");
				break;
			case 5:
				System.out.println("RETORNAR UNIFORMITAT:\n"
						+ "1.Segons tipus\n"
						+ "2.Segons treballador");

				opcio = sc.nextInt();

				while(opcio < 1 || opcio > 2) {
					System.out.print("El nombre introduït no és correcte.\nTorna-ho a intentar: ");
					opcio = sc.nextInt();
				}

				switch(opcio) {
				case 1:
					retornarSegonsTipus();
					break;
				case 2:
					retornarSegonsTreballador();
					break;
				}
				System.out.println("");
				break;
			case 6:
				for(Uniformitat uniformitats : llistaUniformitats) {
					System.out.println(uniformitats.getNom() + " : " + uniformitats.getUnitats() + " unitats.");
				}

				System.out.println("");

				break;
			}

		} while(opcio > 0);

		System.out.print("A reveure!");
	}

	public static int posicioLlistaUniformitats(String nom) {
		int i = 0, posicio = -1;

		while(i < llistaUniformitats.length) {
			if(llistaUniformitats[i].getNom().equalsIgnoreCase(nom)) {
				posicio = i;
				i = llistaUniformitats.length;
			}
			i++;
		}
		return posicio;
	}

	public static int posicioLlistatTreballadors(String nom, String cognom) {
		int i = 0, posicio = -1;

		while(i < treballadors.size()) {
			if(treballadors.get(i).getNom().equalsIgnoreCase(nom) & treballadors.get(i).getCognom().equalsIgnoreCase(cognom)) {
				posicio = i;
				i = treballadors.size();
			}
			i++;
		}
		return posicio;
	}

	public static boolean teUniformitat(Treballador treballador, String uniformitat) {
		int i = 0;
		boolean teUniformitat = false;

		while(i < treballador.getUniforme().size()) {
			if(treballador.getUniforme().get(i).equalsIgnoreCase(uniformitat)) {
				teUniformitat = true;
				i = treballador.getUniforme().size();
			}
			i++;
		}
		return teUniformitat;
	}

	public static boolean crearTreballador(String nom, String cognom, String correu) {

		int unitatsJaquetes = llistaUniformitats[posicioLlistaUniformitats("jaqueta")].getUnitats();
		int unitatsPantalons = llistaUniformitats[posicioLlistaUniformitats("pantaló")].getUnitats();
		boolean creatCorrectament = false;
		Treballador treballador = null;

		treballador = new Treballador(nom,cognom,correu);
		if(unitatsJaquetes > 0 & unitatsPantalons > 0) {
			uniformar(treballador,"jaqueta");
			uniformar(treballador,"pantaló");
			treballadors.add(treballador);
			creatCorrectament = true;
		}
		return creatCorrectament;
	}

	public static void doanrBaixaTreballador(String nom, String cognom) {

		boolean jaqueta = false, pantalo = false, sabates = false, mascareta = false, guants = false;
		Treballador treballadorTrobat = null;
		treballadorTrobat = treballadors.get(posicioLlistatTreballadors(nom,cognom));
		int unitatsUniforme = treballadorTrobat.getUniforme().size();

		for(String uniformitat : treballadorTrobat.getUniforme()) {
			if(uniformitat.equalsIgnoreCase("jaqueta")) {
				llistaUniformitats[posicioLlistaUniformitats("jaqueta")].addUnitat();
				jaqueta = true;
			}else if(uniformitat.equalsIgnoreCase("pantaló")) {
				llistaUniformitats[posicioLlistaUniformitats("pantaló")].addUnitat();
				pantalo = true;
			}else if(uniformitat.equalsIgnoreCase("sabates")) {
				llistaUniformitats[posicioLlistaUniformitats("sabates")].addUnitat();
				sabates = true;
			}else if(uniformitat.equalsIgnoreCase("mascaretes")) {
				llistaUniformitats[posicioLlistaUniformitats("mascaretes")].addUnitat();
				mascareta = true;
			}else if(uniformitat.equalsIgnoreCase("guants")) {
				llistaUniformitats[posicioLlistaUniformitats("guants")].addUnitat();
				guants = true;
			}
		}

		for(int i = 1; i <= unitatsUniforme; i++){
			if(jaqueta) {
				treballadorTrobat.removeUniformitat("jaqueta");
			}else if(pantalo) {
				treballadorTrobat.removeUniformitat("pantaló");
			}else if(sabates) {
				treballadorTrobat.removeUniformitat("sabates");
			}else if(mascareta) {
				treballadorTrobat.removeUniformitat("mascaretes");
			}else if(guants) {
				treballadorTrobat.removeUniformitat("guants");
			}
		}


	}

	public static boolean uniformar(Treballador treballador, String uniformitat) {

		boolean uniformatCorrectament = false;

		if(uniformitat.equalsIgnoreCase("jaqueta") & (!teUniformitat(treballador,uniformitat)) & (llistaUniformitats[posicioLlistaUniformitats("jaqueta")].getUnitats() > 0)) {
			treballador.addUniformitat("jaqueta");
			llistaUniformitats[posicioLlistaUniformitats("jaqueta")].removeUnitat();
			uniformatCorrectament = true;
		}else if(uniformitat.equalsIgnoreCase("pantaló") & (!teUniformitat(treballador,uniformitat)) & (llistaUniformitats[posicioLlistaUniformitats("pantaló")].getUnitats() > 0)) {
			treballador.addUniformitat("pantaló");
			llistaUniformitats[posicioLlistaUniformitats("pantaló")].removeUnitat();
			uniformatCorrectament = true;
		}else if(uniformitat.equalsIgnoreCase("sabates") & (!teUniformitat(treballador,uniformitat)) & (llistaUniformitats[posicioLlistaUniformitats("sabates")].getUnitats() > 0)) {
			treballador.addUniformitat("sabates");
			llistaUniformitats[posicioLlistaUniformitats("sabates")].removeUnitat();
			uniformatCorrectament = true;
		}else if(uniformitat.equalsIgnoreCase("mascaretes") & (!teUniformitat(treballador,uniformitat)) & (llistaUniformitats[posicioLlistaUniformitats("mascaretes")].getUnitats() > 0)) {
			treballador.addUniformitat("mascaretes");
			llistaUniformitats[posicioLlistaUniformitats("mascaretes")].removeUnitat();
			uniformatCorrectament = true;
		}else if(uniformitat.equalsIgnoreCase("guants") & (!teUniformitat(treballador,uniformitat)) & (llistaUniformitats[posicioLlistaUniformitats("guants")].getUnitats() > 0)) {
			treballador.addUniformitat("guants");
			llistaUniformitats[posicioLlistaUniformitats("guants")].removeUnitat();
			uniformatCorrectament = true;
		}
		return uniformatCorrectament;
	}

	public static void menuEditor(String nomInicial, String cognomInicial) {
		int opcio = -1;
		String nom = nomInicial;
		String cognom = cognomInicial;
		String nouNom = "";
		String nouCognom = "";
		String nouCorreu = "";


		do {
			System.out.println("EDITOR DE TREBALLADORS [" + nom + " " + cognom + "]:\n"
					+ "0.Sortir del editor\n"
					+ "1.Editar nom\n"
					+ "2.Editar cognom\n"
					+ "3.Editar correu\n"
					+ "(Per retirar uniformitats dirigiu-vos al punt 4 del menú principal)");

			opcio = sc.nextInt();

			while(opcio < 0 || opcio > 3) {
				System.out.print("El nombre introduït no és correcte.\nTorna-ho a intentar: ");
				opcio = sc.nextInt();
			}

			switch(opcio) {
			case 1:
				System.out.print("Escriu el nom correcte del treballador.\n");
				System.out.print("Nom: ");
				sc.nextLine();
				nouNom = sc.nextLine();
				treballadors.get(posicioLlistatTreballadors(nom,cognom)).setNom(nouNom);
				System.out.println("El nom del treballador " + nom + " " + cognom + " ha passat a ser " + nouNom + ".\n");
				nom = nouNom;
				break;
			case 2:
				System.out.print("Escriu el cognom correcte del treballador.\n");
				System.out.print("Cognom: ");
				sc.nextLine();
				nouCognom = sc.nextLine();
				treballadors.get(posicioLlistatTreballadors(nom,cognom)).setCognom(nouCognom);
				System.out.println("El cognom del treballador " + nom + " " + cognom + " ha passat a ser " + nouCognom + ".\n");
				cognom = nouCognom;
				break;
			case 3:
				System.out.print("Escriu l'adreça de correu electrònica correcta: ");
				sc.nextLine();
				nouCorreu = sc.nextLine();
				treballadors.get(posicioLlistatTreballadors(nom,cognom)).setCorreu(nouCorreu);
				System.out.println("El correu electrònic del treballador " + nom + " " + cognom + " ha passat a ser " + nouCorreu + ".\n");
				break;
			}

		} while(opcio > 0);
	}	

	public static void menuRetirar() {
		int opcio = -1;
		String nom = "";
		String cognom = "";
		Treballador treballadorTrobat = null;

		do {
			System.out.println("Quina uniformitat vols retirar del magatzem?\n"
					+ "1.Jaquetes\n"
					+ "2.Pantalons\n"
					+ "3.Sabates\n"
					+ "4.Mascaretes\n"
					+ "5.Guants\n"
					+ "0.Tornar al menú principal");

			opcio = sc.nextInt();

			while(opcio < 0 || opcio > 5) {
				System.out.print("El nombre introduït no és correcte.\nTorna-ho a intentar: ");
				opcio = sc.nextInt();
			}

			System.out.println("Llistat de teballadors:");

			llistaTreballadors();

			System.out.println("");

			switch(opcio) {
			case 1:
				System.out.println("Escriu el nom del treballador al que li vols assignar una jaqueta. ");
				System.out.print("Nom:");
				sc.nextLine();
				nom = sc.nextLine();
				System.out.print("Cognom: ");
				cognom = sc.nextLine();

				while(posicioLlistatTreballadors(nom,cognom) == -1) {
					System.out.print("No existeix cap treballador amb aquest nom i cognoms.\nTorna-ho a provar.\n");
					System.out.print("Nom: ");
					nom = sc.nextLine();
					System.out.print("Cognom: ");
					cognom = sc.nextLine();
				}

				treballadorTrobat = treballadors.get(posicioLlistatTreballadors(nom,cognom));   
				if(teUniformitat(treballadorTrobat,"jaqueta")) {
					System.out.println("El treballador " + nom + " " + cognom + " ja disposa de jaqueta.");
				}else {
					llistaUniformitats[posicioLlistaUniformitats("jaqueta")].removeUnitat();
					treballadorTrobat.addUniformitat("jaqueta");
					System.out.println("El treballador " + nom + " " + cognom + " ha rebut una jaqueta.");
				}
				System.out.println("");
				break;
			case 2:
				System.out.println("Escriu el nom del treballador al que li vols assignar uns pantalons. ");
				System.out.print("Nom:");
				sc.nextLine();
				nom = sc.nextLine();
				System.out.print("Cognom: ");
				cognom = sc.nextLine();

				while(posicioLlistatTreballadors(nom,cognom) == -1) {
					System.out.print("No existeix cap treballador amb aquest nom i cognoms.\nTorna-ho a provar.\n");
					System.out.print("Nom: ");
					nom = sc.nextLine();
					System.out.print("Cognom: ");
					cognom = sc.nextLine();
				}

				treballadorTrobat = treballadors.get(posicioLlistatTreballadors(nom,cognom));   
				if(teUniformitat(treballadorTrobat,"pantaló")) {
					System.out.println("El treballador " + nom + " " + cognom + " ja disposa de pantalons.");
				}else {
					llistaUniformitats[posicioLlistaUniformitats("pantaló")].removeUnitat();
					treballadorTrobat.addUniformitat("pantaló");
					System.out.println("El treballador " + nom + " " + cognom + " ha rebut uns pantalons.");
				}
				System.out.println("");
				break;
			case 3:
				System.out.println("Escriu el nom del treballador al que li vols assignar un parell de sabates. ");
				System.out.print("Nom:");
				sc.nextLine();
				nom = sc.nextLine();
				System.out.print("Cognom: ");
				cognom = sc.nextLine();

				while(posicioLlistatTreballadors(nom,cognom) == -1) {
					System.out.print("No existeix cap treballador amb aquest nom i cognoms.\nTorna-ho a provar.\n");
					System.out.print("Nom: ");
					nom = sc.nextLine();
					System.out.print("Cognom: ");
					cognom = sc.nextLine();
				}

				treballadorTrobat = treballadors.get(posicioLlistatTreballadors(nom,cognom));   
				if(teUniformitat(treballadorTrobat,"sabates")) {
					System.out.println("El treballador " + nom + " " + cognom + " ja disposa de pantalons.");
				}else {
					llistaUniformitats[posicioLlistaUniformitats("sabates")].removeUnitat();
					treballadorTrobat.addUniformitat("sabates");
					System.out.println("El treballador " + nom + " " + cognom + " ha rebut unes sabates.");
				}
				System.out.println("");
				break;
			case 4:
				System.out.println("Escriu el nom del treballador al que li vols assignar una mascareta. ");
				System.out.print("Nom:");
				sc.nextLine();
				nom = sc.nextLine();
				System.out.print("Cognom: ");
				cognom = sc.nextLine();

				while(posicioLlistatTreballadors(nom,cognom) == -1) {
					System.out.print("No existeix cap treballador amb aquest nom i cognoms.\nTorna-ho a provar.\n");
					System.out.print("Nom: ");
					nom = sc.nextLine();
					System.out.print("Cognom: ");
					cognom = sc.nextLine();
				}

				treballadorTrobat = treballadors.get(posicioLlistatTreballadors(nom,cognom));   
				if(teUniformitat(treballadorTrobat,"mascaretes")) {
					System.out.println("El treballador " + nom + " " + cognom + " ja disposa de mascareta.");
				}else {
					llistaUniformitats[posicioLlistaUniformitats("mascaretes")].removeUnitat();
					treballadorTrobat.addUniformitat("mascaretes");
					System.out.println("El treballador " + nom + " " + cognom + " ha rebut una mascareta.");
				}
				System.out.println("");
				break;
			case 5:
				System.out.println("Escriu el nom del treballador al que li vols assignar uns guants. ");
				System.out.print("Nom:");
				sc.nextLine();
				nom = sc.nextLine();
				System.out.print("Cognom: ");
				cognom = sc.nextLine();

				while(posicioLlistatTreballadors(nom,cognom) == -1) {
					System.out.print("No existeix cap treballador amb aquest nom i cognoms.\nTorna-ho a provar.\n");
					System.out.print("Nom: ");
					nom = sc.nextLine();
					System.out.print("Cognom: ");
					cognom = sc.nextLine();
				}

				treballadorTrobat = treballadors.get(posicioLlistatTreballadors(nom,cognom));   
				if(teUniformitat(treballadorTrobat,"guants")) {
					System.out.println("El treballador " + nom + " " + cognom + " ja disposa de guants.");
				}else {
					llistaUniformitats[posicioLlistaUniformitats("guants")].removeUnitat();
					treballadorTrobat.addUniformitat("guants");
					System.out.println("El treballador " + nom + " " + cognom + " ha rebut uns guants.");
				}
				System.out.println("");
				break;
			}

		} while(opcio > 0);
	}

	public static void retornarSegonsTipus() {
		int opcio = -1;
		String uniformitatEscollida = "";
		String nom = "";
		String cognom = "";
		Treballador treballadorTrobat = null;
		ArrayList<Treballador> treballadorsAmbUniformitat = new ArrayList<Treballador>();


		System.out.println("1.Jaqueta\n"
				+ "2.Pantaló\n"
				+ "3.Sabates\n"
				+ "4.Mascareta\n"
				+ "5.Guants");

		opcio = sc.nextInt();

		while(opcio < 1 || opcio > 5) {
			System.out.print("El nombre introduït no és correcte.\nTorna-ho a intentar: ");
			opcio = sc.nextInt();
		}

		switch(opcio) {
		case 1:
			uniformitatEscollida = "jaqueta";
			break;
		case 2:
			uniformitatEscollida = "pantaló";
			break;
		case 3:
			uniformitatEscollida = "sabates";
			break;
		case 4:
			uniformitatEscollida = "mascareta";
			break;
		case 5:
			uniformitatEscollida = "guants";
			break;
		}

		for(Treballador treballador : treballadors) {
			if(teUniformitat(treballador,uniformitatEscollida)) {
				treballadorsAmbUniformitat.add(treballador);
			}
		}

		System.out.println("El llistat següent conté els treballadors que disposen de " + uniformitatEscollida + ":");

		for(Treballador treballador : treballadorsAmbUniformitat) {
			System.out.println("- " + treballador.getNom() + " " + treballador.getCognom());
		}

		System.out.print("Escriu el nom i cognom del treballador al que li vols retirar la uniformitat.\nNom:");
		sc.nextLine();
		nom = sc.nextLine();
		System.out.print("Cognom:");
		cognom = sc.nextLine();

		while(posicioLlistatTreballadors(nom,cognom) == -1) {
			System.out.print("No existeix cap treballador amb aquest nom i cognoms.\nTorna-ho a intentar.\n");
			System.out.print("Nom: ");
			nom = sc.nextLine();
			System.out.print("Cognom: ");
			cognom = sc.nextLine();
		}

		treballadorTrobat = treballadors.get(posicioLlistatTreballadors(nom,cognom));
		treballadorTrobat.removeUniformitat(uniformitatEscollida);
		llistaUniformitats[posicioLlistaUniformitats(uniformitatEscollida)].addUnitat();	
		
		System.out.println("Al treballador " + nom + " " + cognom + " se li ha retirat la unitat d'uniforme " + uniformitatEscollida + " correctament.");
	}

	public static void retornarSegonsTreballador() {
		String nom = "";
		String cognom = "";
		String uniformitatProposada = "";
		Treballador treballadorTrobat = null;

		System.out.println("El següent llistat inclou tots els treballadors de la empresa:");

		llistaTreballadors();

		System.out.print("Escriu el nom i cognom del treballador al que li vols retirar la uniformitat.\nNom:");
		sc.nextLine();
		nom = sc.nextLine();
		System.out.print("Cognom:");
		cognom = sc.nextLine();

		while(posicioLlistatTreballadors(nom,cognom) == -1) {
			System.out.print("No existeix cap treballador amb aquest nom i cognoms.\nTorna-ho a intentar.\n");
			System.out.print("Nom: ");
			nom = sc.nextLine();
			System.out.print("Cognom: ");
			cognom = sc.nextLine();
		}

		treballadorTrobat = treballadors.get(posicioLlistatTreballadors(nom,cognom));

		System.out.println("Aquestes son les uniformitats del treballador " + nom + " " + cognom + ":");

		for(String uniformitat : treballadorTrobat.getUniforme()) {
			System.out.println("- " + uniformitat);
		}
		
		System.out.print("");
		System.out.print("Escriu el nom de la peça d'uniformitat que vols retirar: ");
		uniformitatProposada = sc.nextLine();

		while(!teUniformitat(treballadorTrobat,uniformitatProposada)) {
			System.out.print("El treballador " + nom + " " + cognom + " no te tal uniformitat.\nTorna-ho a provar: ");
			uniformitatProposada = sc.nextLine();
		}

		treballadorTrobat.removeUniformitat(uniformitatProposada);
		llistaUniformitats[posicioLlistaUniformitats(uniformitatProposada)].addUnitat();
		
		System.out.println("Al treballador " + nom + " " + cognom + " se li ha enretirat correctament la uniformitat " + uniformitatProposada + ".");
	}

	public static void llistaTreballadors() {

		for(Treballador treballador : treballadors) {
			System.out.println("- " + treballador.getNom() + " " + treballador.getCognom());
		}
	}
}

