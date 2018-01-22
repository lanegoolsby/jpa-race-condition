package com.example.racecondition.engagement;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EngagementsRepository extends JpaRepository<Engagement, Long> {
  Engagement findById(Long id);
}
