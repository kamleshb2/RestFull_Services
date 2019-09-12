package com.ct.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ct.entity.Contacts;
import com.ct.util.DbUtil;

public class JdbcContactsDao implements ContactsDao {

	@Override
	public Contacts addContact(Contacts contact) throws DaoException {
		String sql = "insert into contacts(name, gender,email,phone,city,country) values (?,?,?,?,?,?)";
		try(
				Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				
				){
			
			stmt.setString(1, contact.getName());
			stmt.setString(2, contact.getGender());
			stmt.setString(3, contact.getEmail());
			stmt.setString(4, contact.getPhone());
			stmt.setString(5, contact.getCity());
			stmt.setString(6, contact.getCountry());
			
			stmt.executeUpdate();
			ResultSet keys = stmt.getGeneratedKeys();
			keys.next();
			contact.setId(keys.getInt(1));
			return contact;
			
		}catch(Exception ex) {
			throw new DaoException(ex);
		}
	}

	@Override
	public Contacts findById(Integer id) throws DaoException {
		
		String sql = "select id, name, gender, email, phone, city, country from contacts where id='" + id + "'";
		try(
				Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql); 
				
				){
			//stmt.setInt(1,  id);
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				Contacts c = toContact(rs);
				rs.close();
				return c;
			}
			
			rs.close();
		}
		catch(Exception ex) {
			throw new DaoException(ex);
		}
		
		return null;
		
	}

	private Contacts toContact(ResultSet rs) throws SQLException {
		Contacts c = new Contacts();
		c.setId(rs.getInt("id"));
		c.setName(rs.getString("name"));
		c.setGender(rs.getString("gender"));
		c.setEmail(rs.getString("email"));
		c.setPhone(rs.getString("phone"));
		c.setCity(rs.getString("city"));
		c.setCountry(rs.getString("country"));
		return c;
	}

	@Override
	public Contacts updateContact(Contacts contact) throws DaoException{
		String sql = "update contacts set name=?, gender=?, email=?,phone=?,city=?,country=? where id=?";
		try(
				Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql); 
				
				){
			
			stmt.setString(1, contact.getName());
			stmt.setString(2, contact.getGender());
			stmt.setString(3, contact.getEmail());
			stmt.setString(4, contact.getPhone());
			stmt.setString(5, contact.getCity());
			stmt.setString(6, contact.getCountry());
			stmt.setInt(7,  contact.getId());
			
			int count = stmt.executeUpdate();
			if(count == 0) {
				throw new DaoException("no record found, invalid id supplied: " + contact.getId());
			}
			
		} catch(Exception ex) {
			throw new DaoException(ex);
		}
		return contact;
	}

	@Override
	public void deleteContact(Integer id) throws DaoException {
		String sql = "delete from contacts where id=?";
		try(
				Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				
				){
			stmt.setInt(1,  id);
			int count = stmt.executeUpdate();
			if(count == 0) {
				throw new DaoException("no record found, invalid id supplied: " + id);
			}
		}
		catch(Exception ex) {
			throw new DaoException(ex);
		}
		
	}

	@Override
	public List<Contacts> findAll() throws DaoException {

		String sql = "select * from contacts";
		List<Contacts> list = new ArrayList();
		try(
				Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				
				)
		{
			while(rs.next()) {
				Contacts c = toContact(rs);
				list.add(c);
			}
		}
		
		catch(Exception ex) {
					throw new DaoException(ex);
				}
		return list;
	}

	@Override
	public List<Contacts> findByCity(String city) throws DaoException {
		String sql = "select * from contacts where city = ? ";
		List<Contacts> list = new ArrayList();
		try(
				Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				
				
				)
		{
			stmt.setString(1, city);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Contacts c = toContact(rs);
				list.add(c);
			}
			
			rs.close();
		}
		
		catch(Exception ex) {
					throw new DaoException(ex);
				}
		return list;
	}

	@Override
	public List<Contacts> findByCountry(String country) throws DaoException {
		String sql = "select * from contacts where country = ? ";
		List<Contacts> list = new ArrayList();
		try(
				Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				
				
				)
		{
			stmt.setString(1, country);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Contacts c = toContact(rs);
				list.add(c);
			}
			
			rs.close();
		}
		
		catch(Exception ex) {
					throw new DaoException(ex);
				}
		return list;
	}

}
