package com.inventory.management.inventory_management.shared.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class Paginate<T> {

  private int page;
  private int limit;

  public Paginate(Integer page, Integer limit) {
    this.page = (page != null && page > 0) ? page : 1;
    this.limit = (limit != null && limit > 0) ? limit : 10;
  }

  public Pageable toPageable() {
    return PageRequest.of(this.page - 1, this.limit);
  }

  public PaginationMeta buildMeta(Page<T> pagedData) {
    int totalPages = pagedData.getTotalPages();
    long totalItems = pagedData.getTotalElements();
    int currentPageItems = pagedData.getNumberOfElements();

    String currentUrl = buildPageUrl(this.page);
    String nextPageUrl = (this.page < totalPages) ? buildPageUrl(this.page + 1) : null;
    String previousPageUrl = (this.page > 1) ? buildPageUrl(this.page - 1) : null;
    String lastPageUrl = totalPages > 0 ? buildPageUrl(totalPages) : null;

    return new PaginationMeta(
        this.page,
        currentPageItems,
        totalItems,
        currentUrl,
        nextPageUrl,
        previousPageUrl,
        lastPageUrl
    );
  }

  private String buildPageUrl(int page) {
    return ServletUriComponentsBuilder.fromCurrentRequest()
        .replaceQueryParam("page", page)
        .replaceQueryParam("limit", this.limit)
        .toUriString();
  }

  public static class PaginationMeta {

    public PaginationMeta(int currentPage, int currentPageItems, long totalItems,
        String currentPageUrl, String nextPageUrl,
        String previousPageUrl, String lastPageUrl) {
    }

    // Getters e Setters
  }
}
