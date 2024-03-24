package com.fsse2401.project_backend.api;

import com.fsse2401.project_backend.data.product.domainObject.ProductResponseData;
import com.fsse2401.project_backend.data.product.dto.response.GetAllProductResponseDto;
import com.fsse2401.project_backend.data.product.dto.response.ProductResponseDto;
import com.fsse2401.project_backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/public/product")
public class ProductApi {
    private final ProductService productService;

    @Autowired
    public ProductApi(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<GetAllProductResponseDto> getAllProduct(){
        List <GetAllProductResponseDto> responseDtoList = new ArrayList<>();
        for(ProductResponseData data: productService.getAllProduct()){
            responseDtoList.add(new GetAllProductResponseDto(data));
        }
        return responseDtoList;
    }

    @GetMapping("/{pid}")
    public ProductResponseDto getProductByPid(@PathVariable Integer pid){
        ProductResponseData responseData = productService.getByPid(pid);
        return new ProductResponseDto(responseData);
    }
}
