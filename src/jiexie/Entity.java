package jiexie;

public class Entity {
	private String header;			//包头
	private String length;			//长度
	private String clas;				//class
	private String elevatorId;	//电梯id
	private String gatewayId;     //网关终端 id
	private String command;     //command
	private String message;		//message
	
	
	
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getClas() {
		return clas;
	}
	public void setClas(String clas) {
		this.clas = clas;
	}
	public String getElevatorId() {
		return elevatorId;
	}
	public void setElevatorId(String elevatorId) {
		this.elevatorId = elevatorId;
	}
	public String getGatewayId() {
		return gatewayId;
	}
	public void setGatewayId(String gatewayId) {
		this.gatewayId = gatewayId;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	
	
	
}
