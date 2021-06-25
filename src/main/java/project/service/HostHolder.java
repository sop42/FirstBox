package project.service;

import org.springframework.stereotype.Service;
import project.model.User;
import project.utils.ConcurrentUtils;

@Service
public class HostHolder {

    public  User getUser()
    {
        return ConcurrentUtils.getHost();
    }
    public  void setUser(User user)
    {
         ConcurrentUtils.setHost(user);
    }
}
