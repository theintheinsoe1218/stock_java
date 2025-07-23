package com.tts.stock.dao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tts.stock.domain.UserAccount;
import com.tts.stock.dto.UserAccountDto;
import com.tts.stock.util.Cryption;


@Repository
public class UserAccountDaoImpl implements UserAccountDao{
	@Autowired
	SessionFactory sessionFactory;
	@Override
	public UserAccount getLoginAccount(String userName, String password) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String encryPassword = Cryption.encryption(password);
		List<UserAccount> list = session.createQuery("SELECT ac FROM UserAccount ac WHERE  ac.userName=:userName  AND ac.encryptPassword =:encryPassword AND ac.status = 1  ")
				.setParameter("userName", userName)
				.setParameter("encryPassword", encryPassword)
				.getResultList();
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	@Override
	public UserAccount getUserAccountsById(int userAccountId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.find(UserAccount.class, userAccountId);
	}
	@Override
	public void addUserAccounts(UserAccount ua) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(ua);
	}
	
	@Override
	public List<UserAccountDto> getUserAccounts(String userType) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List<UserAccount> list = session.createQuery("SELECT ac FROM UserAccount ac "
				//+ "WHERE  ac.userType=:userType "
				)
				//.setParameter("userType", userType)
				.getResultList();
		List<UserAccountDto> dtoList = new ArrayList<>();//new //;l
		for(UserAccount ua:list) {
			UserAccountDto dto = new UserAccountDto(ua);
			dtoList.add(dto);
		}
		return dtoList;
	}
	
	@Override
	public List<UserAccountDto> getUserList(String userType) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String sqlData="SELECT ua.userAccountId,ua.profileName,ua.userName,ua.phone,ua.nrcNo,ua.userType,ua.`status`,\r\n"
				+ "ua.address,ua.remark,b.branchId,b.branchName,ua.password,ua.encryptPassword,ua.date,ua.modifiedDate\r\n"
				+ "FROM useraccount ua \r\n"
				+ "LEFT JOIN branch b ON b.branchId = ua.branchId\r\n";
		String whereClause="WHERE ua.`status` = 1 ";
		String orderClause = " ORDER BY ua.profileName ASC";
		
		
		List<Object[]> objectList = new ArrayList<Object[]>();
		if("ALL".equals(userType)) {
			objectList=session.createNativeQuery(sqlData+whereClause+orderClause).getResultList();
		}else {
			objectList=session.createNativeQuery(sqlData+whereClause+ " AND ua.userType=:userType "+orderClause)
					  .setParameter("userType", userType).getResultList();
		}
		
		List<UserAccountDto> userDtoList=new ArrayList<UserAccountDto>();
		
		for(Object[] object:objectList) {
			int userAccountId=Integer.parseInt(object[0].toString()) ;
			String profileName=object[1].toString();
			String userName=object[2].toString();
			String phone="";
			if(object[3]!=null) {
				phone=object[3].toString();
			}
			String nrcNo="";
			if(object[4]!=null) {
				nrcNo=object[4].toString();
			}
			
			String usertype=object[5].toString();
			boolean status=(boolean) object[6];
			String address = (String)object[7];
			String remark = (String)object[8];
			int branchId = Integer.parseInt(object[9].toString());
			String branchName = (String)object[10];
			String password = (String)object[11];
			String epassword = (String)object[12];
			Date date = (Date)object[13];
			Date modifiedDate = (Date)object[14];
					
			UserAccountDto dto=new UserAccountDto(userAccountId,profileName,userName,phone,nrcNo,usertype,
					status,address,remark,password,epassword);

//			BranchDto branchDto = new BranchDto(branchId,branchName);
//			dto.setBranchDto(branchDto);
			dto.setDate(date);
			dto.setModifiedDate(modifiedDate);
			userDtoList.add(dto);
		}
		return userDtoList;
	}
	
	@Override
	public List<UserAccountDto> getUserListByName(String userName) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String sqlData="SELECT ua.userAccountId,ua.profileName,ua.userName,ua.phone,ua.nrcNo,ua.userType,\r\n"
				+ "ua.`status`,ua.address,ua.remark, ua.branchId, b.branchName,ua.date\r\n"
				+ "FROM useraccount ua\r\n"
				+ "LEFT JOIN branch b ON b.branchId = ua.branchId\r\n";
		String whereClause="WHERE ua.`status`= 1 ";
		String orderClause = " ORDER BY ua.profileName ASC ";
		List<Object[]> objectList = session.createNativeQuery(sqlData+whereClause+ " AND (ua.profileName LIKE :userName OR ua.userName LIKE :userName	) "+orderClause)
				.setParameter("userName","%"+userName.toString().trim()+"%").getResultList();
		List<UserAccountDto> userDtoList=new ArrayList<UserAccountDto>();
