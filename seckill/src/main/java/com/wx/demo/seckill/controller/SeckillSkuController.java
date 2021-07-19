package com.wx.demo.seckill.controller;

import com.wx.demo.seckill.service.SeckillService;
import com.wx.demo.seckill.to.SeckillSkuRedisTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangxing
 * @date 2021/7/19 13:02
 */
@RestController
public class SeckillSkuController {

    @Autowired
    private SeckillService seckillService;

    /**
     * 当前时间可以参与秒杀商品的信息
     *
     * @return list SeckillSkuRedisTO
     */
    @GetMapping("/currentSeckillSkus")
    public List<SeckillSkuRedisTO> getCurrentSeckillSkus() {
        return seckillService.getCurrentSeckillSkus();
    }

    /**
     * @param skuId
     * @return
     */
    @GetMapping("skuSeckillInfo")
    public SeckillSkuRedisTO getSkuSeckillInfo(@RequestParam("skuId") Long skuId) {
        return seckillService.getSkuSeckillInfo(skuId);
    }

    /**
     * 秒杀
     *
     * @param killId 秒杀id
     * @param key    随机码
     * @param num    秒杀数量
     */
    @GetMapping("/kill")
    public String seckill(@RequestParam("killId") String killId, @RequestParam("key") String key, @RequestParam("num") Integer num, Model model) {
        // 判断是否登陆,拦截器已经做了
        String orderSn = seckillService.kill(killId, key, num);

        model.addAttribute("orderSn", orderSn);

        return "success";
    }

}
