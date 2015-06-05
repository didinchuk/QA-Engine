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
@Table(name="CAMPAIGNQA.QA_DATASETTYPE")
public class DatasetType extends Model {

	@Id
	@Column(name = "IDATASETTYPEID")
	public Long id;

	@Column(name = "SDATASETTYPENAME")
	public String datasetTypeName;

	@OneToMany(mappedBy="datasetType", cascade=CascadeType.PERSIST)
	public List<Dataset> dataset;

	@SuppressWarnings("deprecation")
	public static final Finder<Long,DatasetType> find = new Finder<>(Long.class, DatasetType.class);
}
