package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.avaje.ebean.Model;

@Entity
@Table(name="CAMPAIGNQA.QA_RESULTTABLE")
public class ResultTable extends Model{

	@Id
	@Column(name = "IRESULTSETTABLEID")
	public Long id;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "IRULEID", referencedColumnName = "IRULEID")
	public Rule rule;

	@Column(name = "IROWNUM")
	public int rowNumber;

	@Column(name = "SRESULTSETTABLENAME")
	public String resultsetTableName;

	@OneToMany(mappedBy="resultTable", cascade=CascadeType.PERSIST)
	public List<ProcessingTable> processingTables;

	@SuppressWarnings("deprecation")
	public static final Finder<Long,ResultTable> find = new Finder<>(Long.class, ResultTable.class);
}
