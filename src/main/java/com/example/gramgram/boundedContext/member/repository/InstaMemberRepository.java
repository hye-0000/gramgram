package com.example.gramgram.boundedContext.member.repository;

import com.example.gramgram.boundedContext.member.entity.InstaMember;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Spliterator;

public interface InstaMemberRepository extends JpaRepository<InstaMember,Integer> {
    Optional<InstaMember> findByUsername(String user);
}
