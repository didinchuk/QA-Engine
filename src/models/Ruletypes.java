package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.avaje.ebean.Model;

@Entity
@Table(name="CAMPAIGNQA.QA_RULETYPE")
public class Ruletypes extends Model{
	@Id
	@Column(name = "IRULETYPEID")
	public Long id;

	@Column(name = "STYPENAME")
	public String typeName;

	@OneToMany(mappedBy="ruleType", cascade=CascadeType.PERSIST)
	public List<Rule> rules;

	@SuppressWarnings("deprecation")
	public static final Finder<Long,Ruletypes> find = new Finder<>(Long.class, Ruletypes.class);
}
