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
@Table(name="CAMPAIGNQA.QA_DATASOURCETYPE")
public class DatasourceType extends Model{

	@Id
	@Column(name = "IDATASOURCETYPE")
	public Long id;

	@Column(name = "SDATASOURCETYPENAME")
	public String datasourceTypeName;

	@Column(name = "SGETTABLEDEFINITIONQUERY")
	public String getTableDefinitionQuery;

	@Column(name = "SGETTABLEQUERY")
	public String getTableQuery;

	@Column(name = "SJDBCJAR")
	public String jdbcJar;

	@OneToMany(mappedBy="datasourceType", cascade=CascadeType.PERSIST)
	public List<Datasource> Datasources;

	@SuppressWarnings("deprecation")
	public static final Finder<Long,DatasourceType> find = new Finder<>(Long.class, DatasourceType.class);

}
