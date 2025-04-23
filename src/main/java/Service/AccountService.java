package Service;

import Model.Account;
import DAO.AccountDAO;

public class AccountService implements AccountServiceInt {
    private AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    /**
     * This method creates a new account, given a valid username and password.
     * @param account - information for a to-be-created account
     * @return information of newly created account.
     */
    public Account createAccount(Account account) {
        return accountDAO.createAccount(account.getUsername(), account.getPassword());
    }

    /**
     * This method validates a login attempt.
     * @param account - information for an account to be validated
     * @return information of an already created account.
     */
    public Account loginAccount(Account account) {
        return accountDAO.loginAccount(account.getUsername(), account.getPassword());
    }
}
