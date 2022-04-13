package com.example.service;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.model.NetflixTitle;
import com.example.repository.ResourceRepository;

@Service
public class ResourceService {

	private final ResourceRepository repository;

	@Autowired
	public ResourceService(ResourceRepository repository) {
		super();
		this.repository = repository;
	}
	
	public List<NetflixTitle> getResource(Map<String, String> paramList) {
		NetflixTitle title = new NetflixTitle();
		for(var key : new ArrayList<String>(paramList.keySet())) {
			if(key.equals("releaseYear")) {
				title.setReleaseYear(Integer.parseInt(paramList.get(key)));
			}
			else if(key.equals("actor")) {
				title.setCast(paramList.get(key));
			}
			else {
				invokeSetter(title, key, paramList.get(key));
			}
		}
		System.out.println("Bean Obj: " + title.toString());
		
		ExampleMatcher caseInsensitiveMatcher = ExampleMatcher.matchingAll().withIgnoreCase().withMatcher("cast", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		Sort sort = Sort.by(Direction.ASC, "showId");
		List<NetflixTitle> titleList = repository.findAll(Example.of(title, caseInsensitiveMatcher), sort);
		if(titleList.isEmpty()) {
			ExampleMatcher caseInsensitiveOrMatcher = ExampleMatcher.matchingAny().withIgnoreCase().withMatcher("cast", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
			titleList = repository.findAll(Example.of(title, caseInsensitiveOrMatcher), sort);
		}
		
		return titleList;
	}
	
	private void invokeSetter(Object obj, String fieldName, Object value) {
	    try {
	      PropertyDescriptor pd = new PropertyDescriptor(fieldName, obj.getClass());
	      pd.getWriteMethod().invoke(obj, value);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
	
}
