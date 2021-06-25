package project.service;

import project.dao.BookDAO;
import project.model.Book;
import project.model.enums.BookStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookDAO bookDAO;

    public List<Book> getAllBooks() throws Exception
    {
        try {
            return  bookDAO.selectAll();
        }catch (Exception e){
            throw new Exception("SelectAll出错了",e);
        }
    }

    public int addBook(Book book) throws Exception
    {
        try {
            return  bookDAO.addBook(book);
        }catch (Exception e){
            throw new Exception("AddBook出错了",e);
        }
    }
    public void  deleteBooks(int id) throws Exception
    {
        try {
            bookDAO.updateBookStatus(id, BookStatusEnum.DELETE.getValue());
        }catch (Exception e){
            throw new Exception("updateBookStatus出错了",e);
        }
    }

    public void recoverBooks(int id) throws Exception
    {
        try {
            bookDAO.updateBookStatus(id, BookStatusEnum.NORMAL.getValue());
        } catch (Exception e) {
            throw new Exception("updateBookStatus出错了",e);
        }
    }

    public Book getBook(int id) throws Exception
    {
        try {
            return  bookDAO.selectBookById(id);
        }catch (Exception e){
            throw new Exception("selectBookById出错了",e);
        }
    }

    public Book getBook(String name) throws Exception
    {
        try {
            return  bookDAO.selectBookByName(name);
        }catch (Exception e){
            throw new Exception("selectBookByName出错了",e);
        }
    }
}
