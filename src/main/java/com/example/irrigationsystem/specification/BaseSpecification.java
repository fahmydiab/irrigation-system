package com.example.irrigationsystem.specification;

import org.springframework.data.jpa.domain.Specification;
import com.example.irrigationsystem.util.DateUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public abstract class BaseSpecification<T, U> {

    private static final String WILD_CARD = "%";
    private static final Long MAX_TIMESTAMP = 253370811600000L;

    public abstract Specification<U> getFilter(T request);

    protected String containsLowerCase(String searchField) {
        return WILD_CARD + searchField.toLowerCase() + WILD_CARD;
    }

    protected Specification<U> equal(String attribute, Object value) {
        if (value == null) {
            return null;
        }
        return (root, query, cb) ->
            cb.equal(
                root.get(attribute),
                value
            );
    }

    protected Specification<U> equal(String attribute, Date value) {
        return range(attribute, DateUtils.mapToStartOfDay(value), DateUtils.mapToEndOfDay(value));
    }

    protected Specification<U> isNull(String attribute) {

        return (root, query, cb) ->
            cb.isNull(root.get(attribute));
    }


    protected Specification<U> isNotNull(String attribute) {
        return (root, query, cb) ->
            cb.isNotNull(root.get(attribute));
    }

    protected Specification<U> equal(String attribute,
        List<Integer> ids) {
        if (ids == null) {
            return null;
        }
        return (root, query, cb) -> {
            final Path model = root.get(attribute);
            return model.in(ids);
        };
    }

    protected Specification<U> in(String attribute,
        List<? extends Object> values) {
        return (root, query, cb) -> {
            if (values == null) {
                return null;
            }
            return root.get(attribute).in(values);
        };
    }

    protected Specification<U> like(String attribute, String value) {
        return (root, query, cb) -> {
            if (value == null) {
                return null;
            }
            return cb.like(
                cb.lower(root.get(attribute)),
                containsLowerCase(value)
            );
        };
    }

    protected Specification<U> like(String relName, String attribute, String value) {
        return (root, query, cb) -> {
            if (value == null) {
                return null;
            }
            Join joinRecords = root.join(relName);
            return cb.like(
                cb.lower(joinRecords.get(attribute)),
                containsLowerCase(value)
            );
        };
    }

    protected Specification<U> greaterThanOrEqualTo(String attribute, Integer value) {
        return (root, query, cb) -> {
            if (value == null) {
                return null;
            }
            return cb.greaterThanOrEqualTo(
                root.get(attribute),
                value
            );
        };
    }

    protected Specification<U> greaterThan(String attribute, Integer value) {
        return (root, query, cb) -> {
            if (value == null) {
                return null;
            }
            return cb.greaterThan(
                root.get(attribute),
                value
            );
        };
    }

    protected Specification<U> greaterThan(String attribute, Date value) {
        return (root, query, cb) -> {
            if (value == null) {
                return null;
            }
            return cb.greaterThan(
                root.get(attribute),
                value
            );
        };
    }

    protected Specification<U> greaterThanOrEqualTo(String attribute, Date value) {
        return (root, query, cb) -> {
            if (value == null) {
                return null;
            }
            return cb.greaterThanOrEqualTo(
                root.get(attribute),
                value
            );
        };
    }

    protected Specification<U> lessThanOrEqualTo(String attribute, Date value) {
        return (root, query, cb) -> {
            if (value == null) {
                return null;
            }
            return cb.lessThanOrEqualTo(
                root.get(attribute),
                value
            );
        };
    }

    protected Specification<U> range(String attribute, Date start, Date end) {
        return (root, query, cb) ->
            cb.between(
                root.get(attribute),
                start == null ? new Date(0) : start,
                end == null ? new Date(MAX_TIMESTAMP) : end
            );

    }

    protected Specification<U> greaterThanOrNull(String attribute, boolean value) {
        return value ? greaterThan(attribute, new Date()).or(isNull(attribute)) : null;
    }

    protected Specification<U> between(String attribute, Integer start,
        Integer end) {
        if (start == null || end == null) {
            return null;
        }
        return (root, query, cb) ->
            cb.between(
                root.get(attribute),
                start,
                end
            );
    }

    protected Specification<U> between(String attribute, BigDecimal start,
        BigDecimal end) {
        if (start == null || end == null) {
            return null;
        }
        return (root, query, cb) ->
            cb.between(
                root.get(attribute),
                start,
                end
            );
    }

    protected Specification<U> relationById(String relName, Integer value) {
        return (root, query, cb) -> {
            if (relName == null || value == null) {
                return null;
            }
            Join<U, T> joinRecords = root.join(relName);
            Predicate equalPredicate = cb.equal(joinRecords.get("id"), value);
            query.distinct(true);
            return equalPredicate;
        };
    }

    protected Specification<U> relationGreaterThan(String relName, String attrName, Date value,
        Integer phaseId) {
        if (relName == null || attrName == null || value == null) {
            return null;
        }
        return (root, query, cb) -> {
            Join joinRecords = root.join(relName);
            query.distinct(true);
            return cb.and(
                cb.or(cb.isNull(joinRecords.get(attrName)),
                    cb.greaterThanOrEqualTo(joinRecords.get(attrName), value)),
                cb.equal(joinRecords.get("phase"), phaseId)
            );
        };
    }

    protected Specification<U> relationLessThan(String relName, String attrName, Date value) {
        if (relName == null || attrName == null || value == null) {
            return null;
        }
        return (root, query, cb) -> {
            Join joinRecords = root.join(relName);
            Predicate equalPredicate = cb.lessThanOrEqualTo(joinRecords.get(attrName), value);
            query.distinct(true);
            return equalPredicate;
        };
    }

    protected Specification<U> relation(String relName, String attrName, Object value) {
        if (relName == null || attrName == null || value == null) {
            return null;
        }
        return (root, query, cb) -> {
            Join joinRecords = root.join(relName);
            query.distinct(true);
            return cb.equal(joinRecords.get(attrName), value);
        };
    }

    protected Specification<U> nestedRelations(List<String> relNames, String attrName,
        List<Integer> value) {
        if (relNames == null || attrName == null || value == null) {
            return null;
        }
        return (root, query, cb) -> {
            Join joinRecords = root.join(relNames.get(0));
            Join joinRecords2 = joinRecords.join(relNames.get(1));
            query.distinct(true);
            return joinRecords2.get(attrName).in(value);
        };
    }

    protected Specification<U> relationIn(String relName, String attrName,
        List<? extends Object> values) {
        if (values == null) {
            return null;
        }
        return (root, query, cb) -> {
            Join joinRecords = root.join(relName);
            query.distinct(true);
            return joinRecords.get(attrName).in(values);
        };
    }

    protected Specification<U> relationIn(String relName, String attrName, Object value) {
        if (value == null) {
            return null;
        }
        return (root, query, cb) -> {
            Join joinRecords = root.join(relName);
            query.distinct(true);
            return cb.equal(joinRecords.get(attrName), value);
        };
    }

    protected Specification<U> relationIn(String relName1, String relName2, String attrName,
        List<? extends Object> values) {
        if (values == null) {
            return null;
        }
        return (root, query, cb) -> {
            Join joinRecords = root.join(relName1).join(relName2);

            query.distinct(true);
            return joinRecords.get(attrName).in(values);
        };
    }

    protected Specification<U> relationIn(String relName1, String relName2, String attrName,
        Object value) {
        if (value == null) {
            return null;
        }
        return (root, query, cb) -> {
            Join joinRecords = root.join(relName1).join(relName2);

            query.distinct(true);
            return cb.equal(joinRecords.get(attrName), value);
        };
    }

    protected Specification<U> join(String relName) {
        return (root, query, cb) -> {
            Join joinRecords = root.join(relName);
            return cb.equal(joinRecords.get("id"), root.get("unit_Id"));
        };
    }

    protected Specification<U> byPhaseId(Integer phaseId) {
        return (root, query, cb) ->
            cb.equal(
                root.get("phase").get("id"),
                phaseId
            );
    }

    protected Specification<U> equalsNull(String attribute) {
        return (root, query, cb) ->
            cb.isNull(root.get(attribute));
    }
}
