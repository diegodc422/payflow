package br.com.diego.payflow.controller.records;

import java.util.List;

public record ApiResponse <T> (List<T> data, PaginationResponse pagination) {

}
