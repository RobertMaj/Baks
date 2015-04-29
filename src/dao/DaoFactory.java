/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Robert M
 */
public interface DaoFactory {

    public DaoUser getDaoUser();

    public DaoDefect getDaoDefect();
    
    public DaoInvoice getDaoInvoice();
}
