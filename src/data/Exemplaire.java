package data;

import javax.swing.JTable;

import dbase.Tools;

public class Exemplaire implements Main_data {
	String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOuvreid() {
		return ouvreid;
	}
	public void setOuvreid(String ouvreid) {
		this.ouvreid = ouvreid;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	String ouvreid;
	String etat;
	public Exemplaire(String id, String ouvreid, String etat) {
		super();
		this.id = id;
		this.ouvreid = ouvreid;
		this.etat = etat;
	}
	public Exemplaire(String id, String etat) {
		super();
		this.id = id;
		this.etat = etat;
	}
	public Exemplaire(){}
	@Override
	public void add() {
		String add="insert into exemplaire(id,id_oeuvre,etat) values("
				+"'"+id+"',"
				+"'"+ouvreid+"',"
				+"'"+etat+"')";
		boolean isadd=dbase.Go.runNonQuery(add);
		if(isadd)
		{
			new Tools().msgbox("Exemplaire Ajouté");
		}else
		{
			new Tools().alertmsgbox("Erreur");
		}
		
	}
	@Override
	public void update() {
		String update="update exemplaire set "
				+"etat='"+etat+"'";
		boolean isupdate=dbase.Go.runNonQuery(update);
		if(isupdate)
		{
			new Tools().msgbox("Exemplaire Modifié");
		}else
		{
			new Tools().alertmsgbox("Erreur");
		}
		
	}
	@Override
	public void delete() {
		String delete="delete from exemplaire where id='"+id+"'";
		boolean isdelete=dbase.Go.runNonQuery(delete);
		if(isdelete)
		{
			new Tools().msgbox("Exemplaire Supprimé");
		}
		else
		{
			new Tools().alertmsgbox("Cette exemplaire est empyunté \nVous ne pouvez pas le supprimmer!!");
			
		}
	}
	@Override
	public String getAutonumber() {
		
		return dbase.Go.GetAutoNumber("exemplaire", "id");
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
