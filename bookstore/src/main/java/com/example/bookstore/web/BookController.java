package com.example.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository repository;
	
	@RequestMapping("/index")
	public String sayWelcome() {
		return "index"; //index.html
	}
	@RequestMapping(value = "/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist"; // return booklist.html
	}
	// Palauttaa selaimeen tyhjän kirjalomakkeen
	@RequestMapping(value = "/addbook", method = RequestMethod.GET)
	public String addbook(Model model) {
		model.addAttribute("book", new Book());
		return "addbook";
	}
	// Tallentaa lomakkeelta lähetetyt kirjatiedot tietokantaan.
	@RequestMapping(value = "/savebook", method = RequestMethod.POST)
	public String savebook(Book book) {
		repository.save(book);
		return "redirect:/booklist";
	}
    // Delete student
	 @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    // todo: suojaa delete-toiminto metoditasolla @PreAuthorize-annotaatiolla vain ADMIN-roolin omaaville käyttäjille
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	repository.deleteById(bookId);
        return "redirect:../booklist";
    }     

}