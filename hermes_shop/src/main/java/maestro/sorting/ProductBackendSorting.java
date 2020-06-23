package maestro.sorting;

import org.springframework.stereotype.Component;

@Component
public class ProductBackendSorting extends AbstractSorter {
    private int defaultPageSize=0;

    public ProductBackendSorting() {
    }

    public ProductBackendSorting(int defaultPageSize) {
        this.defaultPageSize = defaultPageSize;
        sortFieldOptions.put("price", "по цене");
        sortFieldOptions.put("category", "по категории");
    }

    @Override
    public int getDefaultPageSize() {
        return defaultPageSize;
    }
}
