package com.example.project1.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.project1.models.PostCodes;

public interface PostCodesRepository extends JpaRepository<PostCodes, Long> {
        // Additional custom queries for News entity
}

