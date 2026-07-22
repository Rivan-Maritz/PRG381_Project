package com.mycompany.prg381_project.BusinessLayer;

import com.mycompany.prg381_project.BusinessLayer.exceptions.BusinessException;
import com.mycompany.prg381_project.DAO.usersDAO;
import com.mycompany.prg381_project.model.usersModel;

/**
 * Business rules for authentication and registration. The UI only ever
 * calls login()/register() and either proceeds or catches a
 * BusinessException and shows its message - it never talks to usersDAO
 * or usersModel directly.
 */
public class UsersService {

    private final usersDAO dao = new usersDAO();

    public boolean login(String username, String password) throws BusinessException {
        if (isBlank(username) || isBlank(password)) {
            throw new BusinessException("Please enter both a username and a password.");
        }
        return dao.LoginUser(username.trim(), password);
    }

    public int getUserId(String username) {
        return dao.getUserIdByUsername(username);
    }

    /**
     * Validates and creates a new account. Throws BusinessException with a
     * user-facing message on any validation failure; does nothing further
     * if it throws (no partial writes).
     */
    public void register(String username, String password, String confirmPassword,
                          String email, String role) throws BusinessException {
        String cleanUsername = username == null ? "" : username.trim();
        String cleanEmail = email == null ? "" : email.trim();

        if (isBlank(cleanUsername) || isBlank(password) || isBlank(cleanEmail)) {
            throw new BusinessException("Username, password, and email are all required.");
        }
        if (password.length() < 6) {
            throw new BusinessException("Password must be at least 6 characters long.");
        }
        if (!password.equals(confirmPassword)) {
            throw new BusinessException("Passwords do not match.");
        }
        if (!cleanEmail.matches("^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)*\\.[a-zA-Z]{2,}$")) {
            throw new BusinessException("Please enter a valid email address.");
        }
        if (dao.usernameExists(cleanUsername)) {
            throw new BusinessException("That username is already taken.");
        }
        if (dao.emailExists(cleanEmail)) {
            throw new BusinessException("That email is already registered.");
        }

        usersModel user = new usersModel();
        user.setUsername(cleanUsername);
        user.setPassword(password);
        user.setEmail(cleanEmail);
        user.setRole(isBlank(role) ? "Storekeeper" : role);

        if (!dao.RegisterUser(user)) {
            throw new BusinessException("Registration failed. Please try again.");
        }
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}
