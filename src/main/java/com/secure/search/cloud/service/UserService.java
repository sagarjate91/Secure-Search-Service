package com.secure.search.cloud.service;

import java.util.List;

import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.secure.search.cloud.model.User;

@Service
public class UserService {
	
	@Autowired
	private ElasticsearchTemplate template;
	
	public List<User> searchMultiField(String firstname,int age){
		QueryBuilder query =QueryBuilders.boolQuery()
				.must(QueryBuilders.matchQuery("firstname", firstname))
				.must(QueryBuilders.matchQuery("age", age));
		NativeSearchQuery nsq=new NativeSearchQueryBuilder().withQuery(query).build();
		List<User> user=template.queryForList(nsq, User.class);
		return user;
	}
	
	public List<User> searchPartialQuery(String input){
		String search=".*"+input+".*";
		SearchQuery query=new NativeSearchQueryBuilder()
				.withFilter(QueryBuilders.regexpQuery("desc", search)).build();
		List<User> user=template.queryForList(query, User.class);
		return user;
	}
	
	public List<User> multiMatchQuery(String text){
		
		SearchQuery search=new NativeSearchQueryBuilder()
				.withQuery(QueryBuilders.multiMatchQuery(text)
						.field("firstname")
						.field("lastname")
						.field("desc")
						.type(MultiMatchQueryBuilder.Type.BEST_FIELDS)).build();
		
		List<User> user=template.queryForList(search, User.class);
		return user;
		
	}
	

}
