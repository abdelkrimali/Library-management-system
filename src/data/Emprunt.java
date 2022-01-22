package data;

import java.util.Date;

import javax.swing.JTable;

import dbase.Tools;

public class Emprunt implements Main_data{
	
	String id_emprunt;
	String id_exemple;
	String id_emprunteur;
	String nom;
	String prenom;
	String reference;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	String titre;
	public Emprunt(String id_emprunteur, String nom, String prenom, String reference, String titre,
			String date_emprunt, String date_retourprevu) {
		super();
		this.id_emprunteur = id_emprunteur;
		this.nom = nom;
		this.prenom = prenom;
		this.reference = reference;
		this.titre = titre;
		this.date_emprunt = date_emprunt;
		this.date_retourprevu = date_retourprevu;
	}
	public Emprunt()
	{}
	String date_retourprevu;
	String date_retoureffective;
	String date_emprunt;
	public String getId_emprunt() {
		return id_emprunt;
	}
	public void setId_emprunt(String id_emprunt) {
		this.id_emprunt = id_emprunt;
	}
	public String getId_exemple() {
		return id_exemple;
	}
	public void setId_exemple(String id_exemple) {
		this.id_exemple = id_exemple;
	}
	public String getId_emprunteur() {
		return id_emprunteur;
	}
	public void setId_emprunteur(String id_emprunteur) {
		this.id_emprunteur = id_emprunteur;
	}
	public String getDate_retourprevu() {
		return date_retourprevu;
	}
	public void setDate_retourprevu(String date_retourprevu) {
		this.date_retourprevu = date_retourprevu;
	}
	public String getDate_retoureffective() {
		return date_retoureffective;
	}
	public void setDate_retoureffective(String date_retoureffective) {
		this.date_retoureffective = date_retoureffective;
	}
	public String getDate_emprunt() {
		return date_emprunt;
	}
	public void setDate_emprunt(String date_emprunt) {
		this.date_emprunt = date_emprunt;
	}
	
	
	@Override
	public void add() {
		String add="insert into emprunt values ("
				+"'"+id_emprunt+"',"
				+"'"+id_exemple+"',"
				+"'"+id_emprunteur+"',"
				+"'"+date_emprunt+"',"
				+"'"+date_retourprevu+"',"
				+"'"+date_retoureffective+"')";
		boolean isadd=dbase.Go.runNonQuery(add);
		if(isadd)
		{
			new Tools().msgbox("Emprunt Enregistré");
		}else
		{
			new Tools().alertmsgbox("Une erreur");
		}
		
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getAutonumber() {
		return dbase.Go.GetAutoNumber("emprunt", "id_emprunt");
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
