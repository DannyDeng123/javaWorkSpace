package com.deng.booklist.web;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.deng.booklist.entity.Book;
import com.deng.booklist.service.BookService;

@RestController
@RequestMapping("/rest")
public class BookJsonApi {
	@Autowired
	private BookService bookService;
	
	/**
	 * 獲取一個書單列表
	 * @return
	 */
	@GetMapping("/books")
	public ResponseEntity<?> list(){
		List<Book> books = bookService.getAll();
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}
	
	/**
	 * 獲取指定id的書本詳情
	 * @param id
	 * @return
	 */
	@GetMapping("/books/{id}")
	public ResponseEntity<?> getBook(@PathVariable long id){
		Book book = bookService.findById(id);
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}
	
	/**
	 * 新增一本書
	 * @param book
	 * @return
	 */
	@PostMapping("/books")
	public ResponseEntity<?> addBook(@RequestBody Book book){
		bookService.addBook(book);
		return new ResponseEntity<Book>(book, HttpStatus.CREATED);
	}
	
	/**
	 * 更新一本書
	 * @param id
	 * @param book
	 * @return
	 */
	@PutMapping("/books/{id}")
	public ResponseEntity<?> updateBook(@PathVariable long id, @RequestBody Book book){
		Book currentBook = bookService.findById(id);
		BeanUtils.copyProperties(book, currentBook);
		Book book1 = bookService.updateBook(currentBook);
		return new ResponseEntity<Book>(book1, HttpStatus.OK);
	}
	
	/**
	 * 刪除一本書
	 * @param id
	 * @return
	 */
	@DeleteMapping("/books/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable long id){
		bookService.deleteBookById(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/books")
	public ResponseEntity<?> deleteAll(){
		bookService.deleteAllBooks();
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/ajax")
	public ModelAndView test() {
		ModelAndView mv = new ModelAndView("ajax");
		return mv;
	}
}
