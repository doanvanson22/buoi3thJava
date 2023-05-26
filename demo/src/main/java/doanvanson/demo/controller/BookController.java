package doanvanson.demo.controller;

import doanvanson.demo.entity.Book;
import doanvanson.demo.services.BookService;
import doanvanson.demo.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showAllBooks(Model model){
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "book/list";
    }

    @GetMapping("/add")
    public String addBookForm(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "book/add";
    }

    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult, Model model){
        // Truong hop co loi rang buoc thi tra lai view add
        if (bindingResult.hasErrors()){
            model.addAttribute("categories", categoryService.getAllCategories());
            return "book/add";
        }

        bookService.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String editBookFrom(@PathVariable("id") Long id, Model model){
        Book find = bookService.getBookById(id);
        if(find != null){
            model.addAttribute("book",find);
            model.addAttribute("categories", categoryService.getAllCategories());
            return "book/edit";
        }else{
            return "not-found";
        }
    }

    @PostMapping("/edit")
    public String editBook(@Valid Book updateBook, Errors errors, Model model){
        if (errors != null && errors.getErrorCount() > 0) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "book/edit";
        }
        else
            bookService.updateBook(updateBook);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBooks(@PathVariable("id") Long id){
        Book find = bookService.getBookById(id);
        if(find!=null){
            bookService.deleteBook(id);
            return "redirect:/books";
        }
        return "not-found";
    }
}
