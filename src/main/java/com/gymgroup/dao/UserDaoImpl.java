/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gymgroup.dao;

import com.gymgroup.entities.Role;
import com.gymgroup.entities.User;

import javax.persistence.NoResultException;

import net.bytebuddy.asm.Advice;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alex
 */
@Transactional(rollbackFor = Exception.class)
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private RoleDao rdao;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public User findByUsername(String username) {
        Query q = getSession().createQuery("SELECT u from User u WHERE u.username = :name");
        q.setParameter("name", username);
        User user = null;
        try {
            user = (User) q.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("There is no result");
            user = null;
        }

        return user;
    }

    @Override
    public void save(User user) {
        Role role = rdao.findById(2);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
       getSession().save(user);
//         Query q = getSession().
//                 createNativeQuery("insert into user_role(uid,rid) values(:userid,2)");
//         q.setParameter("userid", user.getUserID());
    }

    @Override
    public void update(int id, User u) {
        User u2 = getSession().byId(User.class).load(id);
        
        System.out.println("USER U2 BEFORE CHANGES: " + u2);
        if(u.getCountry()!= null && u.getCountry().length()> 1){
        u2.setCountry(u.getCountry());}
        if(u.getEmail()!= null && u.getEmail().length()> 1){
        u2.setEmail(u.getEmail());}
        if(u.getFname() != null && u.getFname().length()> 1){
        u2.setFname(u.getFname());}
        if(u.getLname() != null && u.getLname().length()> 1){
        u2.setLname(u.getLname());}
        if(u.getPassword() != null && u.getPassword().length()> 1){
        u2.setPassword(u.getPassword());}
        if(u.getStreetNumber() != 0 && u.getStreetNumber() > 1){
        u2.setStreetNumber(u.getStreetNumber());}
        if(u.getStreet() != null && u.getStreet().length()> 1){
        u2.setStreet(u.getStreet());}
        if(u.getUsername() != null && u.getUsername().length()> 1){
        u2.setUsername(u.getUsername());}
        if(u.getZip() != 0 && u.getZip() > 1){
        u2.setZip(u.getZip());}
        System.out.println("USER U2 AFTER CHANGES : " + u2);
        getSession().update(u2);
    }

}
