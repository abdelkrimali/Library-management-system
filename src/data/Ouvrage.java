package data;

import javax.swing.JTable;

import dbase.Tools;
import dbase.Utility;

public class Ouvrage implements Main_data {
	String reference;
	String titre;
	String auteur;
	String categ;
	String type;

	public Ouvrage(){} 
	
	public Ouvrage(String reference, String titre, String auteur, String categ, String type) {
		super();
		this.reference = reference;
		this.titre = titre;
		this.auteur = auteur;
		this.categ = categ;
		this.type = type;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) throws Utility{
		if(reference.length()<=0)
		{
			throw new Utility("Reference Oeuvre non valide !");
		}
		this.reference = reference;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) throws Utility{
	     if (null != titre) {
	            if (titre.length() < 3) {
	                throw new Utility("Le titre doit avoir "
	                        + "au moins 3 caractères");
	            }
	        } else {
	            throw new Utility("Merci de saisir le titre de l'oeuvre ");
	        }
		this.titre = titre;
	}
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) throws Utility{
		if (null != auteur) {
            if (auteur.length() < 3) {
                throw new Utility("Le nom de l'auteur doit avoir "
                        + "au moins 3 caractères");
            }
        } else {
            throw new Utility("Merci de saisir un nom ");
        }
		this.auteur = auteur;
	}
	public String getCateg() {
		
		return categ;
	}
	public void setCateg(String categ) throws Utility{
		/* if (null != categ) {
	            if (categ.length() < 3) {
	                throw new Utility("Le nom de la catégorie doit contenir "
	                        + "au moins 3 caractères");
	            }
	        } else {
	            throw new Utility("Merci de saisir une catégorie ");
	        }*/
		this.categ = categ;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) throws Utility{
		this.type = type;
	}

	@Override
	public void add() {
		String add="insert into ouvrage values ("
				+"'"+reference+"',"
				+"'"+titre+"',"
				+"'"+auteur+"',"
				+"'"+categ+"',"
				+"'"+type+"')";
		boolean isadd=dbase.Go.runNonQuery(add);
		if(isadd)
		{
			new Tools().msgbox("ouvrage ajouté");
		}
		else
		{
			new Tools().alertmsgbox("Ereeur !!");
		}
		
	}

	@Override
	public void update() {
		String update="update ouvrage set "
				+"titre='"+titre+"',"
				+"auteur='"+auteur+"',"
				+"categorie='"+categ+"',"
				+"typeoeuvre='"+type+"' where refe_ouvre='"+reference+"'";
		boolean isupdate=dbase.Go.runNonQuery(update);
		if(isupdate)
		{
			new Tools().msgbox("mise à jour avec success");
			
		}else
		{
			new Tools().alertmsgbox("Erreurs lors de la mise à jour de l'oeuvre");
		}
		
	}

	@Override
	public void delete() {
		String delete="delete from ouvrage where refe_ouvre='"+reference+"'";
		boolean isdelete=dbase.Go.runNonQuery(delete);
		if(isdelete)
		{
			new Tools().msgbox("Ouvrage Supprimé");
		}else
		{
			new Tools().alertmsgbox("Vous ne pouvez pas supprimmer cette oeuvre \n ella à des exemplaire!");
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
