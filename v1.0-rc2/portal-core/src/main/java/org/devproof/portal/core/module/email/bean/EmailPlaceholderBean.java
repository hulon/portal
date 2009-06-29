/*
 * Copyright 2009 Carsten Hufe devproof.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 *   
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.devproof.portal.core.module.email.bean;

import java.util.Date;

/**
 * The bean holds the values which are replaced in the emails
 * 
 * @author Carsten Hufe
 */
public class EmailPlaceholderBean {
	private String toUsername;
	private String toFirstname;
	private String toLastname;
	private String toEmail;
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private Date birthday;
	private String confirmationLink;
	private String resetPasswordLink;
	private String content;
	private String contactFullname;
	private String contactEmail;
	private String contactIp;

	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(final String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(final String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(final Date birthday) {
		this.birthday = birthday;
	}

	public String getConfirmationLink() {
		return this.confirmationLink;
	}

	public void setConfirmationLink(final String confirmationLink) {
		this.confirmationLink = confirmationLink;
	}

	public String getResetPasswordLink() {
		return this.resetPasswordLink;
	}

	public void setResetPasswordLink(final String resetPasswordLink) {
		this.resetPasswordLink = resetPasswordLink;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	public String getToUsername() {
		return this.toUsername;
	}

	public void setToUsername(final String toUsername) {
		this.toUsername = toUsername;
	}

	public String getToFirstname() {
		return this.toFirstname;
	}

	public void setToFirstname(final String toFirstname) {
		this.toFirstname = toFirstname;
	}

	public String getToLastname() {
		return this.toLastname;
	}

	public void setToLastname(final String toLastname) {
		this.toLastname = toLastname;
	}

	public String getToEmail() {
		return this.toEmail;
	}

	public void setToEmail(final String toEmail) {
		this.toEmail = toEmail;
	}

	public String getContactFullname() {
		return this.contactFullname;
	}

	public void setContactFullname(final String contactFullname) {
		this.contactFullname = contactFullname;
	}

	public String getContactEmail() {
		return this.contactEmail;
	}

	public void setContactEmail(final String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactIp() {
		return this.contactIp;
	}

	public void setContactIp(final String contactIp) {
		this.contactIp = contactIp;
	}
}