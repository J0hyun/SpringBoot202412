package com.shop.service;

import com.shop.constant.ItemSellStatus;
import com.shop.dto.ItemFormDto;
import com.shop.entity.ItemImg;
import com.shop.repository.ItemImgRepository;
import com.shop.repository.ItemRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Log4j2
@Transactional
public class ItemServiceTest {
    
    @Autowired
    ItemService itemService;
    
    @Autowired
    ItemRepository itemRepository;
    
    @Autowired
    ItemImgRepository itemImgRepository;
    
    List<MultipartFile> createMultipartFiles() throws Exception {
        List<MultipartFile> multipartFiles = new ArrayList<>();
        
        for(int i=0; i<5; i++) {
            String path = "c:/shop/item/";
            String imageName = "image" + i + ".jpg";

            MockMultipartFile multipartFile = 
                    new MockMultipartFile(path, imageName, "image/jpeg", new byte[]{1,2,3,4,5});
            
        }
        return multipartFiles;
    }
    
    
    @Test
    @DisplayName("상품 등록 테스트")
    @WithMockUser(username = "admin", roles = "ADMIN")
    void saveItem() throws Exception {
        ItemFormDto itemFormDto = new ItemFormDto();
        
        itemFormDto.setItemNm("테스트 상품");
        itemFormDto.setItemSellStatus(ItemSellStatus.SELL);
        itemFormDto.setItemDetail("테스트 상품 입니다.");
        itemFormDto.setPrice(2000);
        itemFormDto.setStockNumber(100);

        List<MultipartFile> multipartFileList = createMultipartFiles();

        Long itemId = itemService.saveItem(itemFormDto, multipartFileList);

        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdDesc(itemId);

        itemImgList.forEach(list -> log.info(list));
    }
    
}