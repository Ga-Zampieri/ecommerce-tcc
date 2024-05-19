package com.zprmts.tcc.ecommerce.repository.specification;

import com.zprmts.tcc.ecommerce.domain.Perfume;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;

@Component
public class PerfumeSpecifications {

    public Specification<Perfume> byIdPerfume(Long idPerfume) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), idPerfume);
    }

    public Specification<Perfume> byName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + name.toUpperCase() + "%");
    }

    public Specification<Perfume> byDescription(String description) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("description")), "%" + description.toUpperCase() + "%");
    }

    public Specification<Perfume> byCategories(String categories) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("categories")), "%" + categories.toUpperCase() + "%");
    }

    public Specification<Perfume> builderSpecification(@Nullable Long idPerfume,
                                                       @Nullable String name,
                                                       @Nullable String description,
                                                       @Nullable String categories) {

        Specification spec = Specification.where(null);
        if (Objects.nonNull(idPerfume)) {
            spec = spec.and(byIdPerfume(idPerfume));
        }
        if (Objects.nonNull(name)) {
            spec = spec.and(byName(name));
        }
        if (Objects.nonNull(description)) {
            spec = spec.and(byDescription(description));
        }
        if (Objects.nonNull(categories)) {
            spec = spec.and(byCategories(categories));
        }
        return spec;
    }
}