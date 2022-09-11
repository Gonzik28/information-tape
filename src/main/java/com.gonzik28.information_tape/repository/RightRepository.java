package com.gonzik28.information_tape.repository;

import com.gonzik28.information_tape.entity.RightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RightRepository extends JpaRepository<RightEntity, String> {
}
