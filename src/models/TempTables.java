package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.avaje.ebean.Model;
import com.avaje.ebean.Model.Finder;

@Entity
@Table(name="CAMPAIGNQA.QA_TEMPTABLES")
public class TempTables extends Model {

	@Id
	@Column(name = "ITEMPTABLEID")
	public Long id;

	@OneToMany(mappedBy="tempTable", cascade=CascadeType.PERSIST)
	public List<Dataset> datasets;

	@Column (name = "SCREATESTATEMENT")
	public String createStatement;
	
	@Column(name = "IROWNUM")
	public int rowNumber;

	@Column(name = "STEMPTABLENAME")
	public String tempTableName;

	@SuppressWarnings("deprecation")
	public static final Finder<Long,TempTables> find = new Finder<>(Long.class, TempTables.class);

}
