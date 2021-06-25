package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import project.model.Book;
import project.model.User;
import project.service.BookService;
import project.service.HostHolder;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private HostHolder hostHolder;


    @RequestMapping(path = {"/index"},method = {RequestMethod.GET})
    public String bookList(Model model)throws Exception
    {
        User host  = hostHolder.getUser();
        if(host != null)
        {
            model.addAttribute("host",host);
        }
        loadAllBooksView(model);
        return "book/books";
    }

    @RequestMapping(path = {"/books/add"},method = {RequestMethod.GET})
    public String addBook()
    {
        return "book/addbook";
    }

    @RequestMapping(path = {"/books/add/do"},method = {RequestMethod.POST})
    public String doAddBook(
            @RequestParam("name") String name,
            @RequestParam("author") String author,
            @RequestParam("price") String price)throws Exception
    {
        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setPrice(price);
        bookService.addBook(book);
        return "redirect:/index";
    }

    @RequestMapping(path = {"/books/{bookId:[0-9]+}/delete"},method = {RequestMethod.GET})
    public String deleteBooks(@PathVariable("bookId") int bookId)throws Exception
    {
        bookService.deleteBooks(bookId);
        return  "redirect:/index";
    }

    @RequestMapping(path = {"/books/{bookId:[0-9]+]}/recover"},method = {RequestMethod.GET})
    public String recoverBook(@PathVariable("bookId") int bookId)throws Exception
    {
        bookService.recoverBooks(bookId);
        return "redirect:index";
    }
    /*
    为model加载所有的book
     */
    private void loadAllBooksView(Model model)throws Exception
    {
        model.addAttribute("books",bookService.getAllBooks());
    }
}
