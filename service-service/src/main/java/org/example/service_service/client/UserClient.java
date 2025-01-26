package org.example.service_service.client;

import com.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="user-service", url = "${user.service.url}")
public interface UserClient {

    @GetMapping("/users/{id}")
    User getUserById(@PathVariable("id") int id);
}
