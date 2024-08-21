package com.rgt.dto.response;

import com.rgt.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MenuRespDto {
    private Long menuId;
    private String menuName;
    private Long menuPrice;
    private String menuInfo;

    public static MenuRespDto from(Menu menu){
        return MenuRespDto.builder().
                menuId(menu.getMenuId()).
                menuName(menu.getMenuName()).
                menuPrice(menu.getMenuPrice()).
                menuInfo(menu.getMenuInfo()).
                build();
    }
}
