package com.kenny.forum.dao;

import com.kenny.forum.model.Post;
import com.kenny.forum.model.enums.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface PostRepository extends JpaRepository <Post, UUID>{

   Page<Post> findAll(Pageable pageable);

   Page<Post> findByTypeAndCountryAndStateAndCity(Pageable pageable, Type type, String country, String state, String city);
   Optional<Post> findById(UUID uuid);
}
