package com.mraihaniqbal.unittest.library.dao;

import com.mraihaniqbal.unittest.library.entity.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelfDao extends JpaRepository<Shelf,Long> {
}
