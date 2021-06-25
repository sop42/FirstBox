package project.service;

import project.dao.UserDAO;
import project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public void addUser(User user)throws Exception
    {
        try {
            userDAO.addUser(user);
            user.setId(userDAO.getID(user.getEmail()));
        } catch (Exception e) {
            throw new Exception("addUser出错",e);
        }
    }

    public User getUser(int uid)throws Exception
    {
        try {
            return userDAO.selectById(uid);
        } catch (Exception e) {
            throw new Exception("selectById出错",e);
        }
    }

    public User getUser(String email)throws Exception
    {
        try {
            return userDAO.selectByEmail(email);
        } catch (Exception e) {
            throw new Exception("selectByEmail出错",e);
        }
    }
    public void updatePassword(User user)throws Exception
    {
        try {
            userDAO.updatePassword(user);
        } catch (Exception e) {
            throw new Exception("updatePassword出错",e);
        }
    }
}
