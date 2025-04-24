package Service;

import Model.Account;
import DAO.AccountDAO;

public class AccountService implements AccountServiceInt {
    private AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    /**
     * This method checks whether a given string is just whitespace.
     * @param str - a String object.
     * @return true if all characters are whitespace, false otherwise.
     */
    private boolean whiteSpaceChecker(String str) {
        for (char ch : str.toCharArray()) {
            if (!Character.isWhitespace(ch)) return false;
        }
        return true;
    }
    /**
     * This method creates a new account, given a valid username and password.
     * @param account - information for a to-be-created account
     * @return information of newly created account.
     */
    public Account createAccount(Account account) {
        // username and password must be valid, and username must be unique
        if ((account.getUsername().length() > 0) && (!whiteSpaceChecker(account.getUsername())) && (account.getPassword().length() >= 4) && (!whiteSpaceChecker(account.getPassword())) && (AccountDAO.findAccountByUsername(account.getUsername()) == null)) {
            return accountDAO.createAccount(account.getUsername(), account.getPassword());
        }
        return null;
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
