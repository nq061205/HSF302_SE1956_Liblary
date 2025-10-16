package com.example.hsf302_group1.config;

import com.example.hsf302_group1.model.User;
import com.example.hsf302_group1.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Date; // Sử dụng java.sql.Date cho dễ tương tác với JPA
import java.util.Arrays;
import java.util.List;

@Configuration
public class UserDataInitilizer {

    @Bean
    CommandLineRunner initUserDatabase(UserRepository userRepo) {
        return args -> {
            // Chỉ khởi tạo dữ liệu nếu bảng users trống
            if (userRepo.count() == 0) {

                // Tạo người dùng 1: Nguyen Van A
                User user1 = new User();
                user1.setName("Nguyen Van A");
                user1.setGender(true); // 1 -> true (Nam)
                user1.setDob(Date.valueOf("2000-05-15"));
                user1.setBalance(200000.0);
                user1.setMembership("BASIC");
                user1.setStatus(true); // 1 -> true (Hoạt động)

                // Tạo người dùng 2: Tran Thi B
                User user2 = new User();
                user2.setName("Tran Thi B");
                user2.setGender(false); // 0 -> false (Nữ)
                user2.setDob(Date.valueOf("1999-08-22"));
                user2.setBalance(120000.0);
                user2.setMembership(null); // Membership là NULL
                user2.setStatus(true); // 1 -> true (Hoạt động)

                // Tạo người dùng 3: Le Van C
                User user3 = new User();
                user3.setName("Le Van C");
                user3.setGender(true); // 1 -> true (Nam)
                user3.setDob(Date.valueOf("2001-01-05"));
                user3.setBalance(50000.0);
                user3.setMembership("PREMIUM");
                user3.setStatus(true); // 1 -> true (Hoạt động)

                // Tạo người dùng 4: Pham Thi D
                User user4 = new User();
                user4.setName("Pham Thi D");
                user4.setGender(false); // 0 -> false (Nữ)
                user4.setDob(Date.valueOf("2003-03-10"));
                user4.setBalance(300000.0);
                user4.setMembership("VIP");
                user4.setStatus(true); // 1 -> true (Hoạt động)

                // Lưu tất cả người dùng vào database
                userRepo.saveAll(Arrays.asList(user1, user2, user3, user4));

                System.out.println("✅ Dữ liệu mẫu cho User đã được khởi tạo thành công!");
            } else {
                System.out.println("ℹ️ Dữ liệu User đã tồn tại, không cần khởi tạo lại.");
            }
        };
    }
}