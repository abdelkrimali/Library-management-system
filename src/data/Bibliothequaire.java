package data;

import javax.swing.JTable;

import org.apache.commons.codec.digest.DigestUtils;

import dbase.Tools;
import dbase.Utility;

public class Bibliothequaire implements Main_data{
	
	
	String nom;
	String prenom;
	String nom_utilisateur;
	String pass;
	String email;
	String tel;
	String img;
	public Bibliothequaire(String nom, String prenom, String email, String tel) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
	}
	public Bibliothequaire() {
		
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) throws Utility {
		if(null != nom)
		{
			if(nom.length()<3)
			{
				throw new Utility("Le nom de l'utilisateur doit contenir au moins 3 caractères");
			}
			
		}else
		{
			throw new Utility("Merci de saisir le nom de l'utilisateur");
		}
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) throws Utility {
		if(null != prenom)
		{
			if(prenom.length()<3)
			{
				throw new Utility("Le prenom de l'utilisateur doit contenir au moins 3 caractères");
			}
			
		}else
		{
			throw new Utility("Merci de saisir le prenom de l'utilisateur");
		}
		this.prenom = prenom;
	}
	public String getNom_utilisateur() {
		
		return nom_utilisateur;
	}
	public void setNom_utilisateur(String nom_utilisateur) throws Utility {
		if(null != nom_utilisateur)
		{
			if(nom_utilisateur.length()==0)
			{
				throw new Utility("Le Nom Utilisateur  de doit pas etre vide!");
			}
			else if(nom_utilisateur.length()<4)
			{
				throw new Utility("Le Nom Utilisateur doit contenir au moins 4 caractères");
			}	
		}else
		{
			throw new Utility("Merci de saisir le Nom Utilisateur");
		}
		this.nom_utilisateur = nom_utilisateur;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) throws Utility {
		if(null != pass)
		{
			if(pass.length()<6)
			{
				throw new Utility("Le Mot de Passe doit contenir au moins 6 caractères");
			}
			
		}else
		{
			throw new Utility("Merci de saisir Le Mot de Passe");
		}
		//this.pass = DigestUtils.sha1Hex(pass);
		this.pass=pass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) throws Utility {
		if(null != email)
		{
			if(email.length()<3)
			{
				throw new Utility("L'email de l'emprunteur doit contenir au moins 10 caractères");
			}
			
		}else
		{
			throw new Utility("Merci de saisir le prenom de l'emprunteur");
		}
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) throws Utility {
		if (null != tel) {
            if (tel.length() < 10) {
                throw new Utility("Le numero de téléphone doit se composer \n"
                        + "au moins de 10 chiffres");
            }
        } else {
            throw new Utility("Merci de saisir un numéro de téléphone ");
        }
		this.tel = tel;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public void add() {
		
	
		String add="insert into bibliothequaire values( "
				+"'"+nom+"',"
				+"'"+prenom+"',"
				+"'"+nom_utilisateur+"',"
				+"'"+pass+"',"
				+"'"+email+"',"
				+"'"+tel+"',"
				+"'"+img+"')";
		boolean isadd=dbase.Go.runNonQuery(add);
		if(isadd)
		{
			new Tools().msgbox("Bibliothequaire enregistré");
		}else
		{
			new Tools().alertmsgbox("le nom_utilisateur existe dejà");
		}	
	}
	@Override
	public void update() {
		String update ="update Bibliothequaire set "
				+"mot_de_pass='"+pass+"',"
				+"email='"+email+"' "
				+"where nom_utilisateur ='"+nom_utilisateur+"'";
		boolean isupdate=dbase.Go.runNonQuery(update);
		if(isupdate)
		{
			new Tools().msgbox("Modification Enregistrées");
		}
		else
		{
			new Tools().alertmsgbox("failed");
		}
	}
	@Override
	public void delete() {
		
		
	}
	@Override
	public String getAutonumber() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void getallrow(JTable table) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void getonerow(JTable table) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void getcustomrows(String select, JTable table) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getnameByValue(String value) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getvalueByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
