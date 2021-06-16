package com.deng.booklist.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.deng.booklist.entity.Book;
import com.deng.booklist.service.BookService;

@Controller
@RequestMapping("/api")
public class BookController {
	@Autowired
	private BookService bookService;
	
	/**
	 * 獲取書單列表
	 * @param model
	 * @return
	 */
	@GetMapping("/books")
	public String list(@RequestParam(defaultValue = "0") int page, 
			@RequestParam(defaultValue = "5") int size,
			Model model){
//		List<Book> books = bookService.getAll();
		Sort sort = Sort.by(Sort.Direction.DESC, "id");
		Pageable pageable = PageRequest.of(page, size, sort);
		Page<Book> books = bookService.findAllByPage(pageable);
		model.addAttribute("books", books);
		return "list";
	}
	
	/**
	 * 獲取指定id的書本詳情
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/book/{id}")
	public String deteil(@PathVariable long id, Model model) {
		Book book = (Book) bookService.findById(id);
		model.addAttribute("book", book);
		return "book";
	}
	
	/**
	 * 跳轉到新增頁面
	 * @return
	 */
	@GetMapping("/books/input")
	public String toInputPage(Model model) {
		model.addAttribute("book", new Book());
		return "input";
	}
	
	/**
	 * 跳轉到更新頁面
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/books/{id}/edit")
	public String toEditPage(@PathVariable long id, Model model) {
		Book book = bookService.findById(id);
		model.addAttribute("book", book);
		return "input";
	}
	
	/**
	 * 新增一本書
	 * @param book
	 * @return
	 */
	@PostMapping("/books")
	public String inputPage(Book book, final RedirectAttributes attributes) {
		Book book1 = bookService.add(book);
		if(book1 != null) {
			attributes.addFlashAttribute("message", "《" + book1.getName() + "》 信息提交成功");
		}
		return "redirect:/api/books";
	}
	
	/**
	 * 利用分頁的方式獲取全部書單列表
	 * @return
	 */
	@GetMapping("/pages")
	public Page<Book> findAllByPage(
			@RequestParam(defaultValue = "0") int page, 
			@RequestParam(defaultValue = "5") int size){
		Sort sort = Sort.by(Sort.Direction.DESC, "id");
		Pageable pageable = PageRequest.of(page, size, sort);
		return bookService.findAllByPage(pageable);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
