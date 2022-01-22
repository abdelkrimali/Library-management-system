package data;

import javax.swing.JTable;

import dbase.Tools;
import dbase.Utility;

public class Emprunteur implements Main_data{
	
	 private String nom;
	 private String prenom;
	 private String num_tel;
	 private String departement;
	 private String email;
	 private String image;
     private String num_inscr;
     String sanction;
     public String getSanction() {
		return sanction;
	}


	public void setSanction(String sanction) {
		this.sanction = sanction;
	}


	public Emprunteur()
     {
    	 
     }


	public Emprunteur(String num_inscr,String nom,String prenom,String departement,String num_tel,String email)
     {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.num_tel = num_tel;
		this.departement = departement;
		this.email = email;
		this.num_inscr = num_inscr;
     }
	
	
	public String getNum_inscr() {
		return num_inscr;
	}
	public void setNum_inscr(String num_inscr) throws Utility{
		
		if(null != num_inscr)
		{
			if(num_inscr.length()==0)
			{
				throw new Utility("numéro inscription de doit pas etre vide!");
			}
			else if(num_inscr.length()<4)
			{
				throw new Utility("Le numéro d'inscription doit contenir au moins 4 caractères");
			}
			
				
			
		}else
		{
			throw new Utility("Merci de saisir le numéro  de l'emprunteur");
		}
		this.num_inscr = num_inscr;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) throws Utility {
		
		if(null != nom)
		{
			if(nom.length()<3)
			{
				throw new Utility("Le nom de l'emprunteur doit contenir au moins 3 caractères");
			}
			
		}else
		{
			throw new Utility("Merci de saisir le nom de l'emprunteur");
		}
		this.nom = nom;
	}
	public String getPrenom()  {
		return prenom;
	}
	public void setPrenom(String prenom) throws Utility {
		if(null != prenom)
		{
			if(prenom.length()<3)
			{
				throw new Utility("Le prenom de l'emprunteur doit contenir au moins 3 caractères");
			}
			
		}else
		{
			throw new Utility("Merci de saisir le prenom de l'emprunteur");
		}
		this.prenom = prenom;
	}
	public String getNum_tel() {
		return num_tel;
	}
	public void setNum_tel(String num_tel) throws Utility {
		if (null != num_tel) {
            if (num_tel.length() < 10) {
                throw new Utility("Le numero de téléphone doit se composer \n"
                        + "au moins de 10 chiffres");
            }
        } else {
            throw new Utility("Merci de saisir un numéro de téléphone ");
        }
		this.num_tel = num_tel;
	}
	public String getDepartement() {
		return departement;
	}
	public void setDepartement(String departement) {
		this.departement = departement;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) throws Utility{
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public void add()  {
		
		String add="insert into emprunteur(num_inscr,nom_emprunt,prenom_emprunt,departement,num_tel,image,email) values("  
				 +"'"+num_inscr+"',"
				 +"'"+nom+"',"
				 +"'"+prenom+"',"
				 +"'"+departement+"',"
				 +"'"+num_tel+"',"
				 +"'"+image+"',"
				 +"'"+email+"' )";
		boolean isadd=dbase.Go.runNonQuery(add);
		if(isadd)
		{
			Tools t=new Tools();
			t.msgbox("Emprunteur ajouté avec success");
		}
		else
		{
			Tools t=new Tools();
			t.msgbox("Erreurs lors de l'ajout d'un usager\n Verifier le numéro inscription et reessayer");
		}
		
	}
	@Override
	public void update() {
		String update="update emprunteur set "
				 +"departement='"+departement+"',"
				 +"email='"+email+"',"
				 +"num_tel='"+num_tel+"',"
				 +"image='"+image+"' "
				 + "where num_inscr='"+num_inscr+"'";
		boolean isupdate=dbase.Go.runNonQuery(update);
		if(isupdate)
		{
			new Tools().msgbox("Modification enregistré");
		}
		else
		{
			new Tools().msgbox("Une erreur est produit lors de modifications");
		}
	}
	@Override
	public void delete() {
		String delete="delete from emprunteur where num_inscr='"+num_inscr+"'";
		boolean isdelete=dbase.Go.runNonQuery(delete);
		if(isdelete)
		{
			new Tools().msgbox("Emprunteur supprimé");
		}
		else
		{
			new Tools().alertmsgbox("Erreur lors de la suppression");
		}
		
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
