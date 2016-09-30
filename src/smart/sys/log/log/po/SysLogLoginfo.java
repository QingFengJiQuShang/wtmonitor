package smart.sys.log.log.po;

import java.util.Date;

/**
 * SysLogLoginfo entity. @author MyEclipse Persistence Tools
 */

public class SysLogLoginfo implements java.io.Serializable {

	// Fields

	private String autoId;
	private Integer type;
	private String username;
	private String systemname;
	private Date operateTime;
	private String operateDetail;
	private String ip;
	private Integer archiveFlag;
	private String archiveId;
	private String descrip;

	// Constructors

	/** default constructor */
	public SysLogLoginfo() {
	}

	/** minimal constructor */
	public SysLogLoginfo(String autoId, Integer type, String username,
			String systemname, Date operateTime, String ip,
			Integer archiveFlag) {
		this.autoId = autoId;
		this.type = type;
		this.username = username;
		this.systemname = systemname;
		this.operateTime = operateTime;
		this.ip = ip;
		this.archiveFlag = archiveFlag;
	}

	/** full constructor */
	public SysLogLoginfo(String autoId, Integer type, String username,
			String systemname, Date operateTime, String operateDetail,
			String ip, Integer archiveFlag, String archiveId, String descrip) {
		this.autoId = autoId;
		this.type = type;
		this.username = username;
		this.systemname = systemname;
		this.operateTime = operateTime;
		this.operateDetail = operateDetail;
		this.ip = ip;
		this.archiveFlag = archiveFlag;
		this.archiveId = archiveId;
		this.descrip = descrip;
	}

	// Property accessors

	public String getAutoId() {
		return this.autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSystemname() {
		return this.systemname;
	}

	public void setSystemname(String systemname) {
		this.systemname = systemname;
	}

	public Date getOperateTime() {
		return this.operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getOperateDetail() {
		return this.operateDetail;
	}

	public void setOperateDetail(String operateDetail) {
		this.operateDetail = operateDetail;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getArchiveFlag() {
		return this.archiveFlag;
	}

	public void setArchiveFlag(Integer archiveFlag) {
		this.archiveFlag = archiveFlag;
	}

	public String getArchiveId() {
		return this.archiveId;
	}

	public void setArchiveId(String archiveId) {
		this.archiveId = archiveId;
	}

	public String getDescrip() {
		return this.descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

}