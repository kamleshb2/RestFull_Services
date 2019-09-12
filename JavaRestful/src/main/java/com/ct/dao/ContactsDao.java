package com.ct.dao;

import java.util.List;

import com.ct.entity.Contacts;

public interface ContactsDao {

	//CRUD Operations
	public Contacts addContact(Contacts contact) throws DaoException;
	public Contacts findById(Integer id) throws DaoException;
	public Contacts updateContact(Contacts contact) throws DaoException;
	public void deleteContact(Integer id) throws DaoException;
	
	//Queries
	
	public List<Contacts> findAll() throws DaoException;
	public List<Contacts> findByCity(String city) throws DaoException;
	public List<Contacts> findByCountry(String country) throws DaoException;
	
}
