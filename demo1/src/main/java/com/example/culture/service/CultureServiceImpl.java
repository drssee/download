package com.example.culture.service;

import com.example.api.ApiProvider;
import com.example.api.ApiRatePolicy;
import com.example.culture.repository.CultureRepository;
import com.example.domain.CultureVO;
import com.example.domain.PageRequest;
import com.example.domain.PageResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class CultureServiceImpl implements CultureService {

    private final ApiProvider apiProvider;
    private final ApiRatePolicy apiRatePolicy;
    private final CultureRepository<CultureVO> cultureRepository;

//    public CultureServiceImpl(ApiProvider apiProvider,
//              ApiRatePolicy apiRatePolicy ,
//              CultureRepository<Culture> cultureRepository) {
//        this.apiProvider = apiProvider;
//        this.apiRatePolicy = apiRatePolicy;
//        this.cultureRepository = cultureRepository;
//    }

    @Override
    public void register() {
        try {
            //가져온 api 데이터로 culture 객체를 완성 후 insert
            List<CultureVO> list = (List<CultureVO>) apiProvider.apiProvide();
            for (CultureVO cultureVO : list) {
                Map<String,Integer> map = apiRatePolicy.apiRatePolicy();
                cultureVO.setCapacity(map.get("capacity"));
                if(cultureVO.getPay_ay_nm().contains("유료")){
                    cultureVO.setPrice(map.get("price"));
                }
                else{
                    cultureVO.setPrice(0);
                }
                //repository에 insert
                cultureRepository.insert(cultureVO);
            }//for

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("api 등록에 실패했습니다");
            throw new RuntimeException(e);
        }
    }

    @Override
    public PageResponse<CultureVO> getCultures(PageRequest pageRequest) {
        return cultureRepository.selectAll(pageRequest);
    }

    @Override
    public CultureVO getCulture(Long cno) {
        Optional<CultureVO> result = cultureRepository.selectOne(cno);
        return result.orElseThrow(); //NoSuchElementException
    }

    @Override
    public void removeAll() {
        cultureRepository.deleteAll();
    }
}
