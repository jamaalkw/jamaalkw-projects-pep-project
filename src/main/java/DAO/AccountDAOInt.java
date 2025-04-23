package DAO;

import Model.Account;

/*
 * Interface for AccountDAO
 */
public interface AccountDAOInt {
    public Account createAccount(String username, String password);
    public Account loginAccount(String username, String password);
}
