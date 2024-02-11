package com.MrFix30.Model;

public class EmailDetails {
	 @Override
		public String toString() {
			return "EmailDetails [recipient=" + recipient + ", msgBody=" + msgBody + ", subject=" + subject
					+ ", attachment=" + attachment + ", url=" + url + "]";
		}
		private String recipient;
	    private String msgBody;
	    private String subject;
	    private String attachment;
	    private String url="http://localhost:8080";
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getRecipient() {
			return recipient;
		}
		public void setRecipient(String recipient) {
			this.recipient = recipient;
		}
		public String getMsgBody() {
			return msgBody;
		}
		public void setMsgBody(String msgBody) {
			this.msgBody = msgBody;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		public String getAttachment() {
			return attachment;
		}
		public void setAttachment(String attachment) {
			this.attachment = attachment;
		}
}
