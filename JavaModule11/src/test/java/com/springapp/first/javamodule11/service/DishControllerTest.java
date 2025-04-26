package com.springapp.first.javamodule11.service;

import com.springapp.first.javamodule11.DTO.DishGetDTO;
import com.springapp.first.javamodule11.controller.DishController;
import com.springapp.first.javamodule11.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Collections;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DishController.class)
@Import(GlobalExceptionHandler.class)
@ExtendWith(SpringExtension.class)
class DishControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DishService dishService;

    private static Stream<Arguments> provideSearchParameters() {
        return Stream.of(
                // 1. Все параметры указаны — ожидаем успех
                Arguments.of("Italian", 0, 10, 200, "{\"content\":[{\"id\":1,\"name\":\"Pizza\",\"cuisine\":\"Italian\"}],\"pageable\":\"INSTANCE\",\"last\":true,\"totalPages\":1,\"totalElements\":1,\"size\":1,\"number\":0,\"sort\":{\"empty\":true,\"sorted\":false,\"unsorted\":true},\"first\":true,\"numberOfElements\":1,\"empty\":false}"),

                // 2. Есть только keyword — должно быть 400
                Arguments.of("Italian", null, null, 400, "Page и size должны присутствовать"),

                // 3. Все параметры отсутствуют — тоже 400
                Arguments.of(null, null, null, 400, "Page и size должны присутствовать"),

                // 4. Только page и size — ожидаем успех
                Arguments.of(null, 0, 10, 200, "{\"content\":[{\"id\":1,\"name\":\"Pizza\",\"cuisine\":\"Italian\"}],\"pageable\":\"INSTANCE\",\"last\":true,\"totalPages\":1,\"totalElements\":1,\"size\":1,\"number\":0,\"sort\":{\"empty\":true,\"sorted\":false,\"unsorted\":true},\"numberOfElements\":1,\"first\":true,\"empty\":false}")
        );
    }

    @ParameterizedTest
    @MethodSource("provideSearchParameters")
    void search_testWithVariousParams(String keyword, Integer page, Integer size, int expectedStatus, String expectedBody) throws Exception {
        // Мокаем вызов сервиса только если статус ожидается как 200
        if (expectedStatus == 200) {
            DishGetDTO dto = new DishGetDTO(1L, "Pizza", "Italian");
            Page<DishGetDTO> pageResult = new PageImpl<>(Collections.singletonList(dto));
            //Mockito.when(dishService.globalSearch(any(), any()))
            //        .thenReturn(pageResult);
            Mockito.when(dishService.globalSearch(keyword, PageRequest.of(page, size)))
                    .thenReturn(pageResult);
        }

        MockHttpServletRequestBuilder request = get("/dishes/search");
        if (keyword != null) request = request.param("keyword", keyword);
        if (page != null) request = request.param("page", page.toString());
        if (size != null) request = request.param("size", size.toString());

        ResultActions result = mockMvc.perform(request)
                .andExpect(status().is(expectedStatus));

        // Неплохо было бы объединитть тесты по смылу (успешный/нет нет 200-е/400-е/500-e)
        if (!expectedBody.isEmpty()) {
            if (expectedStatus == 400) {
                verify(dishService, never()).globalSearch(any(), any());
                result.andExpect(content().string(expectedBody));
            }
            else if (expectedStatus == 200) {
                verify(dishService, times(1)).globalSearch(keyword, PageRequest.of(page, size));
                result.andExpect(content().json(expectedBody));
            }
        }
    }
}


