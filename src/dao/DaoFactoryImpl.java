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
public class DaoFactoryImpl implements DaoFactory {

    @Override
    public DaoUser getDaoUser() {
        return new DaoUser();
    }

    public DaoFactoryImpl() {
    }

    @Override
    public DaoDefect getDaoDefect() {
        return new DaoDefect();
    }

    @Override
    public DaoInvoice getDaoInvoice() {
        return new DaoInvoice();
    }

}
