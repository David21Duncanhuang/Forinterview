package com.firstspringboot.myspringapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping(path = "/show")
    public String showTheWorld()
    {
        return "Praise the Lore, Thank You Jesus";
    }

    public int Math(int n)
    {
        int rlt = n;
        for (int i = n-1; i >= 1; i--) {
            rlt = rlt * (i);
            //System.out.println(i);
        }
        return rlt;
    }

    public void findNumber()
    {
        int i = 0;

        for ( i = 0; i <= 100; i++) {
            if(i%3 == 0 && i%5 ==0 && i != 0)
            {
                System.out.println(i + ": 35");
            }
            else if(i%3 == 0 && i != 0)
            {
                System.out.println(i + ": 3");
            }
            else if(i%5 ==0 && i != 0)
            {
                System.out.println(i + ": 5");
            }
            //System.out.println(i);
        }
    }

}
