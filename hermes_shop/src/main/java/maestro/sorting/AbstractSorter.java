package maestro.sorting;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.LinkedHashMap;
import java.util.Map;

public class AbstractSorter<T> implements ISorter<T> {

    public static Integer FIRST_PAGE = 1;
    public static Integer PAGE_SIZE_DEFAULT = 3;
    public static Sort.Direction DIRECTION_DEFAULT = Sort.Direction.ASC;

    protected final Map<String, String> sortFieldOptions = new LinkedHashMap<>();
    private final Map<Integer, String> pageSizeOptions = new LinkedHashMap<>();
    private final Map<String, String> directionOptions = new LinkedHashMap<>();
    private Integer pageNumber;
    private Integer pageSize;
    private String sortBy;
    private Sort.Direction sortDirection;

    public AbstractSorter() {
        directionOptions.put(DIRECTION_DEFAULT.toString(), "по возрастанию");
        directionOptions.put("desc", "по убыванию");

        pageSizeOptions.put(3, "3");
        pageSizeOptions.put(5, "5");
        pageSizeOptions.put(10, "10");
        pageSizeOptions.put(20, "20");
    }

    private static Sort.Direction parseSortDirection(String direction) {
        if (direction == null)
            return DIRECTION_DEFAULT;
        return Sort.Direction.fromOptionalString(direction).orElse(DIRECTION_DEFAULT);
    }

    @Override
    public PageRequest updateSorting(SortingValuesDTO values) {
        this.sortBy = (values.getSort() == null) ? getSortFieldDefault() : values.getSort();
        this.pageSize = (values.getSize() == null) ? getDefaultPageSize() : values.getSize();
        this.pageNumber = (values.getPage() == null) ? FIRST_PAGE : values.getPage();
        this.sortDirection = parseSortDirection(values.getDirect());
        return createPageRequest();
    }

    protected int getDefaultPageSize() {
        return PAGE_SIZE_DEFAULT;
    }

    private String getSortFieldDefault() {
        return sortFieldOptions.keySet().iterator().next();
    }

    private PageRequest createPageRequest() {
        return PageRequest.of(
                getPageNumber() - 1,
                getPageSize(),
                getSortDirection(),
                getSortBy());
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public String getSortBy() {
        return sortBy;
    }

    public Sort.Direction getSortDirection() {
        return sortDirection;
    }

    public Map<Integer, String> getPageSizeOptions() {
        return pageSizeOptions;
    }

    public Map<String, String> getSortFieldOptions() {
        return sortFieldOptions;
    }

    public Map<String, String> getDirectionOptions() {
        return directionOptions;
    }
}
