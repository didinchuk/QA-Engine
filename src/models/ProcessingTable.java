package models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.avaje.ebean.Model;

@Entity
@Table(name="CAMPAIGNQA.QA_PROCESSINGTABLE")
public class ProcessingTable extends Model {

	@Id
	@Column(name = "IJOBID")
	public Long jobId;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "IRESULTSETTABLEID", referencedColumnName = "IRESULTSETTABLEID")
	public ResultTable resultTable;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "IRULESETID", referencedColumnName = "IRULESETID")
	public Ruleset ruleset;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "IDATASETID", referencedColumnName = "IDATASETID")
	public Dataset dataset;

	@Column(name = "IJOBSTATUS")
	public int jobStatus;

	@Column(name = "TSCOMPLETIONDATE")
	public Date completionDate;

	@Column(name = "TSQUEUEDATA")
	public Date queuDate;

	@Column(name = "TSSTARTDATE")
	public Date startDate;

}
