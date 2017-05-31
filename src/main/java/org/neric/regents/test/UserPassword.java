package org.neric.regents.test;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserPassword
{
	private int userId;
	
	@Size(min=3, max=30)
	private String oldPassword;
	
	@Size(min=3, max=30)
	private String newPassword;
	
	@Size(min=3, max=30)
	private String newPasswordConfirm;

	
	public UserPassword(int userId)
	{
		super();
		this.userId = userId;
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public String getOldPassword()
	{
		return oldPassword;
	}
	public void setOldPassword(String oldPassword)
	{
		this.oldPassword = oldPassword;
	}
	public String getNewPassword()
	{
		return newPassword;
	}
	public void setNewPassword(String newPassword)
	{
		this.newPassword = newPassword;
	}
	public String getNewPasswordConfirm()
	{
		return newPasswordConfirm;
	}
	public void setNewPasswordConfirm(String newPasswordConfirm)
	{
		this.newPasswordConfirm = newPasswordConfirm;
	}
	
	
}
