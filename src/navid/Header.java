package navid;

import java.util.ArrayList;
import java.util.HashSet;

import com.avaje.ebeaninternal.server.lib.util.Str;

public class Header {
	ArrayList<Column> columns;
	HashSet<String> hashSet ;
	public Header(){
		columns = new ArrayList<Column>();
		hashSet = new HashSet<String>();
	}
	
	public boolean contains(String string) {
		
		return hashSet.contains(string);
	}
}
