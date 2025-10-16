package com.example.hsf302_group1.config;

import com.example.hsf302_group1.model.Author;
import com.example.hsf302_group1.model.Book;
import com.example.hsf302_group1.repository.AuthorRepository;
import com.example.hsf302_group1.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(AuthorRepository authorRepo, BookRepository bookRepo) {
        return args -> {

            if (authorRepo.count() == 0 && bookRepo.count() == 0) {

                // Tạo sách
                Book book1 = new Book();
                book1.setName("Mắt Biếc");
                book1.setType("Tiểu thuyết");
                book1.setPrice(100000);

                Book book2 = new Book();
                book2.setName("Cho Tôi Xin Một Vé Đi Tuổi Thơ");
                book2.setType("Văn học thiếu nhi");
                book2.setPrice(85000);

                Book book3 = new Book();
                book3.setName("Truyện Kiều");
                book3.setType("Thơ");
                book3.setPrice(120000);

                Book book4 = new Book();
                book4.setName("Lão Hạc");
                book4.setType("Truyện ngắn");
                book4.setPrice(75000);

                // Lưu tạm thời để có ID
                bookRepo.saveAll(Arrays.asList(book1, book2, book3, book4));

                // Tạo tác giả
                Author author1 = new Author();
                author1.setName("Nguyễn Nhật Ánh");
                author1.setDob("1955-05-07");
                author1.setQuotes("Tuổi thơ dữ dội là tác phẩm tôi yêu nhất");
                author1.setBookList(Arrays.asList(book1, book2));

                Author author2 = new Author();
                author2.setName("Nguyễn Du");
                author2.setDob("1765-01-03");
                author2.setQuotes("Trăm năm trong cõi người ta...");
                author2.setBookList(List.of(book3));

                Author author3 = new Author();
                author3.setName("Nam Cao");
                author3.setDob("1915-10-29");
                author3.setQuotes("Sống đã rồi hãy viết.");
                author3.setBookList(List.of(book4));

                authorRepo.saveAll(Arrays.asList(author1, author2, author3));

                System.out.println("✅ Dữ liệu mẫu Author và Book đã được khởi tạo!");
            } else {
                System.out.println("ℹ️ Dữ liệu đã tồn tại, không cần khởi tạo lại.");
            }
        };
    }
}
