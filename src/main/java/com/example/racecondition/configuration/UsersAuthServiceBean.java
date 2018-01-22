package com.example.racecondition.configuration;

import com.example.racecondition.engagement.EngagementsRepository;
import com.example.racecondition.users.UsersAuthService;
import com.example.racecondition.users.UsersAuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsersAuthServiceBean {
  @Autowired
  private EngagementsRepository repository;

  @Bean
  public UsersAuthService usersAuthService(){
    return new UsersAuthServiceImpl(repository);
  }
}
