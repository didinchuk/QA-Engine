package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.CascadeType;

import com.avaje.ebean.Model;

@Entity
@Table(name="CAMPAIGNQA.QA_DATASET")
public class Dataset extends Model{

	@Id
	@Column(name = "IDATASETID")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long id;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "ITEMPTABLEID", referencedColumnName = "ITEMPTABLEID")
	public TempTables tempTable;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "IDATASETTYPEID", referencedColumnName = "IDATASETTYPEID")
	public DatasetType datasetType;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "IDATASOURCEID", referencedColumnName = "IDATASOURCEID")
	public Datasource datasource;

	@Column(name = "BAUDITFLAG")
	public boolean isAudit;

	@Column(name = "BHASHEADER")
	public boolean hasHeader;

	@Column(name = "CDELIMITERTYPE")
	public char delimiterType;

	@Column(name = "IFIXEDWIDTH")
	public int fixedWidth;

	@Column(name = "IIGNORELINES")
	public int ignoreLines;

	@Column(name = "SDATASETNAME")
	public String datasetName;

	@Column(name = "SSOURCEFILEPATH")
	public String sourceFilePath;

	@Column(name = "SSOURCENAME")
	public String SourceName;

	@OneToMany(mappedBy="dataset", cascade=CascadeType.PERSIST)
	public List<ProcessingTable> processingTables;

	@OneToMany(mappedBy="dataset", cascade=CascadeType.PERSIST)
	public List<Ruleset> rulesets;

	@SuppressWarnings("deprecation")
	public static final Finder<Long,Dataset> find = new Finder<>(Long.class, Dataset.class);

}
