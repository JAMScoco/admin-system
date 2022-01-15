package com.jamscoco.service.impl;

import com.jamscoco.entity.Book;
import com.jamscoco.mapper.BookMapper;
import com.jamscoco.service.IBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JAMScoco
 * @since 2022-01-15
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {

}
