package com.secure.search.cloud.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.secure.search.cloud.model.User;

@Repository
public interface UserRepository extends ElasticsearchRepository<User, String>{

}
