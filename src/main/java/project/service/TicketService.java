package project.service;

import project.dao.TicketDAO;
import project.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    @Autowired
    private TicketDAO ticketDAO;

    public void  addTicket(Ticket t) throws  Exception
    {
        try{
            ticketDAO.addTicket(t);
        }catch (Exception e)
        {
            throw new Exception("addTicket出错了",e);
        }
    }

    public Ticket getTicket(int uid)throws Exception
    {
       try{
           return  ticketDAO.selectByUserId(uid);
       }catch (Exception e){
           throw new Exception("selectByUserId出错了",e);
       }
    }

    public Ticket getTicket(String t)throws Exception
    {
        try {
           return ticketDAO.selectByTicket(t);
        } catch (Exception e) {
            throw new Exception("selectByTicket出错了",e);
        }
    }

    public void deleteTicket(int tid)throws Exception
    {
        try {
            ticketDAO.deleteTicketById(tid);
        } catch (Exception e) {
            throw new Exception("deleteTicketById",e);
        }
    }

    public void deleteTicket(String t)throws Exception
    {
        try {
            ticketDAO.deleteTicket(t);
        } catch (Exception e) {
            throw new Exception("deleteTicket",e);
        }
    }
}
