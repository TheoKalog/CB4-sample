package project2;

import java.util.Date;

public class Messages {
	private Date dateOfSubmission;
	private String sender;
	private String receiver;
	private String messageData;

	public Messages(Date dateOfSubmission, String sender, String receiver, String messageData) {
		super();
		this.dateOfSubmission = dateOfSubmission;
		this.sender = sender;
		this.receiver = receiver;
		this.messageData = messageData;
	}

	public Date getDateOfSubmission() {
		return dateOfSubmission;
	}

	public void setDateOfSubmission(Date dateOfSubmission) {
		this.dateOfSubmission = dateOfSubmission;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMessageData() {
		return messageData;
	}

	public void setMessageData(String messageData) {
		this.messageData = messageData;
	}

	@Override
	public String toString() {
		return "Messages [dateOfSubmission=" + dateOfSubmission + ", sender=" + sender + ", receiver=" + receiver
				+ ", messageData=" + messageData + "]";
	}

	// DateFormat dateFormatter = null;

}
