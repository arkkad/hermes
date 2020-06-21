package maestro.sorting;

import org.springframework.data.domain.PageRequest;

public interface ISorter<T> {
    PageRequest updateSorting(SortingValuesDTO sortingValues);
}
