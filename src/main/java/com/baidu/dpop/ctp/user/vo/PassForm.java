package com.baidu.dpop.ctp.user.vo;

import java.io.Serializable;

public class PassForm implements Serializable {

	private static final long serialVersionUID = -4726270442038030416L;
	private String oldPass;
	private String newPass;
	private String newAgainPass;

	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public String getNewAgainPass() {
		return newAgainPass;
	}

	public void setNewAgainPass(String newAgainPass) {
		this.newAgainPass = newAgainPass;
	}

}
