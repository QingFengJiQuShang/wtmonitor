package com.jrsoft.fri.dagl.entity;


/**
 * 电梯网关表
 */

public class DaxxGateway implements java.io.Serializable {

	// Fields

	private Long id;				//
	private String type;				//电梯网关类型
	private String flow;				//取流方式
	private String net;				//入网方式
	private String communication;				//通信线路
	private String terminal;				//终端机型
	private String hardware;				//硬件版本
	private String software;				//软件版本
	private String sim;				//SIM卡号
	private String report;				//上报周期
	private String serialNumber;				//设备序列号
	private String ip;				//IP地址
	private String port;				//port

	// Constructors

	/** default constructor */
	public DaxxGateway() {
	}

	/** full constructor */
	public DaxxGateway(String type, String flow, String net,
			String communication, String terminal, String hardware,
			String software, String sim, String report, String serialNumber,
			String ip, String port) {
		this.type = type;
		this.flow = flow;
		this.net = net;
		this.communication = communication;
		this.terminal = terminal;
		this.hardware = hardware;
		this.software = software;
		this.sim = sim;
		this.report = report;
		this.serialNumber = serialNumber;
		this.ip = ip;
		this.port = port;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFlow() {
		return this.flow;
	}

	public void setFlow(String flow) {
		this.flow = flow;
	}

	public String getNet() {
		return this.net;
	}

	public void setNet(String net) {
		this.net = net;
	}

	public String getCommunication() {
		return this.communication;
	}

	public void setCommunication(String communication) {
		this.communication = communication;
	}

	public String getTerminal() {
		return this.terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getHardware() {
		return this.hardware;
	}

	public void setHardware(String hardware) {
		this.hardware = hardware;
	}

	public String getSoftware() {
		return this.software;
	}

	public void setSoftware(String software) {
		this.software = software;
	}

	public String getSim() {
		return this.sim;
	}

	public void setSim(String sim) {
		this.sim = sim;
	}

	public String getReport() {
		return this.report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public String getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return this.port;
	}

	public void setPort(String port) {
		this.port = port;
	}

}