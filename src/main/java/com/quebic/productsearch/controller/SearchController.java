package com.quebic.productsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lovi.searchbox.common.async.response.AsyncResponseEntity;
import com.lovi.searchbox.common.util.AsyncControllerBase;
import com.lovi.searchbox.query.Criteria;
import com.lovi.searchbox.query.Query;
import com.lovi.searchbox.service.AsyncSearchBoxOperations;
import com.lovi.searchbox.service.search.Page;
import com.lovi.searchbox.service.search.SearchResult;
import com.quebic.productsearch.dto.SearchDto;
import com.quebic.productsearch.model.Product;
import rx.Single;

@RestController
@RequestMapping("/search")
public class SearchController extends AsyncControllerBase{
	
	@Autowired
	private AsyncSearchBoxOperations searchBoxOperations;
	
	/**
	 * (title == {titlePrefix}) 
	 * <br/>
	 * AND (priceValue >= {minPrice}) 
	 * <br/>
	 * AND (priceValue <= {maxPrice}) 
	 * <br/>
	 * AND ( ( productCategories == {productCategories[0]} ) OR ( productCategories == {productCategories[1]} ) ... )
	 * @param searchDto
	 * @param page
	 * @return
	 */
	@RequestMapping
	public AsyncResponseEntity<SearchResult<Product>> search(
			@ModelAttribute SearchDto searchDto
			, @ModelAttribute Page page){
		
		Criteria criteria = null;
		
		//item name prefix filter
		String itemNamePrefix = searchDto.getTitlePrefix();
		
		if(!StringUtils.isEmpty(itemNamePrefix))
			criteria = Criteria.where("title").searchByPrefix(itemNamePrefix);
		
		
		//item minPrice filter
		double minPrice = searchDto.getMinPrice();
		if(minPrice != 0.0){
							
			if(criteria == null)
				criteria = Criteria.where("price").gte(minPrice);
			else
				criteria = criteria.and("price").gte(minPrice);
							
		}
		
		//item maxPrice filter
		double maxPrice = searchDto.getMaxPrice();
		if(maxPrice != 0.0){
					
			if(criteria == null)
				criteria = Criteria.where("price").lte(maxPrice);
			else
				criteria = criteria.and("price").lte(maxPrice);
					
		}
			
		//productCategories filter
		Criteria criteriaItemTypes = null;
		for(String condition : searchDto.getProductCategories()){

			if(criteriaItemTypes == null)
				criteriaItemTypes = Criteria.where("productCategories").is(condition);
			else
				criteriaItemTypes = criteriaItemTypes.or("productCategories").is(condition);
			
		}
		
		if(criteria == null){
			
			if(criteriaItemTypes == null)
				return makeAsyncResponse(Single.just(new SearchResult<>()));
			else
				criteria = criteriaItemTypes;
			
		}else
			if(criteriaItemTypes != null) criteria = criteria.and(criteriaItemTypes);
			
		Query query = new Query(criteria);
		return makeAsyncResponse(searchBoxOperations.search(Product.class, query, page));
		
	}

}
