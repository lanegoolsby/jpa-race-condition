package com.example.racecondition;

import com.example.racecondition.engagement.Engagement;
import com.example.racecondition.engagement.EngagementsRepository;
import com.example.racecondition.users.User;
import com.example.racecondition.users.UsersAuthService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EngagementTest {
  @Mock
  UsersAuthService usersService;

  @Autowired
  EngagementsRepository engagementsRepository;

  @Autowired
  UsersAuthService authService;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    //authService = new UsersAuthServiceImpl(usersService, engagementsRepository);
  }

  @Test
  public void addingMultipleUsersAtOnceSucceeds() throws InterruptedException {
    Long engagementId = 1L;
    String userId1 = "user1";
    String userId2 = "user2";
    String userId3 = "user3";
    String userId4 = "user4";
    String userId5 = "user5";
    String auth = "asdf";
    User adminUser = User.builder()
                         .userId("adminUser")
                         .email("user@user.com")
                         .name("Admin User")
                         .build();
    Engagement engagement = new Engagement();
    engagement.setAssignedUsers(new ArrayList<>());
    engagement.getAssignedUsers().add(adminUser.getUserId());

    engagementsRepository.save(engagement);

    ExecutorService executorService = Executors.newFixedThreadPool(5);//change this to 1 to see the test pass
    List<Callable<Engagement>> callableList = Arrays.asList(
        addUserThread(engagementId, userId1, auth, adminUser),
        addUserThread(engagementId, userId2, auth, adminUser),
        addUserThread(engagementId, userId3, auth, adminUser),
        addUserThread(engagementId, userId4, auth, adminUser),
        addUserThread(engagementId, userId5, auth, adminUser));

    executorService.invokeAll(callableList);

    Engagement after = engagementsRepository.findById(engagementId);
    assertEquals(6, after.getAssignedUsers().size());
  }

  private Callable<Engagement> addUserThread(Long engagementId, String userId1, String auth, User adminUser) {
    return () -> authService.addUserTo(engagementId, userId1, auth, adminUser);
  }
}