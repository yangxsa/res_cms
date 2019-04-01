package com.yaa.cms.model;

import java.io.Serializable;
import java.util.Date;


/**
 * Generator code
 */
public class Cert implements Serializable{

	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//车辆准运证号
	private String navicert;
	//
	private String approvalTime;
	//
	private String number;
	//
	private String owned;
	//
	private String projectName;
	//
	private String projectAddress;
	//
	private String accommodation;
	//
	private String accommodationAddress;
	//
	private String permit;


	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}


	public void setNavicert(String navicert) {
		this.navicert = navicert;
	}

	public String getNavicert() {
		return navicert;
	}


	public void setApprovalTime(String approvalTime) {
		this.approvalTime = approvalTime;
	}

	public String getApprovalTime() {
		return approvalTime;
	}


	public void setNumber(String number) {
		this.number = number;
	}

	public String getNumber() {
		return number;
	}


	public void setOwned(String owned) {
		this.owned = owned;
	}

	public String getOwned() {
		return owned;
	}


	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectName() {
		return projectName;
	}


	public void setProjectAddress(String projectAddress) {
		this.projectAddress = projectAddress;
	}

	public String getProjectAddress() {
		return projectAddress;
	}


	public void setAccommodation(String accommodation) {
		this.accommodation = accommodation;
	}

	public String getAccommodation() {
		return accommodation;
	}


	public void setAccommodationAddress(String accommodationAddress) {
		this.accommodationAddress = accommodationAddress;
	}

	public String getAccommodationAddress() {
		return accommodationAddress;
	}


	public void setPermit(String permit) {
		this.permit = permit;
	}

	public String getPermit() {
		return permit;
	}

}
