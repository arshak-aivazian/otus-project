package com.example.namegenerator.utility;

import com.example.namegenerator.dto.filter.AndFilter;
import com.example.namegenerator.dto.filter.Filter;
import com.example.namegenerator.dto.filter.OrFilter;
import com.example.namegenerator.dto.filter.SearchCriteria;
import com.example.namegenerator.entity.PetName;
import com.example.namegenerator.repository.PetNameSpecification;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class SpecificationCreator {

    public static Specification<PetName> create(Filter filter) {
        if (filter instanceof OrFilter) {
            OrFilter orFilter = (OrFilter) filter;
            List<Specification<PetName>> specs = createInnerSpecifications(orFilter.getValue());
            return createOrSpecification(specs);
        } else if (filter instanceof AndFilter) {
            AndFilter andFilter = (AndFilter) filter;
            List<Specification<PetName>> specs = createInnerSpecifications(andFilter.getValue());
            return createAndSpecification(specs);
        } else if (filter instanceof SearchCriteria) {
            SearchCriteria searchCriteria = (SearchCriteria) filter;
            return createSearchSpecification(searchCriteria);
        }
        return null;
    }

    private List<Specification<PetName>> createInnerSpecifications(List<Filter> value) {
        List<Specification<PetName>> specs = new ArrayList<>();

        if (value != null && !value.isEmpty()) {
            value.forEach(f -> {
                specs.add(create(f));
            });
        }
        return specs;
    }

    private Specification<PetName> createAndSpecification(List<Specification<PetName>> specs) {
        Specification<PetName> andSpecification = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            andSpecification = specs.get(i).and(andSpecification);
        }
        return andSpecification;
    }

    private Specification<PetName> createOrSpecification(List<Specification<PetName>> specs) {
        Specification<PetName> orSpecification = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            orSpecification = specs.get(i).or(orSpecification);
        }
        return orSpecification;
    }

    private Specification<PetName> createSearchSpecification(SearchCriteria searchCriteria) {
        return new PetNameSpecification(searchCriteria);
    }
}
