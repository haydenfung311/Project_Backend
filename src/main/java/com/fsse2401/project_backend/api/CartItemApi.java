package com.fsse2401.project_backend.api;

import com.fsse2401.project_backend.Util.JwtUtil;
import com.fsse2401.project_backend.data.cartitem.domainObject.CartItemResponseData;
import com.fsse2401.project_backend.data.cartitem.domainObject.Result;
import com.fsse2401.project_backend.data.cartitem.dto.response.CartItemResponseDto;
import com.fsse2401.project_backend.data.cartitem.dto.response.ResultStatusResponseDto;
import com.fsse2401.project_backend.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartItemApi {
    private final CartItemService cartItemService;

    @Autowired
    public CartItemApi(CartItemService cartItemService){
        this.cartItemService = cartItemService;
    }

    @PutMapping ("/{pid}/{quantity}")
    public ResultStatusResponseDto putCartItem(JwtAuthenticationToken jwtToken,
                                           @PathVariable Integer pid,
                                           @PathVariable Integer quantity){
       cartItemService.putCartItem(JwtUtil.getFirebaseUserData(jwtToken), pid, quantity);

       return new ResultStatusResponseDto(Result.success);

//        cartItemService.putCartItem(JwtUtil.getFirebaseUserData(jwtToken),pid,quantity);
//        return new CartItemResponseDto();
    }

    @GetMapping
    public List<CartItemResponseDto> getUserCartItems (JwtAuthenticationToken jwtToken)
    {
        List<CartItemResponseData> cartItemResponseDataList = cartItemService.getUserCartItems(JwtUtil.getFirebaseUserData(jwtToken));

        List<CartItemResponseDto> cartItemResponseDtoList = new ArrayList<>();
        for (CartItemResponseData cartItemResponseData : cartItemResponseDataList)
        {
            CartItemResponseDto cartItemResponseDto = new CartItemResponseDto(cartItemResponseData);
            cartItemResponseDtoList.add(cartItemResponseDto);
        }

        return cartItemResponseDtoList;
    }

    @PatchMapping("/{pid}/{quantity}")
    public CartItemResponseDto patchCartItemQuantity(JwtAuthenticationToken jwtToken, @PathVariable Integer pid, @PathVariable Integer quantity){
        CartItemResponseData responseData = cartItemService.updateQuantity(JwtUtil.getFirebaseUserData(jwtToken), pid, quantity);
        return  new CartItemResponseDto(responseData);
    }

    @DeleteMapping("/{pid}")
    public ResultStatusResponseDto deleteCartItemByPid(JwtAuthenticationToken jwtToken, @PathVariable Integer pid){
        cartItemService.removeCartItemByPid(JwtUtil.getFirebaseUserData(jwtToken), pid);
        return new ResultStatusResponseDto(Result.success);
    }
}
