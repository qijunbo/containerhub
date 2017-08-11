package com.example.common;

public class RestRequest<R> {

	public static class Header {
		int transactionId;
		String user;
		String code;

		public int getTransactionId() {
			return transactionId;
		}

		public void setTransactionId(int transactionId) {
			this.transactionId = transactionId;
		}

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

	}

	private Header header;

	private R body;

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public R getBody() {
		return body;
	}

	public void setBody(R body) {
		this.body = body;
	}

}
