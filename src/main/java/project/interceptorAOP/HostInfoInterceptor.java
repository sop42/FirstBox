package project.interceptorAOP;

import project.model.Ticket;
import project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import project.service.TicketService;
import project.service.UserService;
import project.utils.ConcurrentUtils;
import project.utils.CookieUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class HostInfoInterceptor implements HandlerInterceptor {
    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    /**
     * 为注入host信息
     *
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception
    {
        String t = CookieUtils.getCookie("t", request);
        if (!StringUtils.isEmpty(t))
        {
            Ticket ticket = ticketService.getTicket(t);
            if (ticket != null && ticket.getExpiredAt().after(new Date()))
            {
                User host = userService.getUser(ticket.getUserId());
                ConcurrentUtils.setHost(host);
            }
        }
        return true;
    }
}
