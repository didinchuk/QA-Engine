package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.CascadeType;

import com.avaje.ebean.Model;

@Entity
@Table(name="CAMPAIGNQA.QA_DATASOURCES")
public class Datasource extends Model {

	@Id
	@Column(name = "IDATASOURCEID")
	public Long id;

	@Column(name = "BISACTIVE")
	public boolean isActive;

	@Column(name = "ISERVERPORT")
	public int serverPort;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "IDATASOURCETYPE", referencedColumnName = "IDATASOURCETYPE")
	public DatasourceType datasourceType;

	@Column(name = "SCONNECTIONNAME")
	public String connectionName;

	@Column(name = "SDATABASENAME")
	public String databaseName;

	@Column(name = "SENCRYPTIONTYPE")
	public String encryptionType;

	@Column(name = "SJDBCSTRING")
	public String jdbcString;

	@Column(name = "SPASSWORD")
	public String password;

	@Column(name = "SSERVERNAME")
	public String serverName;

	@Column(name = "SUSERNAME")
	public String username;

	@OneToMany(mappedBy="datasource", cascade=CascadeType.PERSIST)
	public List<Dataset> datasets;

	@SuppressWarnings("deprecation")
	public static final Finder<Long,Datasource> find = new Finder<>(Long.class, Datasource.class);

}
