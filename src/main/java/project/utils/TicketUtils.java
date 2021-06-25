package project.utils;

import project.model.Ticket;
import org.joda.time.DateTime;
public class TicketUtils {
    public static Ticket next(int uid)
    {
        Ticket ticket = new Ticket();
        ticket.setTicket(UuidUtils.next());
        ticket.setUserId(uid);
        //设置t票过期时间
        DateTime expireTime = new DateTime();
        expireTime.plusMonths(3);
        ticket.setExpiredAt(expireTime.toDate());
        return ticket;
    }
}
