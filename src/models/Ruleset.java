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
@Table(name="CAMPAIGNQA.QA_RULESET")
public class Ruleset extends Model{
	@Id
	@Column(name = "IRULESETID")
	public Long id;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "IDATASETID", referencedColumnName = "IDATASETID")
	public Dataset dataset;

	@Column(name = "SRULESETNAME")
	public String rulesetName;

	@OneToMany(mappedBy="ruleset", cascade=CascadeType.PERSIST)
	public List<ProcessingTable> processingTables;

	@OneToMany(mappedBy="ruleset", cascade=CascadeType.PERSIST)
	public List<Rule> rules;

	@SuppressWarnings("deprecation")
	public static final Finder<Long,Ruleset> find = new Finder<>(Long.class, Ruleset.class);
}
