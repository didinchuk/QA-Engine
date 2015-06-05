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
@Table(name="CAMPAIGNQA.QA_RULE")
public class Rule extends Model{

	@Id
	@Column(name = "IRULEID")
	public Long id;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "IRULESETID", referencedColumnName = "IRULESETID")
	public Ruleset ruleset;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "IRULETYPEID", referencedColumnName = "IRULETYPEID")
	public Ruletypes ruleType;

	@Column(name = "SRULEXML")
	public String ruleXML;

	@Column(name = "SRULENAME")
	public String ruleName;

	@OneToMany(mappedBy="rule", cascade=CascadeType.PERSIST)
	public List<ResultTable> resultTables;

	@SuppressWarnings("deprecation")
	public static final Finder<Long,Rule> find = new Finder<>(Long.class, Rule.class);
}
