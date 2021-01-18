//package com.twofa.googleauth.sample.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class DAOService {
//
//	@Autowired
//	@Qualifier("jdbcTemplate")
//	private JdbcTemplate jdbcTemplate;
//	
//	public JdbcTemplate getJdbcTemplate() {
//		return jdbcTemplate;
//	}
//
//	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}
//	
//	public void update2FAProperties(String id, String twofacode) {
//		jdbcTemplate.update(" update useraccess set OTP_CODE=?, OTP_EXPIRE_TIME=? where id=? ", new Object[] 
//				{ twofacode, (System.currentTimeMillis()/1000) + 120, Integer.parseInt(id) });
//	}
//
//	public boolean checkCode(String userid, String code) {
//		return jdbcTemplate.queryForObject("select count(*) from useraccess where otp_code=? and id=?"
//				, new Object[] {code, Integer.parseInt(userid)}, Integer.class) >0; 
//	}
//	
//	/**
//	 * Method to get secret code from userMaster (generateSecret)
//	 * 
//	 * @param EmpId
//	 * @return
//	 */
//	public String getSecretCode(int EmpId) {
//		String sql = "select SECRET_CODE from userAccess where id = ?";
//		  return (String) jdbcTemplate.queryForObject(sql, new Object[] { EmpId }, String.class);
//	}
//
////	@SuppressWarnings("deprecation")
////	public boolean checkCode(String id, String code) {
////		return jdbcTemplate.queryForObject("select count(*) from useraccess where otp_code=? and id=?"
////				+ " and otp_expire_time >=?", new Object[] {code, id, 
////						System.currentTimeMillis()/1000}, Integer.class) >0; 
////	}
//	
//}