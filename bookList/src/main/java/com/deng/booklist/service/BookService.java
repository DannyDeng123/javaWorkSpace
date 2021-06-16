package com.deng.booklist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.deng.booklist.entity.Book;
import com.deng.booklist.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	/**
	 * 取得全部書單
	 * @return
	 */
	public List<Book> getAll(){
		return bookRepository.findAll();
	}
	
	/**
	 * 獲取指定id的書
	 * @param id
	 * @return
	 */
	public Book findById(long id) {
		return bookRepository.getById(id);
	}
	
	/**
	 * 新增一本書
	 * @param book
	 * @return
	 */
	public Book add(Book book) {
		return bookRepository.save(book);
	}
	
	/**
	 * Controller分頁查詢
	 * @param pageable
	 * @return
	 */
	public Page<Book> findAllByPage(Pageable pageable){
//		Sort sort = Sort.by(Sort.Direction.DESC, "id");
//		Pageable pageable = PageRequest.of(1, 5, sort);
		return bookRepository.findAll(pageable);
	}
	
}
