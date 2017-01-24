package com.alcatelsbell.nms.security;

import java.util.List;

public class FlexOperatorUtil {
	public static List<Operator> setFlexOperator(List<com.alcatelsbell.nms.valueobject.domain.Operator> userlist,List<Operator> users,List l) throws Exception
	{
		for(int j=0;j<userlist.size();j++)
		{
			Operator o=new Operator();
			o.setId(userlist.get(j).getId());
			o.setLoginName(userlist.get(j).getLoginName());
			o.setPassWD(new String(BASE64.decryptBASE64(userlist.get(j).getPassWD())));
			o.setExpDate(userlist.get(j).getExpDate());
			o.setAccountStatus(userlist.get(j).getAccountStatus());
			o.setPwdExpdate(userlist.get(j).getPwdExpdate());
			o.setCreator(userlist.get(j).getCreator());
			o.setCreateDate(userlist.get(j).getCreateDate());
			o.setDepartment(userlist.get(j).getDepartment());
			o.setDepartmentdn(userlist.get(j).getDepartmentdn());

			o.setTelephone(userlist.get(j).getTelephone());
			o.setDn(userlist.get(j).getDn());
			o.setNo(userlist.get(j).getNo());
			o.setName(userlist.get(j).getName());
			o.setPredefined(userlist.get(j).getPredefined());
			o.setDescription(userlist.get(j).getDescription());
			o.setAppmodule(userlist.get(j).getAppmodule());
			o.setEmail(userlist.get(j).getEmail());
			o.setIsteamleader(userlist.get(j).getIsteamleader());
			o.setUsergroupId(userlist.get(j).getUsergroupId());
			o.setLogoutTime(userlist.get(j).getLogoutTime());
			o.setSynchroflowPWD(userlist.get(j).getSynchroflowPWD());
			o.setSex(userlist.get(j).getSex());
			o.setBirthday(userlist.get(j).getBirthday());
			o.setWorktype(userlist.get(j).getWorktype());
			o.setEntryDate(userlist.get(j).getEntryDate());
			o.setIDCard(userlist.get(j).getIDCard());
			o.setAllowip(userlist.get(j).getAllowip());
			o.setReJectip(userlist.get(j).getReJectip());	
			o.setParentoperatorid(userlist.get(j).getParentoperatorid());
			o.setMobilephone(userlist.get(j).getMobilephone());
			o.setIsweb(false);
            o.setFromWhere(userlist.get(j).getFromWhere());
			for(int k=0;k<l.size();k++)
			{
				if(String.valueOf(o.getId()).equals(l.get(k).toString()))
				{
					o.setIsweb(true);
				}
			}
			users.add(o);
		}
		return users;
	}
	public static void setOperator(Operator o,com.alcatelsbell.nms.valueobject.domain.Operator opt) throws Exception
	{
	        opt.setId(o.getId());
		opt.setLoginName(o.getLoginName());
		opt.setIsteamleader(o.getIsteamleader());
		opt.setAccountStatus(o.getAccountStatus());
		opt.setPassWD(BASE64.encryptBASE64(o.getPassWD().getBytes()));
		opt.setExpDate(o.getExpDate());
		opt.setPwdExpdate(o.getPwdExpdate());
		opt.setName(o.getName());
		opt.setNo(o.getNo());
		opt.setDepartment(o.getDepartment());
		opt.setDepartmentdn(o.getDepartmentdn());
		opt.setDn(o.getDn());
		opt.setTelephone(o.getTelephone());
		opt.setEmail(o.getEmail());
		opt.setMobilephone(o.getMobilephone());
		opt.setAllowip(o.getAllowip());
		opt.setReJectip(o.getReJectip());
		opt.setDescription(o.getDescription());
		opt.setCreateDate(o.getCreateDate());
		opt.setCreator(o.getCreator());
//		opt.setDn(o.getLoginName());
		opt.setParentoperatorid(o.getParentoperatorid());
                opt.setFromWhere(o.getFromWhere());
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(BASE64.encryptBASE64("abcd1234".getBytes()));
	}
}
