package Common;

import java.io.Serializable;
import java.util.Scanner;

public class Message implements Serializable{

	private static final long serialVersionUID = 1L;
	private int num;//��Ϣ���
	private String message;//��Ϣ
	private int type;//��Ϣ����
	private Topic topic;//��Ϣ����

	//���캯��
	public Message() {}
	public Message(String s,Topic topic,int num) {
		// Ĭ����Ϣ����Ϊ1
		this.setType(MessageType.REPLY_EXPECTED);
		this.setNum(num);
		if(s.length()>9999) {

			this.message = s.substring(0, 9999);
		}
		else{
			this.message = s;

		}
		this.topic = topic;
	}
	//���캯��
	public Message(String s,int type,int num) {
		this.setType(type);
		this.setNum(num);
		if(s.length()>9999) {

			this.message = s.substring(0, 9999);
		}
		else{
			this.message = s;

		}
	}
	public Message(String s,int type,Topic topic,int num) {
		this.setType(type);
		this.topic = topic;
		this.setNum(num);
		if(s.length()>9999) {

			this.message = s.substring(0, 9999);
		}
		else{
			this.message = s;

		}
		
	}
	public String getMessage() {
		return message;
	}

	public int getType() {
		return type;
	}
	public void setType(int type) {
		if(MessageType.contains(type))
			this.type = type;
		else// Ĭ����Ϣ����Ϊ1
			this.setType(MessageType.REPLY_EXPECTED);
	}


	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}

