package smart.sys.log.log.po;

import java.util.Date;

/**
 * SysLogArchive entity. @author MyEclipse Persistence Tools
 */

public class SysLogArchive implements java.io.Serializable {

	// Fields

	private String autoId;
	private Integer type;
	private Date archiveTime;
	private Date archiveStart;
	private Date archiveEnd;
	private String descrip;
	private Long dataCount;

	// Constructors

	/** default constructor */
	public SysLogArchive() {
	}

	/** minimal constructor */
	public SysLogArchive(String autoId, Integer type, Date archiveTime,
			Long dataCount) {
		this.autoId = autoId;
		this.type = type;
		this.archiveTime = archiveTime;
		this.dataCount = dataCount;
	}

	/** full constructor */
	public SysLogArchive(String autoId, Integer type, Date archiveTime,
			Date archiveStart, Date archiveEnd, String descrip,
			Long dataCount) {
		this.autoId = autoId;
		this.type = type;
		this.archiveTime = archiveTime;
		this.archiveStart = archiveStart;
		this.archiveEnd = archiveEnd;
		this.descrip = descrip;
		this.dataCount = dataCount;
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

	public Date getArchiveTime() {
		return this.archiveTime;
	}

	public void setArchiveTime(Date archiveTime) {
		this.archiveTime = archiveTime;
	}

	public Date getArchiveStart() {
		return this.archiveStart;
	}

	public void setArchiveStart(Date archiveStart) {
		this.archiveStart = archiveStart;
	}

	public Date getArchiveEnd() {
		return this.archiveEnd;
	}

	public void setArchiveEnd(Date archiveEnd) {
		this.archiveEnd = archiveEnd;
	}

	public String getDescrip() {
		return this.descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public Long getDataCount() {
		return this.dataCount;
	}

	public void setDataCount(Long dataCount) {
		this.dataCount = dataCount;
	}

}