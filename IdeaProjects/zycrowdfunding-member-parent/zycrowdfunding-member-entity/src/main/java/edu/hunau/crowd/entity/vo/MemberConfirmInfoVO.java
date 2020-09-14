package edu.hunau.crowd.entity.vo;

import java.io.Serializable;
public class MemberConfirmInfoVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// 易付宝账号
	private String payNum;

	// 法人身份证号
	private String cardNum;

	public MemberConfirmInfoVO() {
	}

	public MemberConfirmInfoVO(String payNum, String cardNum) {
		this.payNum = payNum;
		this.cardNum = cardNum;
	}

	public String getPayNum() {
		return payNum;
	}

	public void setPayNum(String payNum) {
		this.payNum = payNum;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
}