//		BranchDto braDto = new BranchDto();
		for(Object[] object:objectList) {
			int userAccountId=Integer.parseInt(object[0].toString()) ;
			String profileName=object[1].toString();
			String uusername=object[2].toString();
			String phone="";
			if(object[3]!=null) {
				phone=object[3].toString();
			}
			String nrcNo="";
			if(object[4]!=null) {
				nrcNo=object[4].toString();
			}
			String usertype=object[5].toString();
			boolean status=(boolean) object[6];
			String address = (String)object[7];
			
			String remark = (String)object[8];
			
			int branchId = Integer.parseInt(object[9].toString());
			String branchName = (String)object[10];
			
//			braDto = new BranchDto(branchId,branchName);
			Date date = (Date)object[11];
			UserAccountDto dto=new UserAccountDto(userAccountId,profileName,uusername,phone,nrcNo,usertype,
					status,address,remark,date);
//			braDto = new BranchDto(branchId,branchName);
//			dto.setBranchDto(braDto);
			
			userDtoList.add(dto);
		}
		return userDtoList;
	}
	
	@Override
	public List<UserAccountDto> getUserListById(int userId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String sqlData="SELECT ua.userAccountId,ua.profileName,ua.userName,ua.phone,ua.nrcNo,ua.userType,ua.`status`\r\n"
				+ ",ua.address,ua.remark,ua.branchId,b.branchName,ua.date \r\n"
				+ "FROM useraccount ua\r\n"
				+ "LEFT JOIN branch b ON b.branchId = ua.branchId\r\n";
		String whereClause="WHERE ua.`status`= 1 ";
		String orderClause = " ORDER BY ua.profileName ASC ";
//		BranchDto braDto = new BranchDto();
		List<Object[]> objectList = session.createNativeQuery(sqlData+whereClause+ " AND ua.userAccountId=:userAccountId "+orderClause)
				  .setParameter("userAccountId", userId).getResultList();
		List<UserAccountDto> userDtoList=new ArrayList<UserAccountDto>();
		for(Object[] object:objectList) {
			int userAccountId=Integer.parseInt(object[0].toString()) ;
			String profileName=object[1].toString();
			String userName=object[2].toString();
			String phone="";
			if(object[3]!=null) {
				phone=object[3].toString();
			}
			String nrcNo="";
			if(object[4]!=null) {
				nrcNo=object[4].toString();
			}
			String usertype=object[5].toString();
			boolean status=(boolean) object[6];
			String address = (String)object[7];


			String remark = (String)object[8];

			int branchId = Integer.parseInt(object[9].toString());
			String branchName = (String)object[10];
			Date date = (Date)object[11];
			UserAccountDto dto=new UserAccountDto(userAccountId,profileName,userName,phone,nrcNo,usertype,
					status,address,remark,date);
//			braDto = new BranchDto(branchId,branchName);
//			dto.setBranchDto(braDto);
			userDtoList.add(dto);
		}
		return userDtoList;
	}
	
	
	@Override
	public void deleteUserList(int userAccountId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.createNativeQuery(" UPDATE useraccount ua SET ua.status=0 WHERE ua.`status`=1 AND ua.useraccountId=:userAccountId ")
		       .setParameter("userAccountId", userAccountId).executeUpdate();
		
	}
	
	
	@Override
	public void updateUserList(UserAccount ua) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(ua);
	}
	

	
}
