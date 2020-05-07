package ir.khu.jaobshaar.utils;

import ir.khu.jaobshaar.entity.EntityBase;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.metamodel.SingularAttribute;
import java.util.Collection;

public class PredicateHelper {

    public <X> CriteriaBuilder.In<Integer> valueIn(SingularAttribute<? super EntityBase, X> field, final Collection<X>
            values) {
        return null;
    }
}
