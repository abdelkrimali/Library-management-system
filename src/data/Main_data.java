package data;

import javax.swing.JTable;

public interface Main_data {
	
		public void add();
	    public void update();
	    public void delete();
	    public String getAutonumber();
	    public void getallrow(JTable table);
	    public void getonerow(JTable table);
	    public void getcustomrows(String select,JTable table);
	    public String getnameByValue(String value);
	    public String getvalueByName(String name);

}
