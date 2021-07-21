package com.demo.order.feign;

import com.demo.order.vo.MemberReceiveAddressVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author wangxing
 * @date 2021/7/21 14:55
 */
@FeignClient("gulimall-member")
public interface MemberFeignService {

    @GetMapping("/{memberId}/address")
    List<MemberReceiveAddressVo> getAddress(@PathVariable("memberId") Long memberId);

}